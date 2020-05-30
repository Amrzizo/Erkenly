package com.amit.erkenly.network.model.request;

import com.google.gson.annotations.SerializedName;

public class CompanyRegisterationRequest {

    @SerializedName("hour_price")
    private int hourPrice;

    @SerializedName("hours_from")
    private String hoursFrom;

    @SerializedName("image")
    private String image;

    @SerializedName("password")
    private String password;

    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    @SerializedName("lng")
    private double lng;

    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("hours_to")
    private String hoursTo;

    @SerializedName("email")
    private String email;

    @SerializedName("lat")
    private double lat;

    @SerializedName("capacity")
    private int capacity;

    public CompanyRegisterationRequest(String name, String email, String mobile, String password, String passwordConfirmation, int capacity, int hourPrice, String hoursFrom, String hoursTo,String image, double lat,  double lng) {
        this.hourPrice = hourPrice;
        this.hoursFrom = hoursFrom;
        this.image = image;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.lng = lng;
        this.name = name;
        this.mobile = mobile;
        this.hoursTo = hoursTo;
        this.email = email;
        this.lat = lat;
        this.capacity = capacity;
    }

    public int getHourPrice() {
        return hourPrice;
    }

    public String getHoursFrom() {
        return hoursFrom;
    }

    public String getImage() {
        return image;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHoursTo() {
        return hoursTo;
    }

    public String getEmail() {
        return email;
    }

    public double getLat() {
        return lat;
    }

    public int getCapacity() {
        return capacity;
    }
}