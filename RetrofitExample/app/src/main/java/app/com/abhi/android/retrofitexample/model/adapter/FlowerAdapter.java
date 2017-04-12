/*
 * Copyright (c) 2017. Abhilash Reddy
 */

package app.com.abhi.android.retrofitexample.model.adapter;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.com.abhi.android.retrofitexample.R;
import app.com.abhi.android.retrofitexample.model.Flower;

/**
 * Created by Abhilash Reddy on 4/10/2017.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    ArrayList<Flower> listflowers;

    FlowerInterface mflowerinterface;

   public FlowerAdapter(FlowerInterface flowerInterface){
        listflowers = new ArrayList<>();
       mflowerinterface = flowerInterface;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flower,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listflowers.size();
    }


    /**
     *
     * @param position
     * @return
     */
    public Flower getFlower(int position) {
        return listflowers.get(position);
    }



    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img = (ImageView) itemView.findViewById(R.id.imageitem_view);
        TextView name = (TextView) itemView.findViewById(R.id.nameitem_text_view);
        TextView price = (TextView) itemView.findViewById(R.id.price_text_view);

        public Holder(View itemView) {
            super(itemView);
        }

        public void bind(int position){
            Flower flower= listflowers.get(position);
            name.setText(flower.getName());
            price.setText(flower.getPrice()+"");
            Picasso.with(itemView.getContext()).load("http://services.hanselandpetal.com/photos/"+flower.getPhoto()).into(img);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
          //  Toast.makeText(itemView.getContext(), "toast on onclick", Toast.LENGTH_SHORT).show();
            mflowerinterface.onClick(getLayoutPosition());
        }
    }

    public interface FlowerInterface{
        public void onClick(int position);
    }

    public void add(List<Flower> flowerlist){
       for(int i=0;i<flowerlist.size();i++){
            listflowers.add(flowerlist.get(i));
           notifyDataSetChanged();
        }
    }

}
