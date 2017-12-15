package com.jephy.retrofitpracticedemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jfyang on 8/26/17.
 */

public class InternetUtils {

    private static final int CONNECTION_OK = 0;
    private static final int CONNECT_FAIL = 1;

    private InternetUtils(){

    }

    private static InternetUtils utils;

    public static InternetUtils getInstance(){
        if (utils == null) {
            utils = new InternetUtils();
        }
        return utils;
    }

    /**
     * 能否连接google
     * @param context
     * @return
     */
    public static boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(3000);
                urlc.setReadTimeout(3000);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
//                Log.e("mynetwork", "Error checking internet connection", e);
            }
        } else {
            Log.d("mynetwork", "No network available!");
        }
        return false;
    }

    /**
     * 能否连接evomotion服务器
     * @param context
     * @return
     */
    public static boolean hasEvomotionConnection(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://download.evomotion.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(3000);
                urlc.setReadTimeout(3000);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
//                Log.e("mynetwork", "Error checking internet connection", e);
            }
        } else {
            Log.d("mynetwork", "No network available!");
        }
        return false;
    }

    private  static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
