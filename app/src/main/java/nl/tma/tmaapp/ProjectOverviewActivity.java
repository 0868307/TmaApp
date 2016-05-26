package nl.tma.tmaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProjectOverviewActivity extends AppCompatActivity {
    ListView myList;

    private String responseData;

    static class GitUser {
        String username;
        int points;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_project_overview);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://145.24.222.137:8092/ranking")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                 responseData = response.body().string();


            }
        });
        String[] items = {responseData};
        Log.d("Debug", responseData);
        myList = (ListView) findViewById(R.id.projectListView);
        myList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));





    }



    }

