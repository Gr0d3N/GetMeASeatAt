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

public class OpenTable {
    private static final Logger logger = LoggerFactory.getLogger(OpenTable.class);

    private static final String BASE_URL = "http://opentable.herokuapp.com/api/";

    /**
     * Get restaurants by name from Unofficial Open Table API.
     * @param name restaurant name
     * @return a list of restaurants
     * @throws IOException
     */
    public static ArrayList<Restaurant> getRestaurantsByName(final String name) throws IOException {
        String fullURL = BASE_URL + "restaurants?name=" + name;

        URLConnection connection = createConnection(fullURL);

        return parseRestaurants(connection.getInputStream());
    }

    /**
     * Get restaurants by city from Unofficial Open Table API. Limited to the first 100 results.
     * @param city city name
     * @return a list of restaurants
     * @throws IOException
     */
    public static ArrayList getRestaurantsInCity(final String city) throws IOException {
        String fullURL = BASE_URL + "restaurants?city=" + city + "&per_page=100";
        URLConnection connection = createConnection(fullURL);

        return parseRestaurants(connection.getInputStream());
    }

    /**
     *  Establish a connection and return a url object.
     */
    private static URLConnection createConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        return url.openConnection();
    }

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
