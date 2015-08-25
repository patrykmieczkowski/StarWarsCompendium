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
        String name = mainObject.get("name").toString();
        String birthYear = mainObject.get("birth_year").toString();
        String eyeColor = mainObject.get("eye_color").toString();
        String gender = mainObject.get("gender").toString();
        String hairColor = mainObject.get("hair_color").toString();
        String height = mainObject.get("height").toString();
        String mass = mainObject.get("mass").toString();
        String skinColor = mainObject.get("skin_color").toString();
        String homeworld = mainObject.get("homeworld").toString();

        People people = new People(name, birthYear, eyeColor, gender, hairColor, height, mass, skinColor, homeworld);

        Log.e("PeopleDeserializer", name + " " + birthYear + " " + gender);
        

        return people;
    }
}
