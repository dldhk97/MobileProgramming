package com.dldhk97.mgji_recy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.dldhk97.mgji_recy.adapters.CafeteriaRecyclerAdapter;
import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.listeners.OnAboutListener;
import com.dldhk97.mgji_recy.listeners.OnCafeteriaTypeSelectedListener;
import com.dldhk97.mgji_recy.listeners.OnMoreListener;

public class MainActivity extends AppCompatActivity {
    public static MainActivity _Instance;
    public CafeteriaRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _Instance = this;
        UIHandler uh = new UIHandler(this);

        // UI 컴포넌트 설정
        setupListeners();
        setupSpinner();
        setupRecyclerView();
    }

    public static MainActivity getInstance(){
        if(_Instance != null)
            return _Instance;
        return null;
    }

    // 버튼 리스너 설정
    private void setupListeners(){
        // 더보기 리스너 설정
        Button button_more = findViewById(R.id.button_more);
        button_more.setOnClickListener(new OnMoreListener());

        // 대하여 리스너 설정
        Button button_about = findViewById(R.id.button_about);
        button_about.setOnClickListener(new OnAboutListener());
    }

    // 식당 타입 스피너 리스너 설정
    private void setupSpinner(){
        Spinner spinner_cafeteriaType = findViewById(R.id.spinner_cafeteriaType);
        String[] items = CafeteriaType.getStringArray();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, items);

        spinner_cafeteriaType.setAdapter(adapter);
        spinner_cafeteriaType.setOnItemSelectedListener(new OnCafeteriaTypeSelectedListener());
    }

    // 리사이클러 뷰 설정
    private void setupRecyclerView(){
        RecyclerView recyclerView_menuView = findViewById(R.id.recyclerView_menuView);
        recyclerAdapter = new CafeteriaRecyclerAdapter(this);
        recyclerView_menuView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView_menuView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView_menuView.addItemDecoration(dividerItemDecoration);

        recyclerView_menuView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter.notifyDataSetChanged();
    }
}
