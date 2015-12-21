package me.dozen.dpreference;


import android.content.Context;

/**
 * Created by wangyida on 15-4-9.
 */
public class DPreference {

    Context mContext;

    String mName;

    private DPreference() {
    }

    public DPreference(Context context, String name) {
        this.mContext = context;
        this.mName = name;
    }

    public String getPrefString(final String key,
                                final String defaultValue) {
        return PreferenceDao.getString(mContext, mName, key, defaultValue);
    }

    public void setPrefString(final String key, final String value) {
        PreferenceDao.setString(mContext, mName, key, value);
    }

    public boolean getPrefBoolean(final String key,
                                  final boolean defaultValue) {
        return PreferenceDao.getBoolean(mContext, mName, key, defaultValue);
    }

    public void setPrefBoolean(final String key, final boolean value) {
        PreferenceDao.setBoolean(mContext, mName, key, value);
    }

    public void setPrefInt( final String key, final int value) {
        PreferenceDao.setInt(mContext, mName, key, value);
    }

    public int getPrefInt(final String key, final int defaultValue) {
        return PreferenceDao.getInt(mContext, mName, key, defaultValue);
    }

    public void setPrefLong( final String key, final long value) {
        PreferenceDao.setLong(mContext, mName, key, value);
    }

    public long getPrefLong(final String key, final long defaultValue) {
        return PreferenceDao.getLong(mContext, mName, key, defaultValue);
    }

    public void removePreference(String key) {
        PreferenceDao.remove(mContext, mName, key);
    }

}
