package com.yu.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testDBHelper(this);
        testReadDB(this);
    }

    private void testReadDB(Context context) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name,age from info where _id=?", new String[]{String.valueOf(1)});
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            Log.e("Main", "name" + name + ",age=" + age);
            Toast.makeText(context, "name=" + name + ",age=" + age, Toast.LENGTH_SHORT).show();

        }
        cursor.close();
        db.close();
    }

    private void testDBHelper(Context context) {
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues(); // 使用ContentValues存储数据
        values.put("age", 19);
        values.put("name", "niko");
        db.insert("info", null, values); // 向数据库插入数据
        db.close();
    }
}
