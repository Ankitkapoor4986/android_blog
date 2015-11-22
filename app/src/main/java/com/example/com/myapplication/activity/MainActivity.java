package com.example.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.com.myapplication.R;
import com.example.com.myapplication.listener.ButtonClickListener;
import com.example.com.myapplication.service.FirstService;
import com.example.com.myapplication.util.Constants;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "*****************************");
        Log.d(TAG,"onCreate called");
        Log.d(TAG,"*****************************");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.TextViewId);
        ButtonClickListener<NewActivity> buttonClickListener =
                new ButtonClickListener<>( textView, this, NewActivity.class);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonClickListener);
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "*****************************");
        Log.d(TAG,"onResume called");
        Log.d(TAG, "*****************************");
       Intent intent= getIntent();
        if((intent!=null) && (intent.getStringExtra(Constants.TEXT_VAL)!=null)) {
            textView.setText(intent.getStringExtra(Constants.TEXT_VAL));
        }

        Log.d(TAG, "On resume called");
        Log.d(TAG, (String) textView.getText());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        Intent firstServiceIntent=new Intent(this,FirstService.class);
        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.start_service:
                startService(firstServiceIntent);
                return true;

            case R.id.stop_service:
                stopService(firstServiceIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "******************************");
        Log.d(TAG, "onRestart ");
        Log.d(TAG, "******************************");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "******************************");
        Log.d(TAG,"OnDestroy called");
        Log.d(TAG, "******************************");
    }
}
