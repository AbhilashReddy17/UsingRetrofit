/*
 * Copyright (c) 2017. Abhilash Reddy
 */

package app.com.abhi.android.retrofitexample.model.callbacks;

import java.util.List;

import app.com.abhi.android.retrofitexample.model.Flower;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Abhilash Reddy on 4/10/2017.
 */

public interface FlowerService {
    @GET("/feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();
}
