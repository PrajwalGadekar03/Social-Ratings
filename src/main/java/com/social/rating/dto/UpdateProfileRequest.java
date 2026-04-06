package com.social.rating.dto;

public class UpdateProfileRequest {

    private String firstName;
    private String lastName;
    private String bio;
    private String city;
    private String profilePictureUrl;
    private String phone;

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getBio() { return bio; }
    public String getCity() { return city; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public String getPhone() { return phone; }
}