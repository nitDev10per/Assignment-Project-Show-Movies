package com.example.letestmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements click1{

    ArrayList<list> arra=new ArrayList<>();
    //public static final MediaType JSON = MediaType.get("application/json");
   // OkHttpClient client = new OkHttpClient();


    //Button refrac;
    click1 k;
    RecyclerView recyl;

    TextInputLayout searh;
    TextInputEditText search;
    String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       // OkHttpClient client = new OkHttpClient();



        //arr.add(new list("sdd",R.drawable.ic_launcher_foreground,"drg","df",3,"dv"));
//        arr.add(new list("sdd",R.drawable.ic_launcher_foreground,"drg","df",3,"dv"));
//        arr.add(new list("sdd",R.drawable.ic_launcher_foreground,"drg","df",3,"dv"));
//        arr.add(new list("sdd",R.drawable.ic_launcher_background,"drg","df",3,"dv"));
//        arr.add(new list("sdd",R.drawable.ic_launcher_foreground,"drg","df",3,"dv"));
//        arr.add(new list("sdd",R.drawable.ic_launcher_foreground,"drg","df",3,"dv"));
//        arr.add(new list("sdd",R.drawable.ic_launcher_background,"drg","df",3,"dv"));


  //          fetchData("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1");
//-----------------------------------------------------------------------------------------------------------------------------------------
        //import android.os.AsyncTask;

// ...
      //  Button refrac=findViewById(R.id.button);
        k=this;
        searh=findViewById(R.id.textinput);
       // search=findViewById(R.id.search);
        search= (TextInputEditText) searh.getEditText();
        input=search.getText().toString();


        if (search != null) {
            search.setSingleLine(true);
            search.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    input=null;
                    input = search.getText().toString().trim(); // Trim to remove leading/trailing spaces
                    Toast.makeText(MainActivity.this,"fatching...", Toast.LENGTH_SHORT).show();
                    if(input!=null) {

                       // RecycleAdapter r = new RecycleAdapter((Context) MainActivity.this, arra, k);
                       // recyl.setAdapter(r);
                        initiateNetworkReques();
                    }
                    search.setText(null);
                    return true;
                }
                return false;
            });
        }



        initiateNetworkRequest();

//        refrac.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initiateNetworkRequest();
//                Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
//            }
//        });


//--------------------------------------------------------------------------------------------------------------------------------------------


        recyl=findViewById(R.id.recy1);
        recyl.setLayoutManager(new GridLayoutManager(this,2));
       /// RecycleAdapter r=new RecycleAdapter(this,arr,this);
       // recyl.setAdapter(r);



    }


    private class FetchDataTask extends AsyncTask<Void, Void, List<list>> {

        @Override
        protected List<list> doInBackground(Void... voids) {
            List<list> arr = new ArrayList<>();

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
                        .get()
                        .header("accept", "application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2N2Y5MjVmODdjNWMyOTU5NDdlYTRhM2IwNmJmNWY5ZCIsInN1YiI6IjY1YmEwZWM1MzNhMzc2MDE3Yjg4NjI5MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8_ilFtMhe8he5fot7fVvyPj7J_H3eFop8KZd6TSeYac")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    JSONObject jsonobj = new JSONObject(response.body().string());
                    JSONArray array = jsonobj.getJSONArray("results");

                    if (array != null) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsnobj = array.getJSONObject(i);
                            arr.add(new list(
                                    jsnobj.getString("title"),
                                    jsnobj.getString("poster_path"),
                                    jsnobj.getString("original_language"),
                                    jsnobj.getString("release_date"),
                                    jsnobj.getString("vote_average"),
                                    jsnobj.getString("overview")
                            ));
                        }
                    }
                }
            } catch (IOException | JSONException e) {
                Log.e("Network", "Exception: " + e.getMessage(), e);
            }

            return arr;
        }

        @Override
        protected void onPostExecute(List<list> arr) {
            // Update UI or perform any other post-execution tasks

            if (arr != null && !arr.isEmpty()) {
                // Display a Toast message here
                Toast.makeText(MainActivity.this, "Data fetching done", Toast.LENGTH_SHORT).show();

                // You can update your UI or adapter here with the 'arr' data
                // RecyclerView recyl = findViewById(R.id.recy1);
                 RecycleAdapter r = new RecycleAdapter((Context) MainActivity.this, (ArrayList<list>) arr, k);
                 recyl.setAdapter(r);
            }
        }
    }


    // Example of how to start the AsyncTask
    private void startFetchData() {
        new FetchDataTask().execute();
    }

