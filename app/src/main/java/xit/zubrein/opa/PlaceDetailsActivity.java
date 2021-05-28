package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import xit.zubrein.opa.adapter.SectionsPagerAdapter;
import xit.zubrein.opa.fragments.FragmentGallery;
import xit.zubrein.opa.fragments.FragmentHospitals;
import xit.zubrein.opa.fragments.FragmentHotels;
import xit.zubrein.opa.fragments.FragmentOverview;
import xit.zubrein.opa.fragments.FragmentPolicestations;
import xit.zubrein.opa.fragments.FragmentRestaurants;
import xit.zubrein.opa.fragments.FragmentTravelPath;

public class PlaceDetailsActivity extends AppCompatActivity {

    String place,imageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        place = getIntent().getStringExtra("place");
        imageurl = getIntent().getStringExtra("imageurl");

        setupViewPager();

    }

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOverview(place,imageurl)); //index 0
        adapter.addFragment(new FragmentHotels(place));
        adapter.addFragment(new FragmentRestaurants(place));
        adapter.addFragment(new FragmentHospitals(place));
        adapter.addFragment(new FragmentTravelPath(place));
        adapter.addFragment(new FragmentPolicestations(place));
        adapter.addFragment(new FragmentGallery(place));
        //index 2


        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Overview & Review");
        tabLayout.getTabAt(1).setText("Hotels");
        tabLayout.getTabAt(2).setText("Restaurants");
        tabLayout.getTabAt(3).setText("Hospitals");
        tabLayout.getTabAt(4).setText("Travel Path");
        tabLayout.getTabAt(5).setText("Police stations");
        tabLayout.getTabAt(6).setText("Gallery");


    }
}
