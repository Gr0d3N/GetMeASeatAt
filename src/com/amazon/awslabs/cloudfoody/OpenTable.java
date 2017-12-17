package com.amazon.awslabs.cloudfoody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OpenTable {
    private static final Logger logger = LoggerFactory.getLogger(OpenTable.class);

    private static final String BASE_URL = "http://opentable.herokuapp.com/api/";

    /**
     * Get a random restaurant from a list of restaurants
     * @param city
     * @param state
     * @return random restaurant object
     * @throws IOException
     */
    public static Restaurant getRandomRestaurant(final String city, final String state) throws IOException {
        ArrayList<Restaurant> restaurants = getRestaurantsInCity(city, state);
        Random myRandomizer = new Random();
        return restaurants.get(myRandomizer.nextInt(restaurants.size()));
    }

    /**
     * Get restaurants by city from Unofficial Open Table API.
     * @param city
     * @param state
     * @return ArrayList of restaurants
     * @throws IOException
     */
    public static ArrayList<Restaurant> getRestaurantsInCity(String city, final String state) throws IOException {
        int pageNumber = getRandomPage(city, state);
        String fullURL = BASE_URL + "restaurants?city=" + replaceSpacesWithPlus(city) + "&state=" + state + "&page=" + pageNumber + "&per_page=100";
        URLConnection connection = createConnection(fullURL);

        return parseRestaurants(connection.getInputStream());
    }

    /**
     * Get a random page number for multiple pages results
     * @param city
     * @param state
     * @return int pageNumber
     * @throws IOException
     */
    private static int getRandomPage(String city, final String state) throws IOException {
        String fullURL = BASE_URL + "restaurants?city=" + replaceSpacesWithPlus(city) + "&state=" + state;
        URLConnection connection = createConnection(fullURL);
        HashMap resultsHash = new ObjectMapper().readValue(new InputStreamReader(connection.getInputStream()), HashMap.class);

        /* Get the total number of restaurants at this city then divide is be maximum number of restaurants per page with is 100
           to get the number of pages.
         */

        int totalRestaurants = (int) resultsHash.get("total_entries");
        int numberOfPages = (int) Math.ceil(totalRestaurants/100.00);

        if (numberOfPages == 1) {
            return 1;
        } else {
            return ThreadLocalRandom.current().nextInt(1, numberOfPages + 1);
        }
    }

    /**
     * Get restaurants by name from Unofficial Open Table API.
     * @param name restaurant name
     * @return a list of restaurants
     * @throws IOException
     */
    public static ArrayList<Restaurant> getRestaurantsByName(String name) throws IOException {
        String fullURL = BASE_URL + "restaurants?name=" + replaceSpacesWithPlus(name);
        URLConnection connection = createConnection(fullURL);
        return parseRestaurants(connection.getInputStream());
    }

    /**
     * Check if a string contains spaces and replace them with pluses
     * @param originalString
     * @return String
     */
    private static String replaceSpacesWithPlus(final String originalString) {
        if (originalString.contains(" ")) {
            return originalString.replace(" ", "+");
        } else {
            return originalString;
        }
    }

    /**
     *  Establish a connection.
     *  @param urlString
     *  @return url object
     *  @throws IOException
     */
    private static URLConnection createConnection(final String urlString) throws IOException {
        URL url = new URL(urlString);
        return url.openConnection();
    }

    /**
     * Parse a list of restaurant objects from josn
     * @param inputStream
     * @return ArrayList of restaurants
     * @throws IOException
     */
    private static ArrayList<Restaurant> parseRestaurants(final InputStream inputStream) throws IOException {
        final InputStreamReader streamReader = new InputStreamReader(inputStream);
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode root = mapper.readTree(streamReader);

        final ArrayNode restaurantsNode = (ArrayNode) root.get("restaurants");
        final ArrayList<Restaurant> restaurants = new ArrayList();
        for (JsonNode restaurantNode : restaurantsNode) {
            try {
                restaurants.add(mapper.treeToValue(restaurantNode, Restaurant.class));
            } catch (final JsonProcessingException exception) {
                logger.warn(
                        "Failed to parse restaurant object with exception: {}".format(exception.getLocalizedMessage()));
            }
        }

        return restaurants;
    }
}
