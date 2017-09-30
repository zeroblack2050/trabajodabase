package com.cosmo.arquitecturamvpbase.helper;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class Constants {

    //WEB SERVICES

    public final static String URL_BASE = "https://shoppingproducts.herokuapp.com";
    public final static int TIME_OUT = 6;
    public final static String ITEM_PRODUCT = "Itemproduct";
    public static final String REQUEST_TIMEOUT_ERROR_MESSAGE = "La solicitud está tardando demasiado. Por favor inténtalo nuevamente.";
    public static final int DEFAULT_ERROR_CODE = 0;
    public static final String DEFAULT_ERROR = "Ha ocurrido un error, intentalo nuevamente.";
    public static final int UNAUTHORIZED_ERROR_CODE = 401;
    public static final int NOT_FOUND_ERROR_CODE = 404;

    //DATABASE
    public static final  String DATABASE_NAME = "shopping_class_db.db";
    public static final  int DATABASE_VERSION = 1;

}
