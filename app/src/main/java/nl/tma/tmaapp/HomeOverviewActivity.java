package nl.tma.tmaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeOverviewActivity extends AppCompatActivity {
    ListView myList;

    private String responseData;
    private List<String> allNames = new ArrayList<String>();
    SharedPreferences sharedpreferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        setContentView(R.layout.activity_project_overview);
        Button go_to_profile = (Button) findViewById(R.id.toProfile);
        go_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeOverviewActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        String Weight = sharedpreferences.getString("auth_token", null);
        Log.d("app", Weight);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("Authorization", Weight)
                .url("http://10.0.2.2:8080/ranking")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String responseData = response.body().string();
                    JSONArray json = new JSONArray(responseData);
                    for (int i=0; i<json.length(); i++) {
                        JSONObject actor = json.getJSONObject(i);
                        String name = actor.getString("points");
                        allNames.add(name);

                    }
                } catch (JSONException e) {

                }


            }
        });
        myList = (ListView) findViewById(R.id.projectListView);
        myList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, allNames));





    }



    }

