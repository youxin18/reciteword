package com.example.reciteword;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class CreateReciteWordHelper extends SQLiteOpenHelper {
    private Context mContext;


    public CreateReciteWordHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(new StringBuilder().append("create table ").append("recite"+LoginUserPwdActivity.input_id).append("(").append("username text,").append("word text,").append("pron text,").append("definition text,").append("showNum Integer,").append("flag Integer)").toString());
        Toast.makeText(mContext,"Create succeeded", Toast.LENGTH_SHORT).show();
        Log.d("Union","Create succeeded");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
