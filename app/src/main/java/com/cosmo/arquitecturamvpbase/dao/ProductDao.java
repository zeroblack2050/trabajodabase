package com.cosmo.arquitecturamvpbase.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.prividers.DbContentProvider;
import com.cosmo.arquitecturamvpbase.schemes.ProductInterfaceScheme;
import java.util.ArrayList;

/**
 * Created by Superadmin1 on 30/09/2017.
 */

public class ProductDao extends DbContentProvider implements ProductInterfaceScheme, ProductInterfaceDao {

    private Cursor cursor;
    private ContentValues initialValues;

    public  ProductDao(SQLiteDatabase db){
        super(db);
    }

    @Override
    public ArrayList<Product> ProductInterfaceDao_fetchAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        cursor = super.query(PRODUCT_TABLE,PRODUCT_COLUMNS,null,null,COLUMN_PRODUCT_NAME);
        if (cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Product product = DbContentProvider_cursorToEntity(cursor);
                productList.add(product);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return productList;
    }

    @Override
    public boolean ProductInterfaceDao_CreateProduct(Product product) {
        ProductDao_setContentValueProduct(product);
        try {
            return super.insert(PRODUCT_TABLE, ProductDao_getContentValue()) >= 0;
            /*
            **
            long totalInserted= super.insert(PRODUCT_TABLE, ProductDao_getContentValue());
            if (totalInserted == -1){
                return false;
            }
            return true;
            *
            * */

        }catch (SQLiteConstraintException ex){
            Log.e("DbErrorCreateProduct", ex.getMessage());
            return false;
        }


    }

    private void ProductDao_setContentValueProduct(Product product) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_ID, product.getId());
        initialValues.put(COLUMN_PRODUCT_NAME, product.getName());
        initialValues.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        initialValues.put(COLUMN_PRODUCT_QUANTITY, product.getQuantity());
        initialValues.put(COLUMN_PRODUCT_PRICE, product.getPrice());
    }

    private ContentValues ProductDao_getContentValue() {
        return initialValues;
    }


    @Override
    protected Product DbContentProvider_cursorToEntity(Cursor cursor) {
        Product product = new Product();
        int idIndex;
        int nameIndex;
        int descriptionIndex;
        int quantityIndex;
        int priceIndex;

        if (cursor.getColumnIndex(COLUMN_ID) != -1){
            idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            product.setId(cursor.getString(idIndex));
        }
        if (cursor.getColumnIndex(COLUMN_PRODUCT_NAME) != -1){
            nameIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME);
            product.setName(cursor.getString(nameIndex));
        }
        if (cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION) != -1){
            descriptionIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION);
            product.setDescription(cursor.getString(descriptionIndex));
        }
        if (cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY) != -1){
            quantityIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_QUANTITY);
            product.setQuantity(cursor.getString(quantityIndex));
        }
        if (cursor.getColumnIndex(COLUMN_PRODUCT_PRICE) != -1){
            priceIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE);
            product.setPrice(cursor.getString(priceIndex));
        }

        return product;
    }

    @Override
    public boolean ProductInterfaceDao_deleteProduct(Product product) {
        super.delete(PRODUCT_TABLE,product.getId(), PRODUCT_COLUMNS);
        return false;
    }
}
