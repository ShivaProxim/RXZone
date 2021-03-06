package com.rxzone.HomeActivity.CheckoutFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;
import com.rxzone.HomeActivity.HomeActivity;
import com.rxzone.HomeActivity.AllPostFragment.AllPostAdapter;
import com.rxzone.HomeActivity.AllPostFragment.AllPostData;
import com.rxzone.retrofitcall.ApiClient;
import com.rxzone.retrofitcall.ApiInterface;
import com.rxzone.rxzone.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROXIM on 2/7/2018.
 */

public class CheckoutFragment extends Fragment {
    View view;
    RecyclerView common_recyclerview_recycler;
    FragmentManager fragmentManager;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    AllPostAdapter allPostAdapter;
    ArrayList<AllPostData> _allPostData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.checkout_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Checkout");

        ((HomeActivity) getActivity()).changeToolBarText("Checkout");

        setHasOptionsMenu(true);
//        initializeViews();
//        retrofitInit();
        return view;
    }

    private void retrofitInit() {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClient(ApiClient.GET_ALL_POSTS_URL).create(ApiInterface.class);
        Call<ArrayList<AllPostData>> call = apiInterface.getAllPostsReq(ApiClient.GET_ALL_POSTS_URL);
        call.enqueue(new Callback<ArrayList<AllPostData>>() {
            @Override
            public void onResponse(Call<ArrayList<AllPostData>> call,
                                   Response<ArrayList<AllPostData>> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
//                allPostdata = response.body();
                _allPostData = new ArrayList<>();
                _allPostData = response.body();

                initializeRest(_allPostData);
                System.out.println("Size: " + _allPostData.size() + "");

                /*auctionCurrentOrUpcomingData = response.body().getCurrentAuctions();
                if (auctionCurrentOrUpcomingData.size() != 0 && !auctionCurrentOrUpcomingData.isEmpty()) {
                    initializeRest(auctionCurrentOrUpcomingData);
                    Log.e("AuctionCount", Count + "");
                } else {
                    nodataavailtxt.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onFailure(Call<ArrayList<AllPostData>> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    private void initializeViews() {
        common_recyclerview_recycler  = (RecyclerView) view.findViewById(R.id.common_recyclerview_recycler);
        fragmentManager = getActivity().getSupportFragmentManager();
        nodataavailtxt = (TextView) view.findViewById(R.id.nodataavailtxt);
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);
    }
    private void initializeRest(List<AllPostData> allPostdata) {
        ratingNowShowingLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        common_recyclerview_recycler.setLayoutManager(ratingNowShowingLayoutManager);
        allPostAdapter = new AllPostAdapter(getContext(), fragmentManager, allPostdata);
        common_recyclerview_recycler.setAdapter(allPostAdapter);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
      /*  switch (item.getItemId()) {
            *//*case R.id.wishList:
                // TODO put your code here to respond to the button tap
                Toast.makeText(getActivity(), "WishList", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.checkout:
                // TODO put your code here to respond to the button tap
                Toast.makeText(getActivity(), "CheckOut", Toast.LENGTH_SHORT).show();
                return true;*//*
            default:
                return */
        return super.onOptionsItemSelected(item);

    }
}
