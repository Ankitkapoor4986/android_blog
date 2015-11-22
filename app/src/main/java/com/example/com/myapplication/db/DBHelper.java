package com.example.com.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.com.myapplication.model.Person;
import com.example.com.myapplication.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akapoor on 5/11/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    private static final String DB_NAME = "test_db";
    private static final int VERSION = 2;
    private static final String NAME_COL = "name";
    private static final String AGE_COL = "age";
    private static final String TABLE_NAME = "Person";
    private final String[] columns = {NAME_COL, AGE_COL};

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
                .append(NAME_COL).append(Constants.SPACE)
                .append(" varchar(20)").append(Constants.COMMA)
                .append(Constants.SPACE)
                .append(AGE_COL).append(Constants.SPACE)
                .append(" int ")
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
        cv.put(AGE_COL, person.getAge());
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
                cursor.getInt(cursor.getColumnIndex(AGE_COL)));
        cursor.close();
        return person;
    }

    public List<Person> getAllPersonsFromAge(String age) {
        String selecttionArgs[] = {age};
        Cursor cursor = null;
        List<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = getReadableDatabase();

        try {
            cursor = db.query(TABLE_NAME, columns, AGE_COL + "=?", selecttionArgs,
                    null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                Person person = new Person(cursor.getString(cursor.getColumnIndex(NAME_COL)),
                        cursor.getInt(cursor.getColumnIndex(AGE_COL)));
                persons.add(person);
            } while (cursor.moveToNext());
        } catch (Exception e) {
            Log.e(TAG, "Error occured", e);
        }
        return persons;
    }

}
