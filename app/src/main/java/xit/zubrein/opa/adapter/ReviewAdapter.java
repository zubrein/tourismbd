package xit.zubrein.opa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelReview;
import xit.zubrein.opa.model.ModelReview.ReviewItem;
import xit.zubrein.opa.model.ModelReview.ReviewItem;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.PoliceStationViewHolder> {

    List<ModelReview.ReviewItem> list;
    Context c;

    public static class PoliceStationViewHolder extends RecyclerView.ViewHolder {
        public TextView name,review;
        RatingBar ratingBar;
        CardView parent;

        public PoliceStationViewHolder(@NonNull View itemView) {
            super(itemView);

            ratingBar = itemView.findViewById(R.id.ratingBar);
            review = itemView.findViewById(R.id.review);
            name = itemView.findViewById(R.id.name);
        }
    }

    public ReviewAdapter(List<ModelReview.ReviewItem> list, Context c) {
        this.list = list;
        this.c = c;


    }

    @NonNull
    @Override
    public PoliceStationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        PoliceStationViewHolder evh = new PoliceStationViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull PoliceStationViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ModelReview.ReviewItem currentitem = list.get(position);
        holder.name.setText(currentitem.getUser_name());
        holder.review.setText(currentitem.getUser_review());
        holder.ratingBar.setRating(Float.parseFloat(currentitem.getUser_rating()));

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}
