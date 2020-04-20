package com.dldhk97.mgji_recy.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.adapters.CafeteriaRecyclerAdapter;
import com.dldhk97.mgji_recy.models.Menu;

import java.text.SimpleDateFormat;

public class CafeteriaRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView_icon;
    private TextView textView_date;
    private TextView textView_mealTime;
    private TextView textView_menus;

    private Menu menu;

    public CafeteriaRecyclerViewHolder(@NonNull View itemView, CafeteriaRecyclerAdapter adapter) {
        super(itemView);
        this.imageView_icon = itemView.findViewById(R.id.imageView_icon);
        this.textView_date = itemView.findViewById(R.id.textView_date);
        this.textView_mealTime = itemView.findViewById(R.id.textView_mealTime);
        this.textView_menus = itemView.findViewById(R.id.textView_menus);

        // 클릭 리스너 설정
        itemView.setOnClickListener(this);
    }

    public void onBind(Menu menu)throws Exception{
        this.menu = menu;
        // 아이콘 설정
        int imageId = menu.getImageId();
        if(imageId == 0){
            imageId = R.drawable.unknown;
        }
        imageView_icon.setImageResource(imageId);

        // 날짜 설정
        SimpleDateFormat format = new SimpleDateFormat("YYYY.MM.dd");
        String dateStr = format.format(menu.getDate().getTime());
        textView_date.setText(dateStr);

        // 식사시간 설정
        textView_mealTime.setText(menu.getMealTimeType().toString());

        // 음식 간단히 표시
        StringBuilder foodsStr = new StringBuilder();
        for(String food : menu.getFoods()){
            if(food.startsWith("[") || food.startsWith("*") || food.startsWith("-")){   // 식사시간, 가격은 패스한다.
                continue;
            }
            foodsStr.append(food + " ");
            if(foodsStr.length() > 20){
                foodsStr.append("...");
                break;
            }
        }
        textView_menus.setText(foodsStr.toString());

    }

    @Override
    public void onClick(View view) {
        int pos = getLayoutPosition();
        if(pos != RecyclerView.NO_POSITION){
            UIHandler.getInstance().showAlert(menu.toString());
        }
    }
}
