package com.example.com.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.com.myapplication.R;
import com.example.com.myapplication.db.DBHelper;
import com.example.com.myapplication.model.Person;

import java.util.List;

/**
 * Created by ankit on 16/11/15.
 */
public class DBActivity extends Activity {

    private static final String TAG = "DBActivity";
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_layout);
        Log.d(TAG, "onCreate() called with: ");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume called");
    }

    public void onInsertClick(View view) {

        Log.d(TAG, "onInsertClick called");
        Person person = new Person("Ankit", 29);
        dbHelper.insert(person);

    }

    public void ongetOneRecordClick(View view) {
        Log.d(TAG, "ongetOneRecordClick called");
        Person person = dbHelper.getPerson();
        Log.d(TAG,"Person object retrived is");
        Log.d(TAG, person.toString());
    }

    public void ongetAllRecord(View view) {
        Log.d(TAG, "ongetAllRecord called");
        List<Person> persons = dbHelper.getAllPersonsFromAge("29");
        for (Person person : persons) {
            Log.d(TAG, person.toString());
        }

    }

}
