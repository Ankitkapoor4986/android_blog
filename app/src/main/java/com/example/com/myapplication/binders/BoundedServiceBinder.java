package com.example.com.myapplication.binders;

import android.os.Binder;

import com.example.com.myapplication.service.BoundedService;


public class BoundedServiceBinder extends Binder {
    private final BoundedService service;

    public BoundedServiceBinder(BoundedService boundedService) {
        this.service = boundedService;
    }

    public BoundedService getService() {
        return service;
    }
}
