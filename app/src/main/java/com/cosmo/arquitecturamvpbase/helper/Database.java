package com.cosmo.arquitecturamvpbase.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.dao.ProductDao;

/**
 * Created by Superadmin1 on 30/09/2017.
 */

public class Database {

    private  final Context context;
    private DatabaseHelper dbeHelper;

    //DAO'S
    public static ProductDao productDao;


    public Database(Context context) {
        this.context = context;
    }

    public Database Database_openConnection(){
        try{
            dbeHelper = new DatabaseHelper(context);
            SQLiteDatabase sqLiteDatabasedb = dbeHelper.getWritableDatabase();
            productDao = new ProductDao(sqLiteDatabasedb);
            return this;

        }catch (SQLException ex){
            Log.e("SQL Open", ex.getMessage());
            throw ex;
        }
    }

    public void Database_closeConnection(){
        dbeHelper.close();
    }



}
