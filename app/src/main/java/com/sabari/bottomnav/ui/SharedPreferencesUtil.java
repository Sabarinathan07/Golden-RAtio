package com.sabari.bottomnav.ui;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtil {



    public static String getLanguage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", MODE_PRIVATE);
        String defaultLanguage = Locale.getDefault().getLanguage();
        return sharedPreferences.getString("lang", defaultLanguage);
    }
}
