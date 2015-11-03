package com.example.com.myapplication.listener;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.com.myapplication.util.Constants;

public class ButtonClickListener<T extends Activity> implements View.OnClickListener {

    private TextView textView;
    private Activity activity;
    private Class<T> tClass;
    private static String TAG = "ButtonClickListener";

    public ButtonClickListener(TextView view, Activity activity, Class<T> tClass) {
        this.textView = view;
        this.activity = activity;
        this.tClass = tClass;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, tClass);
        if ((textView != null)) {
            Log.d(TAG, "****************");
            String text=String.valueOf(textView.getText());
            Log.d(TAG, text);

            intent.putExtra(Constants.TEXT_VAL,text);
        }
        activity.startActivity(intent);
    }
}
