package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.adapter.MyBookingAdapter;
import xit.zubrein.opa.adapter.RoomListAdapter;
import xit.zubrein.opa.adapter.SectionsPagerAdapter;
import xit.zubrein.opa.fragments.FragmentMBPackages;
import xit.zubrein.opa.fragments.FragmentMBRooms;
import xit.zubrein.opa.fragments.FragmentPackages;
import xit.zubrein.opa.fragments.FragmentRooms;
import xit.zubrein.opa.model.ModelRoom;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class MyBookingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        setupViewPager();

    }

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentMBRooms()); //index
        adapter.addFragment(new FragmentMBPackages()); //index
        //index 2


        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("ROOMS");
        tabLayout.getTabAt(1).setText("PACKAGES");


    }
}