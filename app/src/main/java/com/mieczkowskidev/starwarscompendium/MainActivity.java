package com.mieczkowskidev.starwarscompendium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPeopleFromSWAPI("1");
            }
        });
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

    private void getPeopleFromSWAPI(String number){
        Log.d(TAG, "getPeopleFromSWAPI()");

        RestClient restClient;
        StarWarsWebService starWarsWebService;

        restClient = new RestClient();
        starWarsWebService = restClient.getRestAdapter().create(StarWarsWebService.class);

        starWarsWebService.getPeople(number, new Callback<JsonElement>() {
            @Override
            public void success(JsonElement jsonElement, Response response) {
                Log.d(TAG, "starWarsWebService.getPeople() - success! " + response.getUrl());

                JsonObject mainObject = jsonElement.getAsJsonObject();
                String name = mainObject.get("name").toString();

                Log.d(TAG, "Your object: " + name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "starWarsWebService.getPeople() - failure! " + error.getUrl());

            }
        });

    }
}
