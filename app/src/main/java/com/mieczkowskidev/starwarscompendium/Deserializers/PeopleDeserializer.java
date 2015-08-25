package com.mieczkowskidev.starwarscompendium.Deserializers;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mieczkowskidev.starwarscompendium.Objects.People;

import java.lang.reflect.Type;

/**
 * Created by Patryk Mieczkowski on 2015-08-25.
 */
public class PeopleDeserializer implements JsonDeserializer<People> {

    @Override
    public People deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject mainObject = json.getAsJsonObject();
        String name = mainObject.get("name").toString().replaceAll("\"","");
        String birthYear = mainObject.get("birth_year").toString().replaceAll("\"", "").replaceAll("BBY", " BBY").replaceAll("ABY", " ABY").replaceAll("unknown","unknown birth date");
        String eyeColor = mainObject.get("eye_color").toString().replaceAll("\"", "");
        String gender = mainObject.get("gender").toString().replaceAll("\"", "");
        String hairColor = mainObject.get("hair_color").toString().replaceAll("\"", "");
        String height = mainObject.get("height").toString().replaceAll("\"", "") + " cm";
        String mass = mainObject.get("mass").toString().replaceAll("\"", "") + " kg";
        String skinColor = mainObject.get("skin_color").toString().replaceAll("\"", "");
        String homeworld = mainObject.get("homeworld").toString().replaceAll("\"","");

        People people = new People(name, birthYear, eyeColor, gender, hairColor, height, mass, skinColor, homeworld);

        Log.d("PeopleDeserializer", name + " " + birthYear + " " + gender);


        return people;
    }
}
