package me.dozen.dpreference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by wangyida on 15/12/18.
 */
class PreferenceDao {

    public static String getString(Context context, String key, String defaultValue) {
        Uri URI = Uri.parse(getUriByType(PreferenceProvider.PREF_STRING) + key);
        String value = defaultValue;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getString(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static int getInt(Context context, String key, int defaultValue) {
        Uri URI = Uri.parse(getUriByType(PreferenceProvider.PREF_INT) + key);
        int value = defaultValue;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getInt(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static long getLong(Context context, String key, long defaultValue) {
        Uri URI = Uri.parse(getUriByType(PreferenceProvider.PREF_LONG) + key);
        long value = defaultValue;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getLong(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value;
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        Uri URI = Uri.parse(getUriByType(PreferenceProvider.PREF_BOOLEAN) + key);
        int value = defaultValue ? 1 : 0;
        Cursor cursor = context.getContentResolver().query(URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            value = cursor.getInt(cursor.getColumnIndex(PreferenceProvider.PREF_VALUE));
        }
        IOUtils.closeQuietly(cursor);
        return value == 1;
    }

    public static void setString(Context context, String key, String value) {
        insertOrUpdatePreference(context, key, value, PreferenceProvider.PREF_STRING);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        insertOrUpdatePreference(context, key, value, PreferenceProvider.PREF_BOOLEAN);
    }

    public static void setLong(Context context, String key, long value) {
        insertOrUpdatePreference(context, key, value, PreferenceProvider.PREF_LONG);
    }

    public static void setInt(Context context, String key, int value) {
        insertOrUpdatePreference(context, key, value, PreferenceProvider.PREF_INT);
    }

    public static void remove(Context context, String key) {
        Uri URI = Uri.parse(getUriByType(PreferenceProvider.PREF_STRING) + key);
        context.getContentResolver().delete(URI, null, null);
    }

    private static void  insertOrUpdatePreference(Context context, String key, String value, int type) {
        Uri URI = Uri.parse(getUriByType(type));
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    private static void  insertOrUpdatePreference(Context context, String key, boolean value, int type) {
        Uri URI = Uri.parse(getUriByType(type));
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    private static void  insertOrUpdatePreference(Context context, String key, int value, int type) {
        Uri URI = Uri.parse(getUriByType(type));
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    private static void  insertOrUpdatePreference(Context context, String key, long value, int type) {
        Uri URI = Uri.parse(getUriByType(type));
        ContentValues cv = new ContentValues();
        cv.put(PreferenceProvider.PREF_KEY, key);
        cv.put(PreferenceProvider.PREF_VALUE, value);
        context.getContentResolver().update(URI, cv, null, null);
    }

    private static String getUriByType(int type) {
        switch (type) {
            case PreferenceProvider.PREF_BOOLEAN:
                return PreferenceProvider.CONTENT_PREF_BOOLEAN_URI;
            case PreferenceProvider.PREF_INT:
                return PreferenceProvider.CONTENT_PREF_INT_URI;
            case PreferenceProvider.PREF_LONG:
                return PreferenceProvider.CONTENT_PREF_LONG_URI;
            case PreferenceProvider.PREF_STRING:
                return PreferenceProvider.CONTENT_PREF_STRING_URI;
        }
        throw new IllegalStateException("unsupport preftype : " + type);
    }
}
