package com.mieczkowskidev.starwarscompendium;


import com.google.gson.JsonElement;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Patryk Mieczkowski on 2015-08-25.
 */
public interface StarWarsWebService {


    /**
     * Method to return people on requested number
     * @param number - int, from 1+
     * @param callback - JsonElement response
     */
    @GET("/people/{number}/")
    void getPeople(@Path("number") String number,
                   Callback<JsonElement> callback);
}
