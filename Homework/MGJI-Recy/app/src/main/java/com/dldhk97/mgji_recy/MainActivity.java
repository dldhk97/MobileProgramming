package com.dldhk97.mgji_recy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dldhk97.mgji_recy.listener.OnCafeteriaTypeSelectedListener;
import com.dldhk97.mgji_recy.listener.OnRefreshListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uh = new UIHandler(this);
        setupUiComponents();
    }

    // UI 컴포넌트 설정
    private void setupUiComponents() {
        setupListeners();
        setupSpinner();
    }

    private void setupListeners(){
        Button button_refresh = findViewById(R.id.button_refresh);

        button_refresh.setOnClickListener(new OnRefreshListener());
    }

    private void setupSpinner(){
        Spinner spinner_cafeteriaType = findViewById(R.id.spinner_cafeteriaType);
        String[] items = getResources().getStringArray(R.array.cafeteriaType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, items);

        spinner_cafeteriaType.setAdapter(adapter);
        spinner_cafeteriaType.setOnItemSelectedListener(new OnCafeteriaTypeSelectedListener());
    }
}
