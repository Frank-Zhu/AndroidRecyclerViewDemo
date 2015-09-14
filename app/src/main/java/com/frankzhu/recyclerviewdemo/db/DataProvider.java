package com.frankzhu.recyclerviewdemo.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.frankzhu.recyclerviewdemo.app.AppApplication;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-11-22 14:25
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 14-11-22      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DataProvider extends ContentProvider {
    public static final Object obj = new Object();
    public static final String AUTHORITY = "com.frankzhu.recyclerviewdemo";
    public static final String SCHEME = "content://";

    private static final int ALL_ITEMS = 0;//Demo列表

    public static final String PATH_ALL_ITEMS = "/allItems";//Demo列表

    public static final Uri ALL_ITEMS_CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_ALL_ITEMS);//Demo列表

    public static final String ALL_ITEMS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.frankzhu.all.items";//Demo列表

    private static DBHelper mDBHelper;

    public static DBHelper getDBHelper() {
        if (mDBHelper == null) {
            mDBHelper = new DBHelper(AppApplication.getContext());
        }
        return mDBHelper;
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        synchronized (obj) {
            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables(matchTable(uri));

            SQLiteDatabase db = getDBHelper().getReadableDatabase();
            Cursor cursor = queryBuilder.query(db,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }
    }

    private static final UriMatcher sUriMATCHER = new UriMatcher(UriMatcher.NO_MATCH) {{
        addURI(AUTHORITY, "allItems", ALL_ITEMS);//Demo列表

    }};

    private String matchTable(Uri uri) {
        String table;
        switch (sUriMATCHER.match(uri)) {
            case ALL_ITEMS://Demo列表
                table = ItemsDataHelper.ItemsDBInfo.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri" + uri);
        }
        return table;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMATCHER.match(uri)) {
            case ALL_ITEMS://Demo列表
                return ALL_ITEMS_CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        synchronized (obj) {
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            long rowId = 0;
            db.beginTransaction();
            try {
                rowId = db.insert(matchTable(uri), null, values);
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
            if (rowId > 0) {
                Uri returnUri = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(uri, null);
                return returnUri;
            }
            throw new SQLException("Failed to insert row into " + uri);
        }
    }

    @Override
    public int bulkInsert(Uri uri, @NonNull ContentValues[] values) {
        synchronized (obj) {
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            db.beginTransaction();
            try {
                for (ContentValues contentValues : values) {
                    db.insertWithOnConflict(matchTable(uri), BaseColumns._ID, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
                }
                db.setTransactionSuccessful();
                getContext().getContentResolver().notifyChange(uri, null);
                return values.length;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
            throw new SQLException("Failed to insert row into " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        synchronized (obj) {
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            int count = 0;
            db.beginTransaction();
            try {
                count = db.delete(matchTable(uri), selection, selectionArgs);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        synchronized (obj) {
            SQLiteDatabase db = getDBHelper().getWritableDatabase();
            int count;
            db.beginTransaction();
            try {
                count = db.update(matchTable(uri), values, selection, selectionArgs);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
    }

    public static void clearDBCache() {
        synchronized (DataProvider.obj) {
            DBHelper mDBHelper = DataProvider.getDBHelper();
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            db.delete(ItemsDataHelper.ItemsDBInfo.TABLE_NAME, null, null);
        }
    }

    public static class DBHelper extends SQLiteOpenHelper {

        private static final String DB_NAME = "recycler_demo.db";

        private static final int DB_NEW_VERSION = 1;
        private static final int DB_BASE_VERSION = 1;

        private DBHelper(Context context) {
            super(context, DB_NAME, null, DB_BASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            for (int i = DB_BASE_VERSION; i <= DB_NEW_VERSION; i++) {
                updateDb(db, i);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            for (int i = oldVersion + 1; i <= newVersion; i++) {
                updateDb(db, i);
            }
        }

        private void updateDb(SQLiteDatabase db, int version) {
            switch (version) {
                case 1:
                    ItemsDataHelper.ItemsDBInfo.TABLE.create(db);//Demo列表
                    break;
            }
        }
    }
}
