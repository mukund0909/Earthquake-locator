package com.example.apifirstapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class earthquakeloader extends AsyncTaskLoader<ArrayList<earthquakedata>> {
    private String strings;

    public earthquakeloader(Context context,String url) {
        super(context);
        strings=url;
    }

    @Override
    public ArrayList<earthquakedata> loadInBackground() {
        if(strings==null)
            return null;
        ArrayList<earthquakedata> earthquakes=QueryUtils.extractEarthquakes(strings);
        return earthquakes;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
