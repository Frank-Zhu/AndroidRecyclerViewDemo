package com.frankzhu.recyclerviewdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-11-22 14:29
 * Description: 数据库帮助类基类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 14-11-22      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseDataHelper {
    private Context mContext;

    protected abstract Uri getContentUri();
    protected abstract String getTableName();

    public BaseDataHelper(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public void notifyChange() {
        mContext.getContentResolver().notifyChange(getContentUri(), null);
    }

    protected final Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    protected final Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }

    protected final Uri insert(ContentValues values) {
        return mContext.getContentResolver().insert(getContentUri(), values);
    }

    protected final int bulkInsert(ContentValues[] values) {
        return mContext.getContentResolver().bulkInsert(getContentUri(), values);
    }

    protected final int update(ContentValues values, String where, String[] whereArgs) {
        return mContext.getContentResolver().update(getContentUri(), values, where, whereArgs);
    }

    protected final int delete(String where, String[] selectionArgs) {
        return mContext.getContentResolver().delete(getContentUri(), where, selectionArgs);
    }

    public CursorLoader getCursorLoader(Context context) {
        return getCursorLoader(context, null, null, null, null);
    }

    public CursorLoader getCursorLoader(Context context, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(context, getContentUri(), projection, selection, selectionArgs, sortOrder);
    }

    protected final Cursor getList(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mContext.getContentResolver().query(getContentUri(), projection, selection, selectionArgs, sortOrder);
    }
}
