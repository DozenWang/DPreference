package me.dozen;


import android.content.Context;
import android.util.Log;

import net.grandcentrix.tray.TrayAppPreferences;

import me.dozen.dpreference.DPreference;

/**
 * Created by wangyida on 15/12/18.
 */
public class Test {

    private static final String TAG = Test.class.getSimpleName();

    public static void testDPreference(Context context) {
        final DPreference dPreference = new DPreference(context, "dpref"); // this Preference comes for free from the library

        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++) {
            dPreference.setPrefString( "test_string", "test_pref");
        }
        long end = System.currentTimeMillis();
        Log.i(TAG, "DPreferecne i/o setString 1000 cost : " + (end - start));


        long start1 = System.currentTimeMillis();
        for(int i=0;i<1000;i++) {
            dPreference.getPrefString("test_string", "test_pref");
        }
        long end1 = System.currentTimeMillis();
        Log.i(TAG, "DPreferecne i/o getString 1000 cost : " + (end1 - start1));

        boolean b = dPreference.getPrefBoolean("test_boolean", false);
        Log.i(TAG, " getboolean default : " + b);
        dPreference.setPrefBoolean("test_boolean", true);
        Log.i(TAG, " getPrefBoolean : " + dPreference.getPrefBoolean("test_boolean", false));


        int v = dPreference.getPrefInt("test_int", 0);
        Log.i(TAG, " getInt default : " + v);
        dPreference.setPrefInt("test_int", 20);
        Log.i(TAG, " getPrefInt : " +  dPreference.getPrefInt("test_int", 0));

        long l = dPreference.getPrefLong( "test_long", 1);
        Log.i(TAG, " getPrefLong default : " + l);
        dPreference.setPrefLong( "test_long", 100);
        Log.i(TAG, " setPrefLong : " + dPreference.getPrefLong("test_long", 1));

    }

    public static void testTrayPreference(Context context) {
        // create a preference accessor. This is for global app preferences.
        final TrayAppPreferences appPreferences = new TrayAppPreferences(context); // this Preference comes for free from the library

        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++) {
            appPreferences.put("test_string", "test_pref");
        }
        long end = System.currentTimeMillis();
        Log.i(TAG, "Tray i/o setString 1000 cost : " + (end - start));


        long start1 = System.currentTimeMillis();
        for(int i=0;i<1000;i++) {
            appPreferences.getString("test_string", "test_pref");
        }
        long end1 = System.currentTimeMillis();
        Log.i(TAG, "Tray i/o getString 1000 cost : " + (end1 - start1));
    }
}
