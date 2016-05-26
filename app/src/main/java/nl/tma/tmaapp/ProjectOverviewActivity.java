package nl.tma.tmaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

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

public class ProjectOverviewActivity extends AppCompatActivity {
    ListView myList;

    private String responseData;
    private List<String> allNames = new ArrayList<String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_project_overview);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://145.24.222.137:8090/ranking")
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

