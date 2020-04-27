package com.dldhk97.mgji_sensor.viewholders;

import android.hardware.Sensor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dldhk97.mgji_sensor.MainActivity;
import com.dldhk97.mgji_sensor.adapters.SensorRecyclerAdapter;
import com.dldhk97.mgji_sensor.utilities.UIHandler;
import com.dldhk97.mgji_sensor.R;

public class SensorRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView_icon;
    private TextView textView_sensorName;
    private TextView textView_sensorType;
    private ConstraintLayout item_cl;

    private boolean toggle = false;

    public SensorRecyclerViewHolder(@NonNull View itemView, SensorRecyclerAdapter adapter) {
        super(itemView);
        try{
            this.imageView_icon = itemView.findViewById(R.id.imageView_icon);
            this.textView_sensorName = itemView.findViewById(R.id.item_textView_sensorName);
            this.textView_sensorType = itemView.findViewById(R.id.item_textView_sensorType);
            this.item_cl = itemView.findViewById(R.id.item_cl);

            // 클릭 리스너 설정
            itemView.setOnClickListener(this);
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[CafeteriaRecyclerViewHolder.constructor]" + e.getMessage());
        }
    }

    public void onBind(Sensor sensor)throws Exception{
        String sensorName, sensorType;
        if(sensor == null){
            sensorName = "알 수 없음";
            sensorType = "알 수 없음";
        }
        else{
            sensorName = sensor.getName();
            sensorType = sensor.getStringType();
        }
        // 아이콘 설정
        int imageId = R.drawable.unknown;
        switch(sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                imageId = R.drawable.accelerometer;
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                imageId = R.drawable.temperature;
                break;
            case Sensor.TYPE_GRAVITY:
                imageId = R.drawable.gravity;
                break;
            case Sensor.TYPE_GYROSCOPE:
                imageId = R.drawable.gyroscope;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                imageId = R.drawable.magnetic;
                break;
            case Sensor.TYPE_PROXIMITY:
                imageId = R.drawable.proximity;
                break;
            case Sensor.TYPE_LIGHT:
                imageId = R.drawable.light;
                break;
            case Sensor.TYPE_PRESSURE:
                imageId = R.drawable.pressure;
                break;
        };
        imageView_icon.setImageResource(imageId);

        // 센서명 표시
        textView_sensorName.setText(sensorName);

        // 센서타입 표시
        textView_sensorType.setText(sensorType);

    }

    @Override
    public void onClick(View view) {
        try{
            int pos = getLayoutPosition();
            if(pos != RecyclerView.NO_POSITION){
                if(toggle){
                    textView_sensorName.setTextColor(MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorName_color));
                    textView_sensorType.setTextColor(MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorType_color));
                    item_cl.setBackgroundColor(MainActivity.getInstance().getResources().getColor(R.color.item_background_color));
                }
                else{
                    textView_sensorName.setTextColor(MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorName_color_alt));
                    textView_sensorType.setTextColor(MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorType_color_alt));
                    item_cl.setBackgroundColor(MainActivity.getInstance().getResources().getColor(R.color.item_background_color_alt));
                }
                toggle = !toggle;
            }
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[SensorRecyclerViewHolder.onClick]" + e.getMessage());
        }
    }
}
