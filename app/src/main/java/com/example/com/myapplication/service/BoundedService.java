package com.example.com.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.com.myapplication.binders.BoundedServiceBinder;


public class BoundedService extends Service {

    private static final String TAG = "BoundedService";
    public static final String FROM_BOUNDED_SERVICE_METHOD = "From Bounded Service method";

    private final IBinder boundedServiceBinder = new BoundedServiceBinder(this);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "OnBind called");
        return boundedServiceBinder;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate ");
        super.onCreate();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind ");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public String workToDo() {
        Log.d(TAG, "**************************");
        Log.d(TAG, "workToDo ");
        Log.d(TAG, "we need to do this work");
        Log.d(TAG, "***************************");
        return FROM_BOUNDED_SERVICE_METHOD;

    }


}
