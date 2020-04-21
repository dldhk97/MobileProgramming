package com.dldhk97.mgji_recy.listeners;

import android.view.View;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.UIHandler;

public class OnAboutListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        try{
            String title = MainActivity.getInstance().getResources().getString(R.string.about_title);
            String description = MainActivity.getInstance().getResources().getString(R.string.about_description);
            UIHandler.getInstance().showAlert(title, description);
        }
        catch (Exception e){
            UIHandler.getInstance().showAlert("[OnAboutListener.onClick]\n" + e.getMessage());
        }

    }
}
