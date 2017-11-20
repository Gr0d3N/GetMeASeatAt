package com.amazon.asksdk.cloudfoody;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OpenTable {

    private static final String BASE_URL = "http://opentable.herokuapp.com/api/";

    /*
        The method establishes a connection and returns a url object
     */
    public static URLConnection createConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        return url.openConnection();
    }


    /*
        The method takes a restaurant name as an arguement and returns back a hash with
        all the info regarding that restaurant
     */
    public static HashMap getRestaurantByName(String name) throws IOException {
        String fullURL = BASE_URL + "restaurants?name=" + name;
        URLConnection connection = createConnection(fullURL);
        return new ObjectMapper().readValue(new InputStreamReader((connection.getInputStream())), HashMap.class);

    }

    /*
        The method takes a city name as an arguement and returns back a hash with
        all the restaurants in that city and their info as well
     */
    public static HashMap getRestaurantsInCity(String city) throws IOException {
        String fullURL = BASE_URL + "restaurants?city=" + city;
        URLConnection connection = createConnection(fullURL);
        return new ObjectMapper().readValue(new InputStreamReader((connection.getInputStream())), HashMap.class);
    }
}
