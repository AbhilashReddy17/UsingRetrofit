/*
 * Copyright (c) 2017. Abhilash Reddy
 */

package app.com.abhi.android.retrofitexample.controller;

import app.com.abhi.android.retrofitexample.model.callbacks.FlowerService;
import app.com.abhi.android.retrofitexample.model.helper.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhilash Reddy on 4/10/2017.
 */

public class RestManager {

    private FlowerService mflowerService;

    public FlowerService getFlowerService(){
        if(mflowerService == null){
            Retrofit retrofit =new Retrofit.Builder().
                    baseUrl(Constants.HTTP.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
            mflowerService = retrofit.create(FlowerService.class);

        }
        return mflowerService;
    }
}
