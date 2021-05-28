package xit.zubrein.opa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.R;
import xit.zubrein.opa.adapter.GalleryAdapter;
import xit.zubrein.opa.model.ModelGallery;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentGallery extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    GalleryAdapter mAdapter;
    String place;
    GridView simpleList;

    public FragmentGallery(String place) {
        this.place = place;
    }

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        simpleList = (GridView) view.findViewById(R.id.simpleGridView);
        get_gallery();
        

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void get_gallery() {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelGallery>> call = service.get_gallery(place);
        call.enqueue(new Callback<List<ModelGallery>>() {
            @Override
            public void onResponse(Call<List<ModelGallery>> call, Response<List<ModelGallery>> response) {
                if (response.isSuccessful()) {
                    List<ModelGallery> list = response.body();
                    mAdapter=new GalleryAdapter(getContext(),list);
                    simpleList.setAdapter(mAdapter);


                } else {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelGallery>> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
