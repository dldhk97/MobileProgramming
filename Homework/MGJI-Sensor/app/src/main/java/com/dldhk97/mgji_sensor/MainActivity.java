package com.dldhk97.mgji_sensor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.os.Bundle;

import com.dldhk97.mgji_sensor.adapters.SensorRecyclerAdapter;
import com.dldhk97.mgji_sensor.logics.SensorController;
import com.dldhk97.mgji_sensor.utilities.UIHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static MainActivity _Instance;
    public static SensorRecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _Instance = this;
        UIHandler uh = new UIHandler(this);

        setupUiComponents();
    }

    public static MainActivity getInstance(){
        if(_Instance != null)
            return _Instance;
        return null;
    }

    // UI 컴포넌트 설정
    public void setupUiComponents(){
        try{
            setupRecyclerView();
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert( "MainActivity.setupSpinner" + e.getMessage());
        }
    }


    // 리사이클러 뷰 설정
    private void setupRecyclerView() throws Exception{
        ArrayList<Sensor> sensors = SensorController.getSensorList();

        RecyclerView recyclerView_menuView = findViewById(R.id.recyclerView_sensorView);
        recyclerAdapter = new SensorRecyclerAdapter(this, sensors);
        recyclerView_menuView.setItemViewCacheSize(sensors.size());                     // 이거 해줘야지 최상단/하단 데이터가 저장됨.
        recyclerView_menuView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView_menuView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView_menuView.addItemDecoration(dividerItemDecoration);

        recyclerView_menuView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter.notifyDataSetChanged();
    }
}
