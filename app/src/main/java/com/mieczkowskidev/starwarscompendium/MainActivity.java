package com.mieczkowskidev.starwarscompendium;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mieczkowskidev.starwarscompendium.Deserializers.PeopleDeserializer;
import com.mieczkowskidev.starwarscompendium.Objects.People;

import java.lang.reflect.Type;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button button;
    private TextView nameText, dateText, heightText, massText, genderText, eyesText, hairText, skinText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random r = new Random();
                int myRandom = r.nextInt(80 - 1) + 1;
                if (isOnline()) {
                    getPeopleFromSWAPI(String.valueOf(myRandom));
                } else {
                    dateText.setText("Check your internet connection and try again!");
                }
            }
        });
    }

    private void getViews() {

        button = (Button) findViewById(R.id.button);
        nameText = (TextView) findViewById(R.id.people_name);
        dateText = (TextView) findViewById(R.id.people_date);
        heightText = (TextView) findViewById(R.id.people_height);
        massText = (TextView) findViewById(R.id.people_mass);
        genderText = (TextView) findViewById(R.id.people_gender);
        eyesText = (TextView) findViewById(R.id.people_eyes);
        hairText = (TextView) findViewById(R.id.people_hair);
        skinText = (TextView) findViewById(R.id.people_skin);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getPeopleFromSWAPI(String number) {
        Log.d(TAG, "getPeopleFromSWAPI()");

        RestClient restClient;
        StarWarsWebService starWarsWebService;

        restClient = new RestClient();
        starWarsWebService = restClient.getRestAdapter().create(StarWarsWebService.class);

        starWarsWebService.getPeople(number, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Log.d(TAG, "starWarsWebService.getPeople() - success! " + response.getUrl());

                Type type = new TypeToken<People>() {
                }.getType();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(type, new PeopleDeserializer());
                Gson gson = gsonBuilder.create();
                GsonConverter gsonConverter = new GsonConverter(gson);
                People people = null;
                gson.fromJson(jsonElement, type);

                try {
                    people = (People) gsonConverter.fromBody(response.getBody(), type);
                } catch (ConversionException e) {
                    e.printStackTrace();
                } finally {
                    setTextView(people);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "starWarsWebService.getPeople() - failure! " + error.getUrl());

            }
        });

    }

    private void setTextView(People people) {

        nameText.setText(people.getName());

        if (people.getBirthYear().contains("unknown")) {
            dateText.setVisibility(View.INVISIBLE);
        } else {
            if (dateText.getVisibility() == View.INVISIBLE) {
                dateText.setVisibility(View.VISIBLE);
            }
            dateText.setText(people.getBirthYear());
        }

        if (people.getHeight().contains("unknown")) {
            heightText.setVisibility(View.GONE);
        } else {
            if (heightText.getVisibility() == View.GONE) {
                heightText.setVisibility(View.VISIBLE);
            }
            heightText.setText("Height: " + people.getHeight());
        }

        if (people.getMass().contains("unknown")) {
            massText.setVisibility(View.GONE);
        } else {
            if (massText.getVisibility() == View.GONE) {
                massText.setVisibility(View.VISIBLE);
            }
            massText.setText("Mass: " + people.getMass());
        }

        genderText.setText("Gender: " + people.getGender());

        eyesText.setText("Eyes: " + people.getEyeColor());

        hairText.setText("Hair: " + people.getHairColor());

        if (people.getSkinColor().contains("unknown")) {
            skinText.setVisibility(View.GONE);
        } else {
            if (skinText.getVisibility() == View.GONE) {
                skinText.setVisibility(View.VISIBLE);
            }
            skinText.setText("Skin: " + people.getSkinColor());
        }

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
