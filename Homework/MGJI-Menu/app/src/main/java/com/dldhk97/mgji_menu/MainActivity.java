package com.dldhk97.mgji_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dldhk97.mgji_menu.adapters.SensorRecyclerAdapter;
import com.dldhk97.mgji_menu.enums.ThemeType;
import com.dldhk97.mgji_menu.logics.SensorController;
import com.dldhk97.mgji_menu.ui.ColorHandler;
import com.dldhk97.mgji_menu.ui.UIHandler;

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
        ColorHandler.getInstance();

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
        recyclerView_menuView.setItemViewCacheSize(sensors.size());                     // 리사이클러뷰 기본 캐쉬값이 5라서, 그대로 놔두면 스크롤시 데이터 이상하게 바뀜
        recyclerView_menuView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView_menuView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView_menuView.addItemDecoration(dividerItemDecoration);

        recyclerView_menuView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_red:
                ColorHandler.getInstance().setTheme(ThemeType.RED);
                UIHandler.getInstance().showToast("테마가 " + ColorHandler.getInstance().getCurrentTheme().toString() + "로 변경되었습니다.");
                return true;
            case R.id.action_green:
                ColorHandler.getInstance().setTheme(ThemeType.GREEN);
                UIHandler.getInstance().showToast("테마가 " + ColorHandler.getInstance().getCurrentTheme().toString() + "로 변경되었습니다.");
                return true;
            case R.id.action_blue:
                ColorHandler.getInstance().setTheme(ThemeType.BLUE);
                UIHandler.getInstance().showToast("테마가 " + ColorHandler.getInstance().getCurrentTheme().toString() + "로 변경되었습니다.");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
