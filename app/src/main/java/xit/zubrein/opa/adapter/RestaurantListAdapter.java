package xit.zubrein.opa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelRestaurant;


public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    List<ModelRestaurant> list;
    Context c;

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txt;
        CardView parent;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.image);
            txt = itemView.findViewById(R.id.txt);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public RestaurantListAdapter(List<ModelRestaurant> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        RestaurantViewHolder evh = new RestaurantViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ModelRestaurant currentitem = list.get(position);
        holder.txt.setText(currentitem.getRes_name());
        Picasso.with(c)
                .load(currentitem.getRes_image())
                .fit()
                .into(holder.img);



    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}
