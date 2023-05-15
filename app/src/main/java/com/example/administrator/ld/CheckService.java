package com.example.administrator.ld;


import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.IBinder;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Provider;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Handler;
import android.support.annotation.Nullable;


public class CheckService extends Service {
    private DBHelper dbHelper;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable()
    {
        @Override
        public void run() { SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, DBHelper.SALARY + "<0", null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID));
                ContentValues values = new ContentValues();
                values.put(DBHelper.SALARY, (Float) null);
                getContentResolver().update(Uri.parse("content://com.example.staffprovider/staff"), values, DBHelper.ID +"=?" ,new String[]{String.valueOf(id)});
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = sdf.format(new Date());
                            String log ="ID:"+id+"时间："+time;
                            writeLog(log); }
                            while (cursor.moveToNext()); }
                            cursor.close();
        handler.postDelayed(runnable, 5000); } };

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.postDelayed(runnable, 5000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        dbHelper.close();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void writeLog(String log) {
        try {
            File file = new File(getExternalFilesDir(null), "check.log");
            FileWriter writer = new FileWriter(file, true);
            writer.write(log + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}