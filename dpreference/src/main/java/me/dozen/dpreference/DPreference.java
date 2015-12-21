package me.dozen.dpreference;


import android.content.Context;

/**
 * Created by wangyida on 15-4-9.
 */
public class DPreference {


    private DPreference() {
    }

    public static String getPrefString(Context context, final String key,
                                       final String defaultValue) {
        return PreferenceDao.getString(context, key, defaultValue);
    }

    public static void setPrefString(Context context, final String key, final String value) {
        PreferenceDao.setString(context, key, value);
    }

    public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
        return PreferenceDao.getBoolean(context, key, defaultValue);
    }

    public static void setPrefBoolean(Context context, final String key, final boolean value) {
        PreferenceDao.setBoolean(context, key, value);
    }

    public static void setPrefInt(Context context, final String key, final int value) {
        PreferenceDao.setInt(context, key, value);
    }

    public static int getPrefInt(Context context, final String key, final int defaultValue) {
        return PreferenceDao.getInt(context, key, defaultValue);
    }

    public static void setPrefLong(Context context, final String key, final long value) {
        PreferenceDao.setLong(context, key, value);
    }

    public static long getPrefLong(Context context, final String key, final long defaultValue) {
        return PreferenceDao.getLong(context, key, defaultValue);
    }

    public static void removePreference(Context context, final String key) {
        PreferenceDao.remove(context, key);
    }

}
