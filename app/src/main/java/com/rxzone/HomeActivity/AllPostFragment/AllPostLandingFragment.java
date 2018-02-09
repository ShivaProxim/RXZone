package com.rxzone.HomeActivity.AllPostFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.simplearcloader.SimpleArcLoader;
import com.rxzone.retrofitcall.ApiInterface;
import com.rxzone.rxzone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PROXIM on 2/7/2018.
 */

public class AllPostLandingFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    View view;
    RecyclerView common_recyclerview_recycler;
    FragmentManager fragmentManager;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    AllPostAdapter allPostAdapter;
    ArrayList<AllPostData> _allPostData;

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.allpost_fragment, container, false);
        initializeViews();
//        retrofitInit();
        return view;
    }


    private void initializeViews() {
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        // Add Fragments to adapter one by one
        adapter.addFragment(new AllPostListFragment(), "All Post");
        adapter.addFragment(new AllPostListFragment(), "Top Post");
        adapter.addFragment(new AllPostListFragment(), "Top Sale");
        adapter.addFragment(new AllPostListFragment(), "Top MFR");
        adapter.addFragment(new AllPostListFragment(), "Top Buyers");

        mViewPager.setOffscreenPageLimit(3);

        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);


        tabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        tabLayout.setTabTextColors(getResources().getColor(R.color.grey), getResources().getColor(R.color.colorAccent1));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent1));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));//);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
        Toast.makeText(getContext(), "Tab Selected" + tab.getPosition(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
