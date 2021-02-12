package com.example.valoranttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.valoranttracker.models.HistoryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String  DATABASE_NAME = "ValorantTrackerDB";
    public static final String TABLE_NAME = "history";
    public static final String HISTORY_ID_COLUMN = "id";
    public static final String HISTORY_PLAYER_ID_COLUMN = "playerId";
    public static final String HISTORY_GAME_NAME_COLUMN = "gameName";
    public static final String HISTORY_TAG_COLUMN = "tag";

    private Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table history" +
                "(id integer primary key autoincrement, gameName text, tag text, playerId text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
        onCreate(sqLiteDatabase);
    }

    public boolean insertHistory(String gameName, String tag, String region) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HISTORY_GAME_NAME_COLUMN, gameName);
        contentValues.put(HISTORY_TAG_COLUMN, tag);
        long x = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

//        Toast.makeText(context, "Data Added! " + x + " " + gameName, Toast.LENGTH_SHORT).show();
        return true;
    }

    public Integer deleteLast() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT MAX(_id) FROM history", null);
        return deleteHistory(res.getInt(res.getColumnIndex(HISTORY_ID_COLUMN)));
    }


    public Integer deleteHistory(String timestamp) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("history", "timestamp = ?", new String[] { timestamp});
    }

    public Integer deleteHistory(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("history", "id = ?", new String[] { Integer.toString(id)});
    }

    public Cursor getData(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from history where id=" + id + "", null);
        return(res);
    }

    public List<HistoryModel> getHistory() {

        List<HistoryModel> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from history", null);
        res.moveToFirst();

        while(!res.isAfterLast()) {
            HistoryModel historyData = new HistoryModel();
            historyData.setGameName(res.getString(res.getColumnIndex(HISTORY_GAME_NAME_COLUMN)));
            historyData.setTag(res.getString(res.getColumnIndex(HISTORY_TAG_COLUMN)));
            historyData.setPlayerId(res.getString(res.getColumnIndex(HISTORY_PLAYER_ID_COLUMN)));
            arrayList.add(historyData);
            res.moveToNext();
        }

        Collections.reverse(arrayList);

//        Toast.makeText(context, "size in helper " + arrayList.size(), Toast.LENGTH_SHORT).show();
        return arrayList;
    }
}
