package com.example.tasbeehcounter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tasbeehcounter.model.Counter;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context ) {
        super(context, "counter", null , 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE tbl_Counter (zikr TEXT , Counter INTEGER) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE tbl_Counter");
        onCreate(db);
        
    }

    public long addCounter(Counter counter)
    {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("zikr", counter.getZikr());
        values.put("Counter",counter.getCounts());

        long result = db.insert("Tbl_Counter",null, values);

        return result;


    }

    public ArrayList<Counter> grabAllCounter(){

        ArrayList<Counter> counters =new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from tbl_Counter",null);


        if (cursor.moveToFirst()){

            do {

                String zikr =cursor.getString(0);
                int counts = cursor.getInt(1);

                Counter counter = new Counter(zikr,counts);
                counters.add(counter);



            }while (cursor.moveToNext());
        }


        return counters;
    }

    public int deleteCounter(Counter counter) {

        SQLiteDatabase db = getWritableDatabase();
        return  db.delete("tbl_counter","zikr=?",new String[]{String.valueOf(counter.getZikr())});
    }
}
