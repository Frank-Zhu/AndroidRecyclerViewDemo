package com.frankzhu.recyclerviewdemo.bean;

import android.database.Cursor;

import com.frankzhu.recyclerviewdemo.db.ItemsDataHelper;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/25  17:21.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DemoItem {
    public int id;
    public String title;

    public DemoItem() {
    }

    public DemoItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static DemoItem fromCursor(Cursor cursor) {
        DemoItem demoItem = new DemoItem();
        demoItem.id = cursor.getInt(cursor.getColumnIndex(ItemsDataHelper.ItemsDBInfo.ID));
        demoItem.title = cursor.getString(cursor.getColumnIndex(ItemsDataHelper.ItemsDBInfo.TITLE));
        return demoItem;
    }
}
