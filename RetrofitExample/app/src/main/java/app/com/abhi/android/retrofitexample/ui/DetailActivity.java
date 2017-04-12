/*
 * Copyright (c) 2017. Abhilash Reddy
 */

package app.com.abhi.android.retrofitexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.com.abhi.android.retrofitexample.R;
import app.com.abhi.android.retrofitexample.model.Flower;
import app.com.abhi.android.retrofitexample.model.helper.Constants;

/**
 * Created by Abhilash Reddy on 4/12/2017.
 */

public class DetailActivity extends AppCompatActivity {

    ImageView img;
    TextView name,id,price,category,instructions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.showflower);
        Intent intent  = getIntent();
        Flower flower = (Flower) intent.getSerializableExtra(Constants.REFERENCE.FLOWER);
        configviews();

        Picasso.with(getApplicationContext()).load("http://services.hanselandpetal.com/photos/"+flower.getPhoto()).into(img);
        id.setText(flower.getProductId()+"");
        price.setText(flower.getPrice()+"");
        category.setText(flower.getCategory());
        name.setText(flower.getName());
        instructions.setText(flower.getInstructions());

    }

    private void configviews() {
        img = (ImageView) findViewById(R.id.showflower_image_view);
        id = (TextView) findViewById(R.id.showflower_textview_id);
        price = (TextView) findViewById(R.id.showflower_textview_price);
        name = (TextView) findViewById(R.id.showflower_textview_name);
        category = (TextView) findViewById(R.id.showflower_textview_category);
        instructions = (TextView) findViewById(R.id.showflower_textview_instruction);

    }
}
