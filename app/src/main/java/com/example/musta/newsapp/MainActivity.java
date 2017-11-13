package com.example.musta.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    TextView totalItems;
    ListView listView;
    ArrayList<Response> newsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.list_view);
        totalItems = (TextView) findViewById(R.id.total_items);


        getSupportLoaderManager().initLoader(0, null, MainActivity.this).forceLoad();


    }


    public void updateUI(String s) {

        try {

            JSONObject root = new JSONObject(s);
            final JSONObject responsee = root.getJSONObject("response");
            int totalResults = responsee.getInt("total");
            JSONArray items = responsee.getJSONArray("results");
            newsArray = new ArrayList<>();
            totalItems.setText(String.valueOf(totalResults) + " News found.");
            for (int i = 0; i < items.length(); i++) {

                final JSONObject details = items.getJSONObject(i);

                String sectionName = details.getString("sectionName");
                String publishedDate = details.getString("webPublicationDate");
                String title = details.getString("webTitle");
                String url = details.getString("webUrl");
                JSONObject fields = details.getJSONObject("fields");
                String author;

                if (fields.has("byline")) {

                    author = fields.getString("byline");
                } else {

                    author = "No author provided";
                }

                Response respo = new Response(title, sectionName, author, url, publishedDate);

                newsArray.add(respo);


                SearchAdapter adapter = new SearchAdapter(this, R.layout.activity_results, newsArray);
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Response clickedItem = newsArray.get(position);

                        String url = clickedItem.getWebUrl();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);

                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new SearchAsyncTask(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        updateUI(s);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


}