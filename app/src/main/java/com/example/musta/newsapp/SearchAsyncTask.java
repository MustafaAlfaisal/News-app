package com.example.musta.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by musta on 11/12/2017.
 */

public class SearchAsyncTask extends android.support.v4.content.AsyncTaskLoader<String> {


    public SearchAsyncTask(Context context) {
        super(context);

    }

    @Override
    public String loadInBackground() {

        StringBuilder JsonData = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {

            String urlString = "https://content.guardianapis.com/search?api-key=e25c0867-ec24-4111-ade2-3fb64bc59796&show-fields=byline,thumbnail";
            Log.v("NETWORK_URL", urlString);


            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                JsonData.append(line);
                line = reader.readLine();
            }
            Log.v("AsyncTask", "Connected" + httpURLConnection.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("AsyncTask", e.getMessage());
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return JsonData.toString();
    }
}