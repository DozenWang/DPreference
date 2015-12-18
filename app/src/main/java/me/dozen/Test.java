package me.dozen;


import android.content.Context;
import android.util.Log;

import me.dozen.dpreference.PreferenceUtils;

/**
 * Created by wangyida on 15/12/18.
 */
public class Test {

    private static final String TAG = Test.class.getSimpleName();

    public static void testPref(Context context) {

        String value = PreferenceUtils.getPrefString(context, "test_string", "default");
        Log.i(TAG, " getPrefString default : " + value);
        PreferenceUtils.setPrefString(context, "test_string", "value_test");
        Log.i(TAG, " getprefString : " +  PreferenceUtils.getPrefString(context, "test_string", "value_test"));

        int v = PreferenceUtils.getPrefInt(context, "test_int", 0);
        Log.i(TAG, " getInt default : " + v);
        PreferenceUtils.setPrefInt(context, "test_int", 20);
        Log.i(TAG, " getprefString : " +  PreferenceUtils.getPrefInt(context, "test_int", 0));

        boolean b = PreferenceUtils.getPrefBoolean(context, "test_boolean", false);
        Log.i(TAG, " getboolean default : " + b);
        PreferenceUtils.setPrefBoolean(context, "test_boolean", true);
        Log.i(TAG, " getPrefBoolean : " + PreferenceUtils.getPrefBoolean(context, "test_boolean", false));

        long l = PreferenceUtils.getPrefLong(context, "test_long", 1);
        Log.i(TAG, " getPrefLong default : " + l);
        PreferenceUtils.setPrefLong(context, "test_long", 100);
        Log.i(TAG, " setPrefLong : " + PreferenceUtils.getPrefLong(context, "test_long", 1));

    }
}
