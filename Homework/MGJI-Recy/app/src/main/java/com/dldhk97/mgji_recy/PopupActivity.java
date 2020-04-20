package com.dldhk97.mgji_recy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dldhk97.mgji_recy.listeners.OnHyperLinkListener;
import com.dldhk97.mgji_recy.models.Menu;

import java.text.SimpleDateFormat;

public class PopupActivity extends Activity {

    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        Intent intent = getIntent();
        menu = (Menu)intent.getSerializableExtra("menu");

        setupUiComponents();
    }

    private void setupUiComponents(){
        //날짜 설정
        TextView textView_title = findViewById(R.id.popup_textView_title);
        SimpleDateFormat format = new SimpleDateFormat("YYYY.MM.dd");
        String dateStr = format.format(menu.getDate().getTime());
        textView_title.setText(dateStr);

        // 식사시간 설정
        TextView popup_textView_mealTime = findViewById(R.id.popup_textView_mealTime);
        popup_textView_mealTime.setText(menu.getMealTimeType().toString());

        // 아이콘 설정
        int imageId = menu.getImageId();
        if(imageId == 0){
            imageId = R.drawable.unknown;
        }
        ImageView popup_imageView_icon = findViewById(R.id.popup_imageView_icon);
        popup_imageView_icon.setImageResource(imageId);

        // 음식 완전히 표시
        StringBuilder foodsStr = new StringBuilder();
        for(String food : menu.getFoods()){
            foodsStr.append(food + "\n");
        }
        TextView popup_textView_menus = findViewById(R.id.popup_textView_menus);
        popup_textView_menus.setText(foodsStr.toString());

        // 하이퍼링크 버튼 설정
        Button popup_button_link = findViewById(R.id.popup_button_link);
        String url = menu.getCafeteriaType().getURL();                          // url 설정
        url += "mode=menuList&srDt=" + format.format(menu.getDate().getTime()); // 해당 날짜로 설정
        popup_button_link.setOnClickListener(new OnHyperLinkListener(url));
    }
}
