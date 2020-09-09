package com.senior.wiet.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.senior.wiet.BuildConfig;

import java.util.Objects;

/**
 * Created by lance on 18/February/2020.
 */
public class Utilities {

    /*
     * This method can be used to show log.
     * @param tag
     * @param message
     */


    public static void Log(String tag, String message) {
        if (BuildConfig.LOG) {
            Log.i(tag, message);
        }
    }

    /*
     * This method can check whether your device is connected
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    //This method to open keyboard
    public static void showKeyboard(Context context){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    //This method to close keyboard
    public static void closeKeyboard(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        if (imm.isAcceptingText()) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }
}
