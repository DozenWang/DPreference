package me.dozen.dpreference;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by wangyida on 15/12/18.
 */
public class PreferenceProvider extends ContentProvider {

    private static final String TAG = PreferenceProvider.class.getSimpleName();

    private static final String AUTHORITY = "me.dozen.dpreference.PreferenceProvider";

    public static final String CONTENT_PREF_BOOLEAN_URI = "content://" + AUTHORITY + "/boolean/";
    public static final String CONTENT_PREF_STRING_URI = "content://" + AUTHORITY + "/string/";
    public static final String CONTENT_PREF_INT_URI = "content://" + AUTHORITY + "/integer/";
    public static final String CONTENT_PREF_LONG_URI = "content://" + AUTHORITY + "/long/";


    public static final String PREF_KEY = "key";
    public static final String PREF_VALUE = "value";

    public static final int PREF_BOOLEAN = 1;
    public static final int PREF_STRING = 2;
    public static final int PREF_INT = 3;
    public static final int PREF_LONG = 4;

    public static final int GET_BOOLEAN = 5;
    public static final int GET_STRING = 6;
    public static final int GET_INT = 7;
    public static final int GET_LONG = 8;

    private IPrefImpl mPrefImpl;

    private static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, "boolean", PREF_BOOLEAN);
        sUriMatcher.addURI(AUTHORITY, "string", PREF_STRING);
        sUriMatcher.addURI(AUTHORITY, "integer", PREF_INT);
        sUriMatcher.addURI(AUTHORITY, "long", PREF_LONG);

        sUriMatcher.addURI(AUTHORITY, "boolean/*", GET_BOOLEAN);
        sUriMatcher.addURI(AUTHORITY, "string/*", GET_STRING);
        sUriMatcher.addURI(AUTHORITY, "integer/*", GET_INT);
        sUriMatcher.addURI(AUTHORITY, "long/*", GET_LONG);

    }

    @Override
    public boolean onCreate() {
        mPrefImpl = new PreferenceImpl(getContext(), Constants.DEFAULT_PREF_NAME);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case GET_BOOLEAN:
                if (uri != null && !TextUtils.isEmpty(uri.getLastPathSegment())) {
                    String key = uri.getLastPathSegment();
                    if(mPrefImpl.hasKey(key)) {
                        cursor = preferenceToCursor(mPrefImpl.getPrefBoolean(key, false) ? 1 : 0);
                    }
                }
                break;
            case GET_STRING:
                if (uri != null && !TextUtils.isEmpty(uri.getLastPathSegment())) {
                    String key = uri.getLastPathSegment();
                    if(mPrefImpl.hasKey(key)) {
                        cursor = preferenceToCursor(mPrefImpl.getPrefString(key, ""));
                    }
                }
                break;
            case GET_INT:
                if (uri != null && !TextUtils.isEmpty(uri.getLastPathSegment())) {
                    String key = uri.getLastPathSegment();
                    if(mPrefImpl.hasKey(key)) {
                        cursor = preferenceToCursor(mPrefImpl.getPrefInt(key, -1));
                    }
                }
                break;
            case GET_LONG:
                if (uri != null && !TextUtils.isEmpty(uri.getLastPathSegment())) {
                    String key = uri.getLastPathSegment();
                    if(mPrefImpl.hasKey(key)) {
                        cursor = preferenceToCursor(mPrefImpl.getPrefLong(key, -1));
                    }
                }
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new IllegalStateException("insert unsupport!!!");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case GET_BOOLEAN:
            case GET_LONG:
            case GET_STRING:
            case GET_INT:
                if (uri != null && !TextUtils.isEmpty(uri.getLastPathSegment())) {
                    String key = uri.getLastPathSegment();
                    mPrefImpl.removePreference(key);
                }
                break;
            default:
                throw new IllegalStateException(" unsupported uri : " + uri);
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (sUriMatcher.match(uri)) {
            case PREF_BOOLEAN:
                persistBoolean(values);
                break;
            case PREF_LONG:
                persistLong(values);
                break;
            case PREF_STRING:
                persistString(values);
                break;
            case PREF_INT:
                persistInt(values);
                break;
            default:
                throw new IllegalStateException("update unsupported uri : " + uri);
        }
        return 0;
    }

    private static String[] PREFERENCE_COLUMNS = {PREF_VALUE};

    private <T> MatrixCursor preferenceToCursor(T value) {
        MatrixCursor matrixCursor = new MatrixCursor(PREFERENCE_COLUMNS, 1);
        MatrixCursor.RowBuilder builder = matrixCursor.newRow();
        builder.add(value);
        return matrixCursor;
    }

    private void persistInt(ContentValues values) {
        if(values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kInteger = values.getAsString(PREF_KEY);
        int vInteger = values.getAsInteger(PREF_VALUE);
        mPrefImpl.setPrefInt(kInteger, vInteger);
    }

    private void persistBoolean(ContentValues values) {
        if(values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kBoolean = values.getAsString(PREF_KEY);
        boolean vBoolean = values.getAsBoolean(PREF_VALUE);
        mPrefImpl.setPrefBoolean(kBoolean, vBoolean);
    }

    private void persistLong(ContentValues values) {
        if(values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kLong = values.getAsString(PREF_KEY);
        long vLong = values.getAsLong(PREF_VALUE);
        mPrefImpl.setPrefLong(kLong, vLong);
    }

    private void persistString(ContentValues values) {
        if(values == null) {
            throw new IllegalArgumentException(" values is null!!!");
        }
        String kString = values.getAsString(PREF_KEY);
        String vString = values.getAsString(PREF_VALUE);
        mPrefImpl.setPrefString(kString, vString);
    }


}
