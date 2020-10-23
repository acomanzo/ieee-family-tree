package com.example.family_tree;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class Person implements Parcelable {

    private ArrayList<Person> children;
    private boolean expanded;
    private Address address;
    private int image;
    private String firstName;
    private String lastName;
    private String age;

    private int futureRelativePosition;
    private String futureRelativeRelationship;

    public Person(String firstName, String lastName, String age, Address address) {
        this(firstName, lastName, age, address, -1, null);
    }

    public Person(String firstName, String lastName, String age, Address address, int futureRelativePosition, String futureRelativeRelationship) {
        children = new ArrayList<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;

        this.futureRelativePosition = futureRelativePosition;
        this.futureRelativeRelationship = futureRelativeRelationship;
    }

    protected Person(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        age = in.readString();
        String streetNumber = in.readString();
        String streetName = in.readString();
        String townCity = in.readString();
        String state = in.readString();
        String country = in.readString();
        String zipcode = in.readString();
        Address address = new Address(streetNumber, streetName, townCity, state, country, zipcode);
        this.address = address;

        children = new ArrayList<>();

        this.futureRelativePosition = in.readInt();
        this.futureRelativeRelationship = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public void addChild(Person person) {
        children.add(person);
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public Address getAddress() {
        return address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(age);
        dest.writeString(address.getStreetNumber());
        dest.writeString(address.getStreetName());
        dest.writeString(address.getTownCity());
        dest.writeString(address.getState());
        dest.writeString(address.getCountry());
        dest.writeString(address.getZipcode());
        dest.writeInt(futureRelativePosition);
        dest.writeString(futureRelativeRelationship);
    }

    public int getFutureRelativePosition() {
        return futureRelativePosition;
    }

    public String getFutureRelativeRelationship() {
        return futureRelativeRelationship;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!firstName.equals(((Person) o).getFirstName())) return false;
        return firstName != null ? firstName.equals(person.getFirstName()) : person.getFirstName() == null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
