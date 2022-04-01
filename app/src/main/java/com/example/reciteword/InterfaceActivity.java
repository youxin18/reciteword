package com.example.reciteword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class InterfaceActivity extends AppCompatActivity {
    String word;
    String pron;
    String definition;
    public static List wordlist;
    int showNum;
    int dailyplan;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Data.initWordList();
        setContentView(R.layout.activity_interface);
        getWordInfo(LoginUserPwdActivity.input_id);
        BottomNavigationView navView =(BottomNavigationView) findViewById(R.id.nav_view);

        navView.setItemIconTintList(null);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_recite, R.id.navigation_brush, R.id.navigation_fight,
                R.id.navigation_wrong).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        System.out.println("there is Interface");
    }
    private void getWordInfo(String username) {
        CreateDbHelper dbHelper = new CreateDbHelper(this, "user.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "Select*from recite where username=?";
        Log.d("Unionword",username);
        // Cursor cursor = db.query("recite",null,username+"=?",new String[]{LoginUserPwdActivity.input_id},null,null,null,"0,10");
        Cursor cursor=db.rawQuery(sql,new String[]{username});
        wordlist = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                Toast.makeText(this, "找到了！", Toast.LENGTH_SHORT).show();
                word = cursor.getString(cursor.getColumnIndex("word"));
                Log.d("Unionword",word);
                pron = cursor.getString(cursor.getColumnIndex("pron"));
                definition = cursor.getString(cursor.getColumnIndex("definition"));
                if (word == null || pron == null || definition == null) {
                    return;
                } else {
                    wordlist.add(word);

                }
                Log.d("Union", "word message:" + word + pron + definition + showNum);
            } while (cursor.moveToNext());
        }
        Log.d("Union", "wordlist:" + wordlist.size());
        cursor.close();
    }


}