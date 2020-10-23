package com.example.family_tree;

public class Address {
    private String streetNumber;
    private String streetName;
    private String townCity;
    private String state;
    private String country;
    private String zipcode;

    public Address(String streetNumber, String streetName, String townCity, String state, String country, String zipcode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.townCity = townCity;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getTownCity() {
        return townCity;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return streetNumber + " " + streetName + ", " + townCity + ", " + state + " " + zipcode;
    }
}
