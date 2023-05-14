package com.example.studenthousing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Property {
    private static String external_id;
    private static String city;
    private static int rent;
    private static int area_sqm;
    private static String cover_image_url;
    private static String property_type;
    private static String gender;
    private static String page_title;
    private static boolean is_room_active;

    // data uit database halen?
    public static String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public static String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public static int getArea_sqm() {
        return area_sqm;
    }

    public void setArea_sqm(int area_sqm) {
        this.area_sqm = area_sqm;
    }

    public static String getCover_image_url() {
        return cover_image_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public static String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public static String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public static boolean getIs_room_active() {
        return is_room_active;
    }

    public void setIs_room_active(boolean is_room_active) {
        this.is_room_active = is_room_active;
    }

    public Property() {
    }

    public Property(String external_id, String city, int area_sqm, int rent, String cover_image_url,
                    String property_type, String gender, String page_title, boolean is_room_active) {
            this.external_id = external_id;
            this.area_sqm = area_sqm;
            this.city = city;
            this.rent = rent;
            this.cover_image_url = cover_image_url;
            this.property_type = property_type;
            this.gender = gender;
            this.page_title = page_title;
            this.is_room_active = is_room_active;

            setAvailable(is_room_active);
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return rent == property.rent && area_sqm == property.area_sqm && is_room_active == property.is_room_active && Objects.equals(external_id, property.external_id) && Objects.equals(city, property.city) && Objects.equals(cover_image_url, property.cover_image_url) && Objects.equals(property_type, property.property_type) && Objects.equals(gender, property.gender) && Objects.equals(page_title, property.page_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(external_id, city, rent, area_sqm, cover_image_url, property_type, gender, page_title, is_room_active);
    }

    @JsonIgnore
        public boolean Is_room_active() {
            return is_room_active;
        }

        public void setAvailable(boolean setAvailable) {
            is_room_active = setAvailable;
        }


}
