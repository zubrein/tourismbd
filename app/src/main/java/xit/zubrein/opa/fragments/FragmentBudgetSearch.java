package xit.zubrein.opa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import xit.zubrein.opa.PlaceListActivity;
import xit.zubrein.opa.R;

public class FragmentBudgetSearch extends Fragment {


    Spinner spinnerDistrict;
    List<String> list_district = new ArrayList<>();
    String district="",budget="",stView = "";
    CardView btnSearch;
    EditText etBudget;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_budget_search, container, false);

        spinnerDistrict = view.findViewById(R.id.spinnerDistrict);
        btnSearch = view.findViewById(R.id.btnSearch);
        etBudget = view.findViewById(R.id.etBudget);
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


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budget = etBudget.getText().toString();
                if(district.equals("")){
                    Toast.makeText(getContext(), "Please select a district", Toast.LENGTH_SHORT).show();
                }else if(budget.equals("")){
                    Toast.makeText(getContext(), "Please enter your budget", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getContext(), PlaceListActivity.class);
                    intent.putExtra("district",district);
                    intent.putExtra("view",stView);
                    intent.putExtra("budget",budget);
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
        list_district.add("Chittagong");
        list_district.add("Dhaka");
        list_district.add("Sylhet");
        list_district.add("Khulna");
        list_district.add("Cumilla");
        list_district.add("Rangpur");
        list_district.add("Dinajpur");
        list_district.add("Rajshahi");
        list_district.add("Bogura");

    }

}
