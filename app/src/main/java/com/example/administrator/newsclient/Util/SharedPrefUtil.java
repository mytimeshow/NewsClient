package com.example.administrator.newsclient.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class SharedPrefUtil {
    private static String FILE = "news";

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
    public static boolean getBoolean(Context context, String key, boolean def) {
        SharedPreferences sp = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);
        return sp.getBoolean(key, def);
    }
}
