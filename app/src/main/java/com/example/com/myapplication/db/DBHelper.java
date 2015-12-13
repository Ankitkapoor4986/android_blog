package com.example.com.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.com.myapplication.model.Person;

import java.util.ArrayList;
import java.util.List;

import com.example.com.myapplication.util.Constants;

import static com.example.com.myapplication.util.Constants.Database.ATTR_COL;
import static com.example.com.myapplication.util.Constants.Database.NAME_COL;

/**
 * Created by akapoor on 5/11/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    private SQLiteDatabase db;
    private static final String DB_NAME = "test_db";
    private static final int VERSION = 5;

    private static final String TABLE_NAME = "Person";
    private final String[] columns = {Constants.Database.NAME_COL,
            Constants.Database.ATTR_COL};

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("create table ")
                .append(TABLE_NAME)
                .append(Constants.OPEN_BRACKET)
                .append(Constants.SPACE)
                .append(Constants.Database.NAME_COL).append(Constants.SPACE)
                .append(" varchar(20) ").append(Constants.COMMA)
                .append(Constants.SPACE)
                .append(Constants.Database
                        .ATTR_COL).append(Constants.SPACE)
                .append(" varchar(20) ")
                .append(Constants.SPACE)
                .append(Constants.CLOSE_BRACKET);

        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ".concat(TABLE_NAME));

        onCreate(db);
    }

    public void insert(Person person) {
        ContentValues cv = new ContentValues(2);
        cv.put(NAME_COL, person.getName());
        cv.put(ATTR_COL, person.getAttr());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public Person getPerson() {
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Person person = new Person(cursor.getString(cursor.getColumnIndex(NAME_COL)),
                cursor.getString(cursor.getColumnIndex(ATTR_COL)));
        cursor.close();
        db.close();
        return person;
    }

    public List<Person> getAllPersonsFromAttr(String attr) {
        String selecttionArgs[] = {attr};
        Cursor cursor = null;
        List<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = getReadableDatabase();

        try {
            cursor = db.query(TABLE_NAME, columns, ATTR_COL + "=?", selecttionArgs,
                    null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                Person person = new Person(cursor.getString(cursor.getColumnIndex(NAME_COL)),
                        cursor.getString(cursor.getColumnIndex(ATTR_COL)));
                persons.add(person);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.e(TAG, "Error occured", e);
        }
        db.close();
        return persons;
    }


    public Cursor getCursorOfPersonFromAttr(String attr, SQLiteDatabase db) {
        String selecttionArgs[] = {attr};
        Cursor cursor = null;
        List<Person> persons = new ArrayList<Person>();
        cursor = db.query(TABLE_NAME, columns, ATTR_COL + "=?", selecttionArgs,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


}
