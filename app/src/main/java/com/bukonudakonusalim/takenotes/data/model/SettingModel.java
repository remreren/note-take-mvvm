package com.bukonudakonusalim.takenotes.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bukonudakonusalim.takenotes.utils.DatabaseController;

import java.util.Locale;

import logme.log.Logme;

import static com.bukonudakonusalim.takenotes.utils.DatabaseController.*;

public class SettingModel {

    private String key;
    private String value;

    public SettingModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void save(DatabaseController controller) {
        ContentValues cv = new ContentValues();
        cv.put(OPTIONS_KEY, String.format(Locale.getDefault(), "%s", key));
        cv.put(OPTIONS_VALUE, String.format(Locale.getDefault(), "%s", value));
        SQLiteDatabase db = controller.getWritableDatabase();
        db.insertWithOnConflict(TABLE_OPTIONS_NAME, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public static SettingModel getSetting(DatabaseController controller, String key) {
        SQLiteDatabase db = controller.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT " +
                OPTIONS_KEY + ", " +
                OPTIONS_VALUE + " FROM " +
                TABLE_OPTIONS_NAME + " WHERE " +
                OPTIONS_KEY + " = '" +
                key + "';", null);

        Logme.wtf("%d", cs.getCount());
        if (cs.moveToFirst()) {
            SettingModel setting = new SettingModel(cs.getString(cs.getColumnIndex(OPTIONS_KEY)), cs.getString(cs.getColumnIndex(OPTIONS_VALUE)));
            cs.close();
            return setting;
        } else {
            cs.close();
            return null;
        }
    }
}
