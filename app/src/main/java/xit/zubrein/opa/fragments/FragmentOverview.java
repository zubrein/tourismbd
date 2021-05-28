package xit.zubrein.opa.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.R;
import xit.zubrein.opa.adapter.HospitalAdapter;
import xit.zubrein.opa.adapter.ReviewAdapter;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.model.ModelReview;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentOverview extends Fragment {

    String name = "", imageurl = "";
    ImageView image;
    TextView place_name,details;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ReviewAdapter mAdapter;
    SharedPreferences sharedPreferences;
    String user_id;
    EditText write_review;
    Button review_submit;
    RatingBar ratingBar;
    String review = "";
    float rating = 0;
    TextView list_text;

    public FragmentOverview(String name, String imageurl) {
        this.imageurl = imageurl;
        this.name = name;
    }


    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_overview, container, false);

        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");

        ratingBar = view.findViewById(R.id.ratingBar);
        list_text = view.findViewById(R.id.list_text);
        write_review = view.findViewById(R.id.write_review);
        review_submit = view.findViewById(R.id.review_submit);
        image = view.findViewById(R.id.image);
        place_name = view.findViewById(R.id.place_name);
        details = view.findViewById(R.id.details);
        recyclerView = view.findViewById(R.id.recyclerView);

        place_name.setText(name);

        Picasso.with(getContext())
                .load(imageurl)
                .fit()
                .into(image);

        get_overView();
        review_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review = write_review.getText().toString();
                rating = ratingBar.getRating();
                if(review.equals("")){
                    Toast.makeText(getContext(), "Please write something", Toast.LENGTH_SHORT).show();
                }else{
                    send_review(review,String.valueOf(rating));
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void get_overView() {
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelReview> call = service.get_overview(name);
        call.enqueue(new Callback<ModelReview>() {
            @Override
            public void onResponse(Call<ModelReview> call, Response<ModelReview> response) {
                if (response.isSuccessful()) {
                    ModelReview list = response.body();
                    details.setText(list.getDescription());
                    if(list.getList().size() > 0){
                        recyclerView.setVisibility(View.VISIBLE);
                        list_text.setVisibility(View.GONE);
                        recyclerView.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        mAdapter = new ReviewAdapter(list.getList(),getContext());
                        recyclerView.setAdapter(mAdapter);
                    }else {
                        list_text.setText("No reviews");
                        recyclerView.setVisibility(View.GONE);
                    }



                } else {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelReview> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void send_review(String review, String rating) {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponse> call = service.send_review(user_id,name,rating,review);
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if (response.isSuccessful()) {
                    ModelResponse modelResponse = response.body();
                    if(modelResponse.getResponse().equals("success")){
                        write_review.setText("");
                        get_overView();
                    }else{
                        Toast.makeText(getContext(), "Sending review failed", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
