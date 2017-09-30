package com.cosmo.arquitecturamvpbase.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.schemes.ProductInterfaceScheme;

/**
 * Created by Superadmin1 on 30/09/2017.
 */

class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductInterfaceScheme.PRODUCT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Constants.DATABASE_NAME, " update version to: "+ newVersion);
        db.execSQL("DROP TABLE IF EXISTS"+ ProductInterfaceScheme.PRODUCT_TABLE);
    }
}
