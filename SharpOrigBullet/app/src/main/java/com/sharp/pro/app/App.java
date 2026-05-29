package com.sharp.pro.app;

import android.app.Application;
import android.content.Context;

import top.niunaijun.blackbox.core.system.api.MetaActivationManager;

public class App extends Application {

    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // تهيئة BlackBox مرة واحدة فقط
        MetaActivationManager.attachClient(base);

        context = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // تفعيل SDK
        MetaActivationManager.activateSdk("CBCA-FFAA-9DA5-DF0C");
    }

    public static Context getAppContext() {
        return context;
    }
}