package com.amazon.awslabs.cloudfoody;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Restaurant {

    private int id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String area;

    private String country;

    private String phone;

    @JsonProperty("reserve_url")
    private String reserveURL;

    @JsonProperty("mobile_reserve_url")
    private String mobileReserveURL;

    @JsonProperty("image_url")
    private String imageURL;

    @JsonProperty("postal_code")
    private String postalCode;

    private int price;

    private double lat;

    private double lng;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(id)
                .append(name)
                .append(address)
                .append(city)
                .append(state)
                .append(area)
                .append(country)
                .append(phone)
                .build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(name)
                .append(address)
                .append(city)
                .append(state)
                .append(area)
                .append(country)
                .append(phone)
                .build();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Restaurant)) {
            return false;
        }

        final Restaurant other = (Restaurant) obj;
        return new EqualsBuilder()
                .append(id, other.getId())
                .append(name, other.getName())
                .append(address, other.getAddress())
                .append(city, other.getCity())
                .append(state, other.getState())
                .append(area, other.getArea())
                .append(country, other.getCountry())
                .append(phone, other.getPhone())
                .build();
    }
}
