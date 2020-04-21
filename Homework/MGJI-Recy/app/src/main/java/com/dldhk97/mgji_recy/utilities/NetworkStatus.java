package com.dldhk97.mgji_recy.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.dldhk97.mgji_recy.MainActivity;
import com.dldhk97.mgji_recy.enums.NetworkStatusType;

public class NetworkStatus {

    public static NetworkStatusType checkStatus(){
        int sdk = Build.VERSION.SDK_INT;
        if(sdk >= Build.VERSION_CODES.M){
            return getConnectivityStatus();
        }
        else{
            return getConnectivityStatusOld();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static NetworkStatusType getConnectivityStatus(){
        ConnectivityManager cm = (ConnectivityManager) MainActivity.getInstance().getSystemService(MainActivity.getInstance().CONNECTIVITY_SERVICE);
        NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());

        if(nc != null){
            return NetworkStatusType.CONNECTED;
        }
        return NetworkStatusType.DISCONNECTED;
    }

    private static NetworkStatusType getConnectivityStatusOld(){
        ConnectivityManager manager = (ConnectivityManager) MainActivity.getInstance().getSystemService(MainActivity.getInstance().CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null){
            int type = networkInfo.getType();
            return NetworkStatusType.CONNECTED;
        }
        return NetworkStatusType.DISCONNECTED;
    }
}
