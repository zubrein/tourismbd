package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import xit.zubrein.opa.adapter.SectionsPagerAdapter;
import xit.zubrein.opa.fragments.FragmentBudgetSearch;
import xit.zubrein.opa.fragments.FragmentPackages;
import xit.zubrein.opa.fragments.FragmentRooms;
import xit.zubrein.opa.fragments.FragmentSearch;

public class HotelActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        id = getIntent().getStringExtra("id");

        setupViewPager();

    }

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentRooms(id)); //index
        adapter.addFragment(new FragmentPackages(id)); //index
        //index 2


        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("ROOMS");
        tabLayout.getTabAt(1).setText("PACKAGES");


    }
}