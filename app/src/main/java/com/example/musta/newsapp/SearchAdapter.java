package com.example.musta.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by musta on 11/12/2017.
 */

public class SearchAdapter extends ArrayAdapter<Response> {
    private Context context;
    private int resources;
    private ArrayList<Response> objects;

    public SearchAdapter(MainActivity context, int resource, ArrayList<Response> objects) {
        super(context, resource, objects);
        this.resources = resource;
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resources, null);
        }

        Response respo = objects.get(position);
        TextView sectionName = convertView.findViewById(R.id.section_name);
        TextView title = convertView.findViewById(R.id.title);
        TextView author = convertView.findViewById(R.id.author);
        TextView publishedDate = convertView.findViewById(R.id.published_date);
        title.setText(String.valueOf(respo.getWebTitle()));
        author.setText(String.valueOf(respo.getAuthor()));
        sectionName.setText(String.valueOf(respo.getSectionName()));
        publishedDate.setText(String.valueOf(respo.getWebPublicationDate()));
        return convertView;
    }
}
