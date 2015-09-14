package com.frankzhu.recyclerviewdemo.app;

import android.app.Application;
import android.content.Context;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/25  17:43.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class AppApplication extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
