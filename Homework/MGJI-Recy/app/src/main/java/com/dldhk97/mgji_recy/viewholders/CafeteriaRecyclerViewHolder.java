package com.dldhk97.mgji_recy.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.adapters.CafeteriaRecyclerAdapter;
import com.dldhk97.mgji_recy.models.Menu;

import java.text.SimpleDateFormat;

public class CafeteriaRecyclerViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView_icon;
    private TextView textView_date;
    private TextView textView_mealTime;
    private TextView textView_menus;

    public CafeteriaRecyclerViewHolder(@NonNull View itemView, CafeteriaRecyclerAdapter adapter) {
        super(itemView);
        this.imageView_icon = itemView.findViewById(R.id.imageView_icon);
        this.textView_date = itemView.findViewById(R.id.textView_date);
        this.textView_mealTime = itemView.findViewById(R.id.textView_mealTime);
        this.textView_menus = itemView.findViewById(R.id.textView_menus);
    }

    public void onBind(Menu menu)throws Exception{
        //            imageView_icon.setImageResource(R.id.);                       // 아이콘 설정
        // 날짜 설정
        SimpleDateFormat format = new SimpleDateFormat("YYYY.MM.dd");
        String dateStr = format.format(menu.getDate().getTime());
        textView_date.setText(dateStr);               // 날짜 설정
        textView_mealTime.setText(menu.getMealTimeType().toString());   // 식사시간 설정
        textView_menus.setText("음식1");

    }
}
