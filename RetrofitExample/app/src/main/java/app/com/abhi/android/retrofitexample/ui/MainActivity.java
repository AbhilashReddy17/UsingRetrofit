/*
 * Copyright (c) 2017. Abhilash Reddy
 */

package app.com.abhi.android.retrofitexample.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import app.com.abhi.android.retrofitexample.R;
import app.com.abhi.android.retrofitexample.controller.RestManager;
import app.com.abhi.android.retrofitexample.model.Flower;
import app.com.abhi.android.retrofitexample.model.adapter.FlowerAdapter;
import app.com.abhi.android.retrofitexample.model.helper.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FlowerAdapter.FlowerInterface {

    RecyclerView mrecyclerView;
    FlowerAdapter mflowerAdapter;
    private RestManager mrestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureViews();

        mrestManager = new RestManager();
      Call< List<Flower>> listCAll = mrestManager.getFlowerService().getAllFlowers();

        listCAll.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                List<Flower> flowerslist = response.body();
                if(flowerslist != null)
              mflowerAdapter.add(flowerslist);
                else{
                    int sc = response.code();
                    switch(sc){

                        case 449:
                            Toast.makeText(MainActivity.this,"request failed",Toast.LENGTH_SHORT);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                Log.e(MainActivity.class.getSimpleName(), t.getLocalizedMessage());
            }
        });
        
    }

    private void configureViews() {
        mrecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mflowerAdapter = new FlowerAdapter(this);

        mrecyclerView.setAdapter(mflowerAdapter);
    }

    @Override
    public void onClick(int position) {
       Flower selectedFlower = mflowerAdapter.getFlower(position);
      //  Toast.makeText(this, selectedFlower.getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra(Constants.REFERENCE.FLOWER,selectedFlower);
        startActivity(intent);
    }
}
