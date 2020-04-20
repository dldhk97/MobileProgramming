package com.dldhk97.mgji_recy.listeners;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.UIHandler;

public class OnHyperLinkListener implements View.OnClickListener {
    String targetUrl = "about:blank";

    public OnHyperLinkListener(String targetUrl){
        this.targetUrl = targetUrl;
    }

    @Override
    public void onClick(View view) {
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(targetUrl));
            MainActivity.getInstance().startActivity(intent);
        }
        catch (Exception e){
            UIHandler.getInstance().showAlert("[OnHyperLinkListener.onClick]" + e.getMessage());
        }
    }
}
