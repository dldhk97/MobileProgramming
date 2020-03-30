package com.dldhk97.mgji_rotater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dldhk97.mgji_rotater.enums.Direction;
import com.dldhk97.mgji_rotater.listener.OnRotateListener;
import com.dldhk97.mgji_rotater.listener.OnLoadListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uh = new UIHandler(this);
        setupUiComponents();
    }

    private void setupUiComponents() {
        EditText userInput = findViewById(R.id.editText_userInput);
        TextView resultView = findViewById(R.id.textView_result);
        Button loadBtn = findViewById(R.id.button_load);
        Button leftBtn = findViewById(R.id.button_left);
        Button rightBtn = findViewById(R.id.button_right);

        loadBtn.setOnClickListener(new OnLoadListener(userInput, resultView));
        leftBtn.setOnClickListener(new OnRotateListener(userInput, resultView, Direction.LEFT));
        rightBtn.setOnClickListener(new OnRotateListener(userInput, resultView, Direction.RIGHT));
    }
}
