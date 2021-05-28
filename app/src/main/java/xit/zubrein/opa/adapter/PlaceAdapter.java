package xit.zubrein.opa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import xit.zubrein.opa.PlaceDetailsActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelSearchPlace;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    List<ModelSearchPlace> list;
    Context c;

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txt;
        CardView parent;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.image);
            txt = itemView.findViewById(R.id.txt);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public PlaceAdapter(List<ModelSearchPlace> list, Context c) {
        this.list = list;
        this.c = c;


    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        PlaceViewHolder evh = new PlaceViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ModelSearchPlace currentitem = list.get(position);
        holder.txt.setText(currentitem.getPlace_name());
        Picasso.with(c)
                .load(currentitem.getPlace_image())
                .fit()
                .into(holder.img);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, PlaceDetailsActivity.class);
                intent.putExtra("place",currentitem.getPlace_name());
                intent.putExtra("imageurl",currentitem.getPlace_image());
                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}
