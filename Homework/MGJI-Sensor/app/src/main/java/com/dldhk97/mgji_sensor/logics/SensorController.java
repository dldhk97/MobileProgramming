package com.dldhk97.mgji_sensor.logics;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.dldhk97.mgji_sensor.MainActivity;

import java.util.ArrayList;

public class SensorController {
    public static ArrayList<Sensor> getSensorList(){
        SensorManager sensorManager = (SensorManager)MainActivity.getInstance().getSystemService(Context.SENSOR_SERVICE);
        return new ArrayList(sensorManager.getSensorList(Sensor.TYPE_ALL));
    }
}
