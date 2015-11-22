package com.example.com.myapplication.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.com.myapplication.R;
import com.example.com.myapplication.binders.BoundedServiceBinder;
import com.example.com.myapplication.listener.ButtonClickListener;
import com.example.com.myapplication.service.BoundedService;


public class BoundedActivity extends Activity implements ServiceConnection  {

    private static String TAG="BoundedActivity";
    private boolean serviceConnected =false;
    private BoundedServiceBinder boundedServiceBinder;
    private BoundedService boundedService;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.bounded_activiy_layout);
        Button backButton = (Button) findViewById(R.id.bounded_activity_back_button);
        ButtonClickListener<ThirdActivity> backButtonListener = new ButtonClickListener<>(null, this, ThirdActivity.class);
        backButton.setOnClickListener(backButtonListener);
        Button fwdButton= (Button) findViewById(R.id.fwd_button_bounded_activity);
        ButtonClickListener<DBActivity> forwardButtonListener=new ButtonClickListener<>(null,this,DBActivity.class);
        fwdButton.setOnClickListener(forwardButtonListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bounded_service_menu,menu);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"OnResume called");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent bindServiceIntent=new Intent(this, BoundedService.class);
        switch (item.getItemId()){
            case R.id.bind_service:

                    bindService(bindServiceIntent, this, Context.BIND_AUTO_CREATE);

                if(serviceConnected) {
                    boundedService.workToDo();
                }
                return  true;
            case R.id.unbind_service:

                unbindService(this);
                return true;
        }
        return  false;
    }



        @Override
        public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
            Log.d(TAG, "onServiceConnected *************");
            serviceConnected=true;
            boundedServiceBinder= (BoundedServiceBinder) serviceBinder;
            boundedService=boundedServiceBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceConnected=false;

        }





}
