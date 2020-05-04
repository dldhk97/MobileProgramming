package com.dldhk97.mgji_menu.ui;

import android.graphics.Color;

import com.dldhk97.mgji_menu.MainActivity;
import com.dldhk97.mgji_menu.R;
import com.dldhk97.mgji_menu.enums.ThemeType;

public class ColorHandler {
    private static ColorHandler _Instance;
    private ThemeType currentThemeType = ThemeType.DEFAULT;

    private static int currentSensorNameColor;
    private static int currentSensorTypeColor;
    private static int currentBackgroundColor;

    public static ColorHandler getInstance(){
        if(_Instance == null){
            _Instance = new ColorHandler();
            currentSensorNameColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorName_color_default);
            currentSensorTypeColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorType_color_default);
            currentBackgroundColor = MainActivity.getInstance().getResources().getColor(R.color.item_background_color_default);
        }

        return _Instance;
    }

    public void setTheme(ThemeType themeType){
        // 현 테마와 변경할 테마가 같으면 Default로 적용 (투글)
        if(themeType == currentThemeType){
            themeType = ThemeType.DEFAULT;
        }
        applyTheme(themeType);
        currentThemeType = themeType;
    }

    private void applyTheme(ThemeType themeType){
        switch(themeType){
            case RED:
                currentSensorNameColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorName_color_red);
                currentSensorTypeColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorType_color_red);
                currentBackgroundColor = MainActivity.getInstance().getResources().getColor(R.color.item_background_color_red);
                break;
            case GREEN:
                currentSensorNameColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorName_color_green);
                currentSensorTypeColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorType_color_green);
                currentBackgroundColor = MainActivity.getInstance().getResources().getColor(R.color.item_background_color_green);
                break;
            case BLUE:
                currentSensorNameColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorName_color_blue);
                currentSensorTypeColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorType_color_blue);
                currentBackgroundColor = MainActivity.getInstance().getResources().getColor(R.color.item_background_color_blue);
                break;
            case DEFAULT:
                currentSensorNameColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorName_color_default);
                currentSensorTypeColor = MainActivity.getInstance().getResources().getColor(R.color.item_textView_sensorType_color_default);
                currentBackgroundColor = MainActivity.getInstance().getResources().getColor(R.color.item_background_color_default);
                break;
        }

    }

    public int getSensorNameColorId(){
        return currentSensorNameColor;
    }

    public int getSensorTypeColorId(){
        return currentSensorTypeColor;
    }

    public int getBackgroundColorId(){
        return currentBackgroundColor;
    }

    public ThemeType getCurrentTheme(){
        return currentThemeType;
    }

}
