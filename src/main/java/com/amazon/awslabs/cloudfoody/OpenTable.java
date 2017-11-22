package com.amazon.awslabs.cloudfoody;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class OpenTable {

    private static final String BASE_URL = "http://opentable.herokuapp.com/api/";

    /**
     *  The method establishes a connection and returns a url object
     */
    public static URLConnection createConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        return url.openConnection();
    }

    /**
     *  The method takes a restaurant name as an arguement and returns back an instance of the Restaurant class
     *  and sets all the fields as well
     */
    public static Restaurant getRestaurantByName(String name) throws IOException {
        String fullURL = BASE_URL + "restaurants?name=" + name;
        URLConnection connection = createConnection(fullURL);
        HashMap resultsHash = new ObjectMapper().readValue(new InputStreamReader((connection.getInputStream())), HashMap.class);
        ArrayList restaurants = (ArrayList)resultsHash.get("restaurants");
        HashMap restaurantHash = (HashMap)restaurants.get(0);
        Restaurant restaurant = new Restaurant();
        restaurant.setName((String) restaurantHash.get("name"));
        restaurant.setRestId((int) restaurantHash.get("id"));
        restaurant.setAddress((String) restaurantHash.get("address"));
        restaurant.setCity((String) restaurantHash.get("city"));
        restaurant.setState((String) restaurantHash.get("state"));
        restaurant.setArea((String) restaurantHash.get("area"));
        restaurant.setPostalCode((String) restaurantHash.get("postal_code"));
        restaurant.setCountry((String) restaurantHash.get("country"));
        restaurant.setPhone((String) restaurantHash.get("phone"));
        restaurant.setLat((double) restaurantHash.get("lat"));
        restaurant.setLng((double) restaurantHash.get("lng"));
        restaurant.setPrice((int) restaurantHash.get("price"));
        restaurant.setReserveURL((String) restaurantHash.get("reserve_url"));
        restaurant.setMobileReserveURL((String) restaurantHash.get("mobile_reserve_url"));
        restaurant.setImageURL((String) restaurantHash.get("img_url"));
        return restaurant;
    }

    /**
     *  The method takes a city name and the current page number as arguements then it
     *  returns back an ArrayList with all the restaurant names in that city
     */
    public static ArrayList getRestaurantsInCity(String city, int page) throws IOException {
        String fullURL = BASE_URL + "restaurants?city=" + city + "&page=" + page + "&per_page=100";
        URLConnection connection = createConnection(fullURL);
        HashMap resultsHash = new ObjectMapper().readValue(new InputStreamReader((connection.getInputStream())), HashMap.class);
        ArrayList jsonRestaurants = (ArrayList)resultsHash.get("restaurants");
        ArrayList restaurants = new ArrayList();
        for (Object jsonRestaurant: jsonRestaurants) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName((String) ((HashMap)jsonRestaurant).get("name"));
            restaurant.setRestId((int) ((HashMap)jsonRestaurant).get("id"));
            restaurant.setAddress((String) ((HashMap)jsonRestaurant).get("address"));
            restaurant.setCity((String) ((HashMap)jsonRestaurant).get("city"));
            restaurant.setState((String) ((HashMap)jsonRestaurant).get("state"));
            restaurant.setArea((String) ((HashMap)jsonRestaurant).get("area"));
            restaurant.setPostalCode((String) ((HashMap)jsonRestaurant).get("postal_code"));
            restaurant.setCountry((String) ((HashMap)jsonRestaurant).get("country"));
            restaurant.setPhone((String) ((HashMap)jsonRestaurant).get("phone"));
            restaurant.setLat((double) ((HashMap)jsonRestaurant).get("lat"));
            restaurant.setLng((double) ((HashMap)jsonRestaurant).get("lng"));
            restaurant.setPrice((int) ((HashMap)jsonRestaurant).get("price"));
            restaurant.setReserveURL((String) ((HashMap)jsonRestaurant).get("reserve_url"));
            restaurant.setMobileReserveURL((String) ((HashMap)jsonRestaurant).get("mobile_reserve_url"));
            restaurant.setImageURL((String) ((HashMap)jsonRestaurant).get("img_url"));
            restaurants.add(restaurant);
        }
        System.out.println(restaurants);
        return restaurants;
    }
}
