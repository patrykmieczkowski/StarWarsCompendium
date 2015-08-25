package com.mieczkowskidev.starwarscompendium.Objects;

/**
 * Created by Patryk Mieczkowski on 2015-08-25.
 */
public class People {

    private String name; //The name of this person

    private String birthYear; //The birth year of the person, using the in-universe standard of BBY or ABY - Before the Battle of Yavin or After the Battle of Yavin. The Battle of Yavin is a battle that occurs at the end of Star Wars episode IV: A New Hope.

    private String eyeColor; //The eye color of this person. Will be "unknown" if not known or "n/a" if the person does not have an eye.

    private String gender; //The gender of this person. Either "Male", "Female" or "unknown", "n/a" if the person does not have a gender.

    private String hairColor; //The hair color of this person. Will be "unknown" if not known or "n/a" if the person does not have hair.

    private String height; //The height of the person in centimeters.

    private String mass; //The mass of the person in kilograms.

    private String skinColor; //The skin color of this person.

    private String homeworld; //The URL of a planet resource, a planet that this person was born on or inhabits.

    public People(String name, String birthYear, String eyeColor, String gender, String hairColor, String height, String mass, String skinColor, String homeworld) {
        this.name = name;
        this.birthYear = birthYear;
        this.eyeColor = eyeColor;
        this.gender = gender;
        this.hairColor = hairColor;
        this.height = height;
        this.mass = mass;
        this.skinColor = skinColor;
        this.homeworld = homeworld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }
}
