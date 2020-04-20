package com.dldhk97.mgji_recy.models;

import androidx.annotation.NonNull;

import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.MealTimeType;

import java.util.ArrayList;
import java.util.Date;

// 어떤 날의 조식/중식/석식 중 하나의 식단
public class Menu {
    Date date;                      // 날짜
    CafeteriaType cafeteriaType;    // 식당
    MealTimeType mealTimeType;      // 조식/중식/석식
    ArrayList<String> foods;        // 음식 리스트

    public Menu(Date date, CafeteriaType cafeteriaType, MealTimeType mealTimeType){
        this.date = date;
        this.cafeteriaType = cafeteriaType;
        this.mealTimeType = mealTimeType;
        foods = new ArrayList<>();
    }

    public Date getDate(){
        return this.date;
    }

    public CafeteriaType getCafeteriaType(){
        return this.cafeteriaType;
    }

    public MealTimeType getMealTimeType(){
        return this.mealTimeType;
    }

    public void addFood(String foodName) throws Exception{
        foods.add(foodName);
    }

    public String getFood(int index) throws Exception{
        return foods.get(index);
    }

    public int getCount(){
        return foods.size();
    }

    @NonNull
    @Override
    public String toString() {
        try{
            StringBuilder sb = new StringBuilder();
            for(String s : foods){
                sb.append(s + " ");
            }
            return sb.toString();
        }
        catch(Exception e){
            UIHandler.getInstance().showToast("[Menu.toString]\n" + e.getMessage());
        }
        return super.toString();
    }
}
