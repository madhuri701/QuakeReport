package com.example.android.quakereport;

import android.content.Context;
import android.content.AsyncTaskLoader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private String mUrl;
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl =url;
    }

        @NonNull
        @Override
        protected void onStartLoading()  {
            forceLoad();
        }

        @Nullable
        @Override
        public List<Earthquake> loadInBackground() {
            if(mUrl==null)
            {
                return null;
            }


            //network request
            List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
            return earthquakes;
        }
    }

