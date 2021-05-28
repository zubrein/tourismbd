package xit.zubrein.opa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.HomeActivity;
import xit.zubrein.opa.PlaceListActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentSearch extends Fragment {


    Spinner spinnerDistrict,spinnerView;
    List<String> list_district = new ArrayList<>();
    List<String> list_view = new ArrayList<>();
    String district="",stView="";
    CardView btnSearch;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        spinnerDistrict = view.findViewById(R.id.spinnerDistrict);
        spinnerView = view.findViewById(R.id.spinnerView);
        btnSearch = view.findViewById(R.id.btnSearch);
        init_data();

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list_district);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(districtAdapter);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                district = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                district = "Chittagong";
            }
        });

        ArrayAdapter<String> ViewAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list_view);
        ViewAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerView.setAdapter(ViewAdapter);

        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stView = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                stView = "";
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(district.equals("")){
                    Toast.makeText(getContext(), "Please select a district", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getContext(), PlaceListActivity.class);
                    intent.putExtra("district",district);
                    intent.putExtra("view",stView);
                    startActivity(intent);
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void init_data(){
        list_district.add("Any");
        list_district.add("Chittagong");
        list_district.add("Dhaka");
        list_district.add("Sylhet");
        list_district.add("Khulna");
        list_district.add("Cumilla");
        list_district.add("Rangpur");
        list_district.add("Dinajpur");
        list_district.add("Rajshahi");
        list_district.add("Bogura");


        list_view.add("Any");
        list_view.add("Hill");
        list_view.add("Sea");
        list_view.add("Waterfall");
        list_view.add("Historical");
        list_view.add("Lake");
        list_view.add("Nature");
        list_view.add("Museum");
        list_view.add("Park");
        list_view.add("River");

    }

}
