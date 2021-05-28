package xit.zubrein.opa.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.HomeActivity;
import xit.zubrein.opa.LoginActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentTravelPath extends Fragment {

    String place;
    TextView travelpath;

    public FragmentTravelPath(String place) {
        this.place = place;
    }

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_travelpath, container, false);

        travelpath = view.findViewById(R.id.travelpath);
        getTravelPath();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void getTravelPath(){
        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponse> call = service.travel_path(place);
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if(response.isSuccessful()){
                    ModelResponse model = response.body();
                    travelpath.setText(model.getResponse());


                }else{
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
