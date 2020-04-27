package com.dldhk97.mgji_sensor.adapters;

import android.content.Context;
import android.hardware.Sensor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dldhk97.mgji_sensor.utilities.UIHandler;
import com.dldhk97.mgji_sensor.viewholders.SensorRecyclerViewHolder;
import com.dldhk97.mgji_sensor.R;

import java.util.ArrayList;

public class SensorRecyclerAdapter extends RecyclerView.Adapter<SensorRecyclerViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Sensor> sensors;

    public SensorRecyclerAdapter(Context context, ArrayList<Sensor> sensors){
        inflater = LayoutInflater.from(context);
        this.sensors = sensors;
    }

    @NonNull
    @Override
    public SensorRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try{
            View itemView = inflater.inflate(R.layout.item_sensor, parent, false);
            return new SensorRecyclerViewHolder(itemView, this);
        }
        catch (Exception e){
            UIHandler.getInstance().showAlert("[CafeteriaRecyclerViewHolder.onCreateViewHolder]\n" + e.getMessage());
        }
        return null;
    }

    int test = 0;
    @Override
    public void onBindViewHolder(@NonNull SensorRecyclerViewHolder holder, int position) {
        try{
            holder.onBind(sensors.get(position));
            Log.d("a",String.valueOf(test) + "pos : "+ String.valueOf(position));
            test++;
        }
        catch(Exception e){
            UIHandler.getInstance().showToast("[Adapter.onBindViewHolder]\n" + e.getMessage());
        }

    }


    @Override
    public int getItemCount() {
        try{
            if(sensors != null){
                return sensors.size();
            }
        }
        catch (Exception e){
            UIHandler.getInstance().showToast("[Adapter.getItemCount]\n" + e.getMessage());
        }

        return 0;
    }
}
