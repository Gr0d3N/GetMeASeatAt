package com.amazon.asksdk.cloudfoody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant extends OpenTable {

    private String name, address, city, state;
    private String area, country, phone, reserveURL;
    private String mobileReserveURL, imageURL, postalCode;
    private int restId, price;
    private double lat, lng;

    public Restaurant(String name) throws IOException {
        this.setName(name);
        this.setInfo(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String name) throws IOException {
        HashMap restaurant = getRestaurantByName(name);

        this.restId = (int) restaurant.get("id");
        this.address = (String)restaurant.get("address");
        this.city = (String)restaurant.get("city");
        this.state = (String)restaurant.get("state");
        this.area = (String)restaurant.get("area");
        this.postalCode = (String)restaurant.get("postal_code");
        this.country = (String)restaurant.get("country");
        this.phone = (String)restaurant.get("phone");
        this.lat = (double) restaurant.get("lat");
        this.lng = (double) restaurant.get("lng");
        this.price = (int) restaurant.get("price");
        this.reserveURL = (String)restaurant.get("reserve_url");
        this.mobileReserveURL = (String)restaurant.get("mobile_reserve_url");
        this.imageURL = (String)restaurant.get("image_url");
    }

    public String getName() {
        return this.name;
    }

    public int getRestId() {
        return this.restId;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getArea() {
        return area;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getPhone() {
        return phone;
    }

    public int getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getMobileReserveURL() {
        return mobileReserveURL;
    }

    public String getReserveURL() {
        return reserveURL;
    }
}
