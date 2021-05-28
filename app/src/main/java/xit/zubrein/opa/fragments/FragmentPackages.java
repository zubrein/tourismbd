package xit.zubrein.opa.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.R;
import xit.zubrein.opa.adapter.PackageListAdapter;
import xit.zubrein.opa.adapter.RoomListAdapter;
import xit.zubrein.opa.model.ModelPackage;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentPackages extends Fragment {

    String id,user_id;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    PackageListAdapter mAdapter;


    public FragmentPackages(String id) {
        this.id = id;
    }


    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_hotel_room, container, false);

        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");

        recyclerView = view.findViewById(R.id.recyclerView);

        packageList();


        return view;
    }

    public void packageList() {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelPackage>> call = service.package_list(id);
        call.enqueue(new Callback<List<ModelPackage>>() {
            @Override
            public void onResponse(Call<List<ModelPackage>> call, Response<List<ModelPackage>> response) {
                if (response.isSuccessful()) {
                    List<ModelPackage> list = response.body();
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new PackageListAdapter(list,getContext(),id);
                    recyclerView.setAdapter(mAdapter);


                } else {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelPackage>> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
