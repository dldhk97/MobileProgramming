package com.dldhk97.mgji_recy.viewholders;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.PopupActivity;
import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.adapters.CafeteriaRecyclerAdapter;
import com.dldhk97.mgji_recy.models.Menu;
import com.dldhk97.mgji_recy.utilities.DateUtility;

public class CafeteriaRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView_icon;
    private TextView textView_date;
    private TextView textView_mealTime;
    private TextView textView_menus;

    private Menu menu;

    public CafeteriaRecyclerViewHolder(@NonNull View itemView, CafeteriaRecyclerAdapter adapter) {
        super(itemView);
        try{
            this.imageView_icon = itemView.findViewById(R.id.imageView_icon);
            this.textView_date = itemView.findViewById(R.id.popup_textView_title);
            this.textView_mealTime = itemView.findViewById(R.id.textView_mealTime);
            this.textView_menus = itemView.findViewById(R.id.textView_menus);

            // 클릭 리스너 설정
            itemView.setOnClickListener(this);
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[CafeteriaRecyclerViewHolder.constructor]" + e.getMessage());
        }
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
        String dateStr = DateUtility.DateToString(menu.getDate(), '.');
        textView_date.setText(dateStr);

        // 식사시간 설정
        textView_mealTime.setText(menu.getMealTimeType().toString());

        // 음식 간단히 표시
        StringBuilder foodsStr = new StringBuilder();
        for(String food : menu.getFoods()){
            if(food.contains("[") || food.contains("*") || food.contains("-")
                    || food.contains("]") || food.contains("식당 안에서 식사는")
                    || food.contains("금지합니다") || food.contains("~")){           // 진짜 음식만 남긴다.
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

    private final int resultCode = 1;

    @Override
    public void onClick(View view) {
        try{
            int pos = getLayoutPosition();
            if(pos != RecyclerView.NO_POSITION){
//            UIHandler.getInstance().showAlert(menu.toString());
                Intent intent = new Intent(MainActivity.getInstance(), PopupActivity.class);
                intent.putExtra("menu", menu);
                MainActivity.getInstance().startActivityForResult(intent, resultCode);
            }
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[CafeteriaRecyclerViewHolder.onClick]" + e.getMessage());
        }
    }
}
