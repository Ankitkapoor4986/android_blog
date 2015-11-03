package com.example.com.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.com.myapplication.R;
import com.example.com.myapplication.listener.ButtonClickListener;
import com.example.com.myapplication.util.Constants;

import static com.example.com.myapplication.R.id.new_activity_back_button;
import static com.example.com.myapplication.R.id.new_activity_fwd_button;


public class NewActivity extends Activity {

    private static final String TAG = "NewActivity";
    EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_layout);
        textView = (EditText) findViewById(R.id.edit_text_id);
        ButtonClickListener<MainActivity> buttonClickListenerBackButton =
                new ButtonClickListener<>(textView, this, MainActivity.class);
        findViewById(new_activity_back_button).setOnClickListener(buttonClickListenerBackButton);
        ButtonClickListener<ThirdActivity> buttonClickListenerFwdButton =
                new ButtonClickListener<>(textView, this, ThirdActivity.class);
        findViewById(new_activity_fwd_button).setOnClickListener(buttonClickListenerFwdButton);

        Log.d(TAG, "OnCreate called ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume called");
        String text = getIntent().getStringExtra(Constants.TEXT_VAL);
        Log.d(TAG, "Before setting the text");
        textView.setText(text);
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
        String msg = "OnStart called";
        Log.d(TAG, msg);
        Log.d(TAG,"******************************");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "******************************");
        Log.d(TAG, "OnDestroy called");
        Log.d(TAG, "*****************");
    }


}
