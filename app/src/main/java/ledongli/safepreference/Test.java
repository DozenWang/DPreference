package ledongli.safepreference;


import android.content.Context;
import android.util.Log;

import me.dozen.dpreference.PreferenceUtils;

/**
 * Created by wangyida on 15/12/18.
 */
public class Test {
    public static void test(Context context) {

        String value = PreferenceUtils.getPrefString(context, "test_string", "default");
        Log.i("Dozen", " getPrefString default : " + value);
        PreferenceUtils.setPrefString(context, "test_string", "value_test");
        Log.i("Dozen", " getprefString : " +  PreferenceUtils.getPrefString(context, "test_string", "value_test"));

        int v = PreferenceUtils.getPrefInt(context, "test_int", 0);
        Log.i("Dozen", " getInt default : " + v);
        PreferenceUtils.setPrefInt(context, "test_int", 20);
        Log.i("Dozen", " getprefString : " +  PreferenceUtils.getPrefInt(context, "test_int", 0));

        boolean b = PreferenceUtils.getPrefBoolean(context, "test_boolean", false);
        Log.i("Dozen", " getboolean default : " + b);
        PreferenceUtils.setPrefBoolean(context, "test_boolean", true);
        Log.i("Dozen", " getPrefBoolean : " + PreferenceUtils.getPrefBoolean(context, "test_boolean", false));

        long l = PreferenceUtils.getPrefLong(context, "test_long", 1);
        Log.i("Dozen", " getPrefLong default : " + l);
        PreferenceUtils.setPrefLong(context, "test_long", 100);
        Log.i("Dozen", " setPrefLong : " + PreferenceUtils.getPrefLong(context, "test_long", 1));


//        long start = System.currentTimeMillis();
//        for(int i = 0;i<1000;i++) {
//            PreferenceUtils.setPrefInt("test_string", 1000);
//        }
//        long end1 = System.currentTimeMillis();
//
//        Log.i("dozen", " preference with provider cost : " + (end1 - start));
//
//        for(int i=0;i<1000;i++) {
//            PreferenceImpl.setPrefInt("test_int", 10001);
//        }
//        long end2 = System.currentTimeMillis();
//
//        Log.i("dozen", " preference with origin cost : " + (end2 - end1));

    }
}
