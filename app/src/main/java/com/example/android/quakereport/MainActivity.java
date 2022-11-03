package com.example.android.quakereport;

import androidx.appcompat.app.AppCompatActivity;
import android.app.LoaderManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Loader;

import android.net.ConnectivityManager;
import android.net.Network;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public abstract class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    private EarthquakeAdapter mAdapter;
    private TextView mEmptyStateTextView;
    //url of usgs data
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private static final int EARTHQUAKE_LOADED_ID=1;
    //  public static final String LOG_TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(
                this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        mEmptyStateTextView=(TextView)findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mEmptyStateTextView);


        earthquakeListView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                //convert the String URL into a URI object (to pass intent connection)
                 Uri earthquakeuri = Uri.parse(currentEarthquake.getUrl());
                //create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeuri);
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        }));
        //set a refrence to the loadManager,in order to interact with loaders.
        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADED_ID, null, this);
    }

@Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle)
{
    return new EarthquakeLoader (this,USGS_REQUEST_URL);
}
@Override
    public void onLoadFinished(Loader<List<Earthquake>>loader,List<Earthquake>earthquakes)
{
    //Set empty state text view
    mEmptyStateTextView.setText(R.string.no_earthquake);
    mAdapter.clear();
    //if there is valid list of {@link Earthuake}s,then add them to the adapter's
    //data set, this will trigger the listview to update
    if(earthquakes!=null && !earthquakes.isEmpty())
    {
        mAdapter.addAll(earthquakes);
    }

}
 public void onLoaderReset(Loader<List<Earthquake>>loader)
{
    mAdapter.clear();
}
}