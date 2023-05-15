package com.example.administrator.ld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private DBHelper dbHelper;
    private SharedPreferences preferences;
    private int backgroundColor;
    private int fontSize;
    private int fontColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        preferences = getSharedPreferences("config", MODE_PRIVATE);
        backgroundColor = preferences.getInt("background_color", Color.WHITE);
        fontSize = preferences.getInt("font_size", 16);
        fontColor = preferences.getInt("font_color", Color.BLACK);
        listView = findViewById(R.id.list_view);
        dbHelper = new DBHelper(this);
        cursor = dbHelper.getReadableDatabase().query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, new String[]{DBHelper.NAME, DBHelper.GENDER, DBHelper.DEPARTMENT, DBHelper.SALARY}, new int[]{R.id.name, R.id.gender, R.id.department, R.id.salary}, 0);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
                    textView.setTextColor(fontColor);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        dbHelper.close();
    }
    @Override
    protected void onResume() {
        super.onResume();
        backgroundColor = preferences.getInt("background_color", Color.WHITE);
        fontSize = preferences.getInt("font_size", 16);
        fontColor = preferences.getInt("font_color", Color.BLACK);
        listView.setBackgroundColor(backgroundColor);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view instanceof TextView) {
                    TextView textView = (TextView) view;
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
                    textView.setTextColor(fontColor);
                    return true;
                }
                return false;
            }
        });
    }

    public void onConfigClick(View view) {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
    }


}
