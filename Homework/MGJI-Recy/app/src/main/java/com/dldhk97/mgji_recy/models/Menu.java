package com.dldhk97.mgji_recy.models;

import androidx.annotation.NonNull;

import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.MealTimeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

// 어떤 날의 조식/중식/석식 중 하나의 식단. 여러 음식을 포함함.
public class Menu implements Serializable {
    private Calendar date;                      // 날짜
    private CafeteriaType cafeteriaType;    // 식당
    private MealTimeType mealTimeType;      // 조식/중식/석식
    private ArrayList<String> foods;        // 음식 리스트
    private int imageId;

    public Menu(Calendar date, CafeteriaType cafeteriaType, MealTimeType mealTimeType){
        this.date = date;
        this.cafeteriaType = cafeteriaType;
        this.mealTimeType = mealTimeType;
        foods = new ArrayList<>();
    }

    public Calendar getDate(){
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

    public ArrayList<String> getFoods(){
        return foods;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
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

            //ETC 추가할것
            return sb.toString();
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[Menu.toString]\n" + e.getMessage());
        }
        return super.toString();
    }
}
