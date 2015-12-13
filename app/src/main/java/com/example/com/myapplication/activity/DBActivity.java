package com.example.com.myapplication.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.com.myapplication.R;
import com.example.com.myapplication.db.DBHelper;
import com.example.com.myapplication.listener.ButtonClickListener;
import com.example.com.myapplication.model.Person;
import com.example.com.myapplication.util.Constants;

import java.util.List;

/**
 * Created by ankit on 16/11/15.
 */
public class DBActivity extends Activity {
    private SQLiteDatabase db = null;
    List<Person> persons;
    private static final String TAG = "DBActivity";
    private DBHelper dbHelper = new DBHelper(this);
    private ListView personListViewArrayAdapter;
    private ListView personListViewCursorAdapter;
    private static final String[] FROM = {Constants.Database.NAME_COL,
            Constants.Database.ATTR_COL};
    private static final int[] TO = {R.id.db_activity_row_name_textView_cursor_adapter_,
            R.id.db_activity_row_attr_textView_cursor_adapter};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_layout);
        Log.d(TAG, "onCreate() called with: ");
        personListViewArrayAdapter = (ListView) findViewById(R.id.db_activity_list_view_array_adapter);
        ButtonClickListener<BoundedActivity> backButtonListener = new ButtonClickListener<>(null, this, BoundedActivity.class);
        findViewById(R.id.back_button_db_activity).setOnClickListener(backButtonListener);
        personListViewCursorAdapter = (ListView) findViewById(R.id.db_activity_list_view_cursor_adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume called");
    }

    public void onInsertClick(View view) {

        Log.d(TAG, "onInsertClick called");
        Person person = new Person(Constants.Database.ATTR_VAL,
                Constants.Database.ATTR_VAL);
        dbHelper.insert(person);

    }

    public void ongetOneRecordClick(View view) {
        Log.d(TAG, "ongetOneRecordClick called");
        Person person = dbHelper.getPerson();
        Log.d(TAG, "Person object retrived is");
        Log.d(TAG, person.toString());
    }

    public void ongetAllRecord(View view) {
        Log.d(TAG, "ongetAllRecord called");
        persons = dbHelper.getAllPersonsFromAttr(Constants.Database.ATTR_VAL);
        for (Person person : persons) {
            Log.d(TAG, person.toString());
        }
        ArrayAdapter<Person> personAarrayAdapter = new ArrayAdapter<>(
                this, R.layout.db_activity_row_array_adapter, R.id.db_activity_row_name_textView_array_adapter, persons
        );
        personListViewArrayAdapter.setAdapter(personAarrayAdapter);

    }

    public void onGetAllRecordsOnBasisOfAttrViaCursor(View view) {
        Log.d(TAG, "onGetAllRecordsOnBasisOfAttrForCursorAdapter");
        if (db == null)
            db = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.getCursorOfPersonFromAttr(Constants.Database.ATTR_VAL,
                db);
        startManagingCursor(cursor);
        SimpleCursorAdapter simpleCursorAdapter;
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.db_activity_row_cursor_adapter,
                cursor, FROM, TO);
        personListViewCursorAdapter.setAdapter(simpleCursorAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
        if (db != null) {
            db.close();
        }
    }
}
