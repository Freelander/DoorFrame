package com.display.doorframe;

import android.app.Application;

/**
 * Created by Jun on 2015/4/15.
 */
public class App extends Application {

    private static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App getInstance(){
        return mContext;
    }
}
