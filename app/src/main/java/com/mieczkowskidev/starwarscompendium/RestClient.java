package com.mieczkowskidev.starwarscompendium;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Patryk Mieczkowski on 2015-08-25.
 */
public class RestClient {

    private RestAdapter restAdapter;

    public RestClient() {
        Gson gson = new GsonBuilder().create();

        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Config.StarWarsServiceURL)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    public RestAdapter getRestAdapter(){

        return restAdapter;
    }
}
