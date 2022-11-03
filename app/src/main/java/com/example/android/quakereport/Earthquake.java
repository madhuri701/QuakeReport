package com.example.android.quakereport;

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private String mDate;
    private long mtimeInmillisecond;
    private String mUrl;
    public Earthquake(Double magnitude, String location, long timeInmillisecond, String url)
    {
        mMagnitude=magnitude;
        mLocation=location;
        mtimeInmillisecond=timeInmillisecond;
        mUrl = url;
    }
    public Double getMagnitude()
    {
        return mMagnitude;
    }
    public String getLocation()
    {
        return mLocation;
    }
    public String getDate()
    {
        return mDate;
    }
    public long gettingInMillisecond()
    {
return mtimeInmillisecond;
    }
    public String getUrl()
    {
        return mUrl;
    }
}

