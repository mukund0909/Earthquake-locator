package com.example.apifirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<earthquakedata>> {
    private static final String urldata="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private earthquakeadapter adapter;
    private TextView empty;
    private ProgressBar pp;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(1,null,this);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        empty=(TextView)findViewById(R.id.empty_view);
        // Create a new {@link ArrayAdapter} of earthquakes
        adapter=new earthquakeadapter(this,new ArrayList<earthquakedata>());
        pp=(ProgressBar)findViewById(R.id.progress);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setEmptyView(empty);
        ListView list=(ListView)findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                earthquakedata data=adapter.getItem(position);
                Uri earthquakeUri = Uri.parse(data.geturl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        ConnectivityManager conMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

            i=1;

        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {

            i=2;
        }
    }
    @NonNull
    @Override
    public Loader<ArrayList<earthquakedata>> onCreateLoader(int id, @Nullable Bundle args) {
        return new earthquakeloader(this,urldata);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<earthquakedata>> loader, ArrayList<earthquakedata> data) {
        adapter.clear();
        pp.setVisibility(View.GONE);
        if(i==1)
            empty.setText("No earthquake report found");
        else if(i==2) {
            empty.setText("No Internet Connection");
            i=0;
        }
        if ((data != null)&&(!data.isEmpty())) {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<earthquakedata>> loader) {
        adapter.clear();
    }
}