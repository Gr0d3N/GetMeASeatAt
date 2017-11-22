package com.amazon.awslabs.cloudfoody;

public class Restaurant {

    private String name, address, city, state;
    private String area, country, phone, reserveURL;
    private String mobileReserveURL, imageURL, postalCode;
    private int restId, price;
    private double lat, lng;


    public void setName(String name) {
        this.name = name;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setMobileReserveURL(String mobileReserveURL) {
        this.mobileReserveURL = mobileReserveURL;
    }

    public void setReserveURL(String reserveURL) {
        this.reserveURL = reserveURL;
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
