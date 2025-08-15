package com.example.personalproject.mainPackage.gpsutills;


public class GPSConstants {
    public static final double DISTANCE_VALIDATION_RANGE = 100;           //100 meters.
    public static final double EARTH_RADIUS = 3958.75;
    public static final int METER_CONVERSION = 1609;

    //location updates
    public static final int MAX_RESULTS = 1;
//	public static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 100;            // 10 meters.
//	public static final long MIN_TIME_BW_UPDATES             = 1000 * 60 * 1; // 1 minute.

    //timer task
    public static final int TIMER_TASK_DELAY = 1000;      //1 second.
    public static final int TIMER_TASK_PERIOD = 30 * 1000;     //2 seconds.

    //location updates
    public static final long INTERVAL = 10 * 1000;  //3 seconds.
    public static final long FASTEST_INTERVAL = 1000;  //3 seconds.

}
