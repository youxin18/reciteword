package com.example.reciteword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.initWordList();
        CreateDbHelper dbHelper = new CreateDbHelper(this, "user.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "Select*from recite where username=?";
        Cursor cursor = db.rawQuery(sql, new String[]{LoginUserPwdActivity.input_id});
        if (cursor.moveToFirst()) {
            do {
                Toast.makeText(this, "找到了！", Toast.LENGTH_SHORT).show();
                 aa = cursor.getString(cursor.getColumnIndex("username"));
            } while (cursor.moveToNext());
        }
        cursor.close();
     if (aa!=null){
        Log.d("Union","not null");
        Intent intent = new Intent(MainActivity.this,InterfaceActivity.class);
        startActivity(intent);
        finish();
     }else {
         Log.d("Union","insert data");
         CreateDbHelper createDbHelper=new CreateDbHelper(this,"user.db",null,1);
         SQLiteDatabase db1=createDbHelper.getWritableDatabase();
         ContentValues values=new ContentValues();
         for (int i=0;i<10;i++){
             Log.d("Union","id:"+LoginUserPwdActivity.input_id);
             values.put("username",LoginUserPwdActivity.input_id);
             values.put("word",Data.getWord(i));
             values.put("pron",Data.getPron(i));
             values.put("definition",Data.getwordDefine(i));
             db1.insert("recite",null,values);
             db1.insert("brush",null,values);
             Log.d("Union","create table:"+"recite");
             Log.d("Union"," create table:"+"brush");
             values.clear();
         }
         Toast.makeText(this,"导入成功！", Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(MainActivity.this,InterfaceActivity.class);
         startActivity(intent);
         finish();
     }
    }

}