package com.cosmo.arquitecturamvpbase.presenter;

import android.provider.ContactsContract;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.dao.ProductDao;
import com.cosmo.arquitecturamvpbase.helper.Database;
import com.cosmo.arquitecturamvpbase.model.Product;
import com.cosmo.arquitecturamvpbase.repository.MapperError;
import com.cosmo.arquitecturamvpbase.repository.ProductRepository;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.IProductView;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class ProductPresenter extends BasePresenter<IProductView> {

    private ProductRepository productRepository;
    private ProductDao productDao;

    public ProductPresenter() {
        productRepository = new ProductRepository();
    }

    public void getListProduct() {
        if (getValidateInternet().isConnected()) {
            createThreadProduct();
        } else {
            getView().showAlertDialogInternet(R.string.error, R.string.validate_internet);
        }
    }

    private void createThreadProduct() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //getProductList();
                ProductPresenter_getProductListDB();
            }
        });
        thread.start();
    }

    private void ProductPresenter_getProductListDB() {
        try {
            ArrayList<Product> productArrayList = Database.productDao.ProductInterfaceDao_fetchAllProducts();
            getView().showProductList(productArrayList);

        } catch (Exception ex) {
            getView().showAlertError(R.string.error, ex.getMessage());
        }
    }

    private void getProductList() {

        try {
            ArrayList<Product> productArrayList = productRepository.getProductList();
            getView().showProductList(productArrayList);

        } catch (RetrofitError retrofitError) {

            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }


}
