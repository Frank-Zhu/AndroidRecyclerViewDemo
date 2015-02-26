package com.frankzhu.recyclerviewdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import com.frankzhu.recyclerviewdemo.bean.DemoItem;
import com.frankzhu.recyclerviewdemo.db.database.Column;
import com.frankzhu.recyclerviewdemo.db.database.SQLiteTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/25  17:30.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ItemsDataHelper extends BaseDataHelper implements DBInterface<DemoItem> {

    public ItemsDataHelper(Context context) {
        super(context);
    }

    @Override
    protected Uri getContentUri() {
        return DataProvider.ALL_ITEMS_CONTENT_URI;
    }

    @Override
    protected String getTableName() {
        return ItemsDBInfo.TABLE_NAME;
    }

    @Override
    public DemoItem query(String id) {
        DemoItem item = null;
        Cursor cursor;
        cursor = query(null, ItemsDBInfo.ID + "= ?",
                new String[]{
                        id
                }, null);
        if (cursor.moveToFirst()) {
            item = DemoItem.fromCursor(cursor);
        }
        cursor.close();
        return item;
    }

    @Override
    public int clearAll() {
        synchronized (DataProvider.obj) {
            DataProvider.DBHelper mDBHelper = DataProvider.getDBHelper();
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            return db.delete(ItemsDBInfo.TABLE_NAME, null, null);
        }
    }

    @Override
    public void bulkInsert(List<DemoItem> listData) {
        ArrayList<ContentValues> contentValues = new ArrayList<>();
        for (DemoItem item : listData) {
            ContentValues values = getContentValues(item);
            contentValues.add(values);
        }
        ContentValues[] valueArray = new ContentValues[contentValues.size()];
        bulkInsert(contentValues.toArray(valueArray));
    }

    @Override
    public ContentValues getContentValues(DemoItem data) {
        ContentValues values = new ContentValues();
        values.put(ItemsDBInfo.ID, data.id);
        values.put(ItemsDBInfo.TITLE, data.title);
        return values;
    }

    @Override
    public CursorLoader getCursorLoader() {
        return new CursorLoader(getContext(), getContentUri(), null, null, null, ItemsDBInfo.ID + " ASC");
    }

    public static final class ItemsDBInfo implements BaseColumns {
        private ItemsDBInfo() {
        }

        public static final String TABLE_NAME = "items";

        public static final String ID = "id";
        public static final String TITLE = "title";

        public static final SQLiteTable TABLE = new SQLiteTable(TABLE_NAME)
                .addColumn(ID, Column.DataType.INTEGER)
                .addColumn(TITLE, Column.DataType.TEXT);
    }
}
