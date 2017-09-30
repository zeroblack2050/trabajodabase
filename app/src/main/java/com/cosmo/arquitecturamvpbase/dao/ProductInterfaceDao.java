package com.cosmo.arquitecturamvpbase.dao;

import com.cosmo.arquitecturamvpbase.model.Product;

import java.util.ArrayList;

/**
 * Created by Superadmin1 on 30/09/2017.
 */

public interface ProductInterfaceDao {

    public ArrayList<Product> ProductInterfaceDao_fetchAllProducts();
    public boolean ProductInterfaceDao_CreateProduct(Product product);
    public boolean ProductInterfaceDao_deleteProduct(String id);


}
