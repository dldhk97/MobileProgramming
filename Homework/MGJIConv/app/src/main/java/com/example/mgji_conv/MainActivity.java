package com.example.mgji_conv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uh = new UIHandler(this);

        setupUiComponents();
    }

    private void setupUiComponents() {
        Button conversionBtn = findViewById(R.id.button_conversion);
        EditText userInput = findViewById(R.id.editText_userInput);
        TextView resultView = findViewById(R.id.textView_result);

        OnConversionListener listener = new OnConversionListener(userInput, resultView);

        conversionBtn.setOnClickListener(listener);         //For btn click
    }
}

