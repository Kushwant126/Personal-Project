package com.example.personalproject.mainPackage.gpsutills;


import com.example.personalproject.mainPackage.gpsutills.common.GPSErrorCode;

public interface GPSCallback
{
    public abstract void gotGpsValidationResponse(Object response, GPSErrorCode code);
}