// ...

    // Call this method where you want to initiate the network request
    private void initiateNetworkRequest() {
        startFetchData();
    }

//    public void post(String url) throws IOException {
//       // RequestBody body = RequestBody.create(json, JSON);
//        try {
//            Request request = new Request.Builder()
//                    .url("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1")
//                    //.post(body)
//                    .get()
//                    .header("accept", "application/json")
//                    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NjNjMjYwMjc5OTA4YmUxMTNhZTQyZjFiNDNkNGI0ZSIsInN1YiI6IjY1YmEwZWM1MzNhMzc2MDE3Yjg4NjI5MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.K9hDIc4oXmMKB2erNeiW7WjRwxlWiDjCK3lUyHSqLdM")
//                    .build();
//            //OkHttpClient client;
//
//            try (Response response = client.newCall(request).execute()) {
//                JSONObject jsonobj = new JSONObject(response.body().string());
//                JSONArray array = jsonobj.getJSONArray("results");
//
//                if(array != null){
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject jsnobj = array.getJSONObject(i);
//                    arr.add(new list(
//                            jsnobj.getString("title"),
//                            jsnobj.getString("poster_path"),
//                            jsnobj.getString("original_language"),
//                            jsnobj.getString("release_date"),
//                            jsnobj.getString("vote_averag"),
//                            jsnobj.getString("overview")
//                    ));
//                }
//                }
//
//
//                // return response.body().string();
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onclick(list l) {
        //search= (TextInputEditText) searh.getEditText();
        //input=search.getText().toString();
        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        intent.putExtra("info",l.getInfo());
        intent.putExtra("overview",l.getOverView());
        intent.putExtra("ratting",l.getRating());
        intent.putExtra("poster",l.getPoster());
        intent.putExtra("title",l.getTitle());
        intent.putExtra("relese",l.getReleseD());


        Toast.makeText(MainActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private class FetchDataTas extends AsyncTask<Void, Void, List<list>> {

        @Override
        protected List<list> doInBackground(Void... voids) {
            List<list> arr = new ArrayList<>();

            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://api.themoviedb.org/3/search/movie?include_adult=false&language=en-US&page=1&query=" + input)
                        .get()
                        .header("accept", "application/json")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2N2Y5MjVmODdjNWMyOTU5NDdlYTRhM2IwNmJmNWY5ZCIsInN1YiI6IjY1YmEwZWM1MzNhMzc2MDE3Yjg4NjI5MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8_ilFtMhe8he5fot7fVvyPj7J_H3eFop8KZd6TSeYac")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    JSONObject jsonobj = new JSONObject(response.body().string());
                    JSONArray array = jsonobj.getJSONArray("results");

                    if (array != null) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsnobj = array.getJSONObject(i);
                            arr.add(new list(
                                    jsnobj.getString("title"),
                                    jsnobj.getString("poster_path"),
                                    jsnobj.getString("original_language"),
                                    jsnobj.getString("release_date"),
                                    jsnobj.getString("vote_average"),
                                    jsnobj.getString("overview")
                            ));
                        }
                    }
                }
            } catch (IOException | JSONException e) {
                Log.e("Network", "Exception: " + e.getMessage(), e);
            }

            return arr;
        }

        @Override
        protected void onPostExecute(List<list> arr) {
            // Update UI or perform any other post-execution tasks

            if (arr != null && !arr.isEmpty()) {
                // Display a Toast message here
                Toast.makeText(MainActivity.this, "Data fetching done", Toast.LENGTH_SHORT).show();

                // You can update your UI or adapter here with the 'arr' data
                // RecyclerView recyl = findViewById(R.id.recy1);
                RecycleAdapter r = new RecycleAdapter((Context) MainActivity.this, (ArrayList<list>) arr, k);
                recyl.setAdapter(r);
            }
        }
    }


    // Example of how to start the AsyncTask
    private void startFetchDat() {
        new FetchDataTas().execute();
    }

// ...

    // Call this method where you want to initiate the network request
    private void initiateNetworkReques() {
        startFetchDat();
    }


}          // ...


