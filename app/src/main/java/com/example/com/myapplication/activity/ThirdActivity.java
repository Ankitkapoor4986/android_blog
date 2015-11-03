package com.example.com.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.com.myapplication.R;
import com.example.com.myapplication.listener.ButtonClickListener;
import com.example.com.myapplication.util.Constants;

/**
 * Created by akapoor on 6/10/15.
 */
public class ThirdActivity extends Activity {

    private static final String TAG = "ThirdActivity";
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        Log.d(TAG, "OnCreate called");
         textview= (TextView) findViewById(R.id.third_activity_text_view);
        Button backButton = (Button) findViewById(R.id.third_activity_back_button);
        ButtonClickListener<NewActivity> backButtonListener = new ButtonClickListener<>(textview, this, NewActivity.class);
        backButton.setOnClickListener(backButtonListener);
        Button fwdButton= (Button) findViewById(R.id.third_activity_fwd_button);
        ButtonClickListener<BoundedActivity> forwardButtonListener=new ButtonClickListener<>(textview,this,BoundedActivity.class);
        fwdButton.setOnClickListener(forwardButtonListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume called");
        String text = getIntent().getStringExtra(Constants.TEXT_VAL);
        Log.d(TAG, "Before setting the text");
        textview.setText(text);
        
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "*****************************");
        Log.d(TAG,"onPause called");
        Log.d(TAG,"*****************************");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "*****************************");
        Log.d(TAG,"onStop called");
        Log.d(TAG, "*****************************");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "******************************");
        Log.d(TAG,"OnStart called");
        Log.d(TAG,"******************************");
    }


}
