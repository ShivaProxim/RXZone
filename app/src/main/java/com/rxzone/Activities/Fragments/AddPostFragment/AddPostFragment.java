package com.rxzone.Activities.Fragments.AddPostFragment;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.leo.simplearcloader.SimpleArcLoader;
import com.rxzone.Activities.DashBoardActivity.HomeActivity;
import com.rxzone.Activities.Fragments.AllPostFragment.AllPostAdapter;
import com.rxzone.Activities.Fragments.AllPostFragment.AllPostData;
import com.rxzone.model.CommonDropDownData;
import com.rxzone.retrofitcall.ApiClient;
import com.rxzone.retrofitcall.ApiInterface;
import com.rxzone.rxzone.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROXIM on 2/7/2018.
 */

public class AddPostFragment extends Fragment {
    View view;
    RecyclerView common_recyclerview_recycler;
    FragmentManager fragmentManager;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    AllPostAdapter allPostAdapter;
    ArrayList<AllPostData> _allPostData;
    Spinner packquatyspn, prductOpt, packConspn, groundspn, shippingspn;


    ArrayList<String> commmonForAllTitles = new ArrayList<String>();

    ArrayList<String> packageQtyTitles = new ArrayList<String>();
    ArrayList<CommonDropDownData> _packageQtyData = new ArrayList<CommonDropDownData>();
    String packageQtySelectedId = "";

    ArrayList<String> prductOptTitles = new ArrayList<String>();
    ArrayList<CommonDropDownData> _prductOptData = new ArrayList<CommonDropDownData>();
    String prductOptSelectedId = "";

    ArrayList<String> packConTitles = new ArrayList<String>();
    ArrayList<CommonDropDownData> _packConData = new ArrayList<CommonDropDownData>();
    String packConSelectedId = "";

    ArrayList<String> groundTitles = new ArrayList<String>();
    ArrayList<CommonDropDownData> _groundData = new ArrayList<CommonDropDownData>();
    String groundSelectedId = "";

    ArrayList<String> shippingTitles = new ArrayList<String>();
    ArrayList<CommonDropDownData> _shippingData = new ArrayList<CommonDropDownData>();
    String shippingSelectedId = "";

    int spinnerPositionNo = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_post_activity, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((HomeActivity) getActivity()).changeToolBarText("Add Post");
        setHasOptionsMenu(true);
        initializeViews();

        retrofitInit(1, ApiClient.SHIPING_METHOD_DROP_DATA_URL);
        retrofitInit(2, ApiClient.SHIPING_METHOD_DROP_DATA_URL);
        retrofitInit(3, ApiClient.PACKAGE_CON_DROP_DATA_URL);
        retrofitInit(4, ApiClient.GROUND_SHIPPING_DROP_DATA_URL);
        retrofitInit(5, ApiClient.SHIPING_METHOD_DROP_DATA_URL);


        return view;
    }

    private void retrofitInit(final int spinnerPositionNolocal, final String CALL_URL) {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClientData().create(ApiInterface.class);
        Call<ArrayList<CommonDropDownData>> call = apiInterface.shippingDropDownReq(CALL_URL);
        call.enqueue(new Callback<ArrayList<CommonDropDownData>>() {
            @Override
            public void onResponse(Call<ArrayList<CommonDropDownData>> call,
                                   Response<ArrayList<CommonDropDownData>> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                dataAddingToAdapter(response.body(), spinnerPositionNolocal);
            }

            @Override
            public void onFailure(Call<ArrayList<CommonDropDownData>> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    //Spinner number given based on design top to bottom
    private void dataAddingToAdapter(ArrayList<CommonDropDownData> dropDownArrayData, int spinnernumber) {
        try {
            _packageQtyData.clear();
            commmonForAllTitles.clear();
            _packageQtyData = new ArrayList<CommonDropDownData>();
            for (int i = 0; i < dropDownArrayData.size(); i++) {
                String id = dropDownArrayData.get(i).get_id();
                String title = dropDownArrayData.get(i).getName();
                _packageQtyData.add(new CommonDropDownData(id, title));
            }
            commmonForAllTitles.add("Select");
            if (_packageQtyData.size() > 0) {
                for (int i = 0; i < _packageQtyData.size(); i++) {
                    commmonForAllTitles.add(_packageQtyData.get(i).getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapterDataAssigingToSpinner(commmonForAllTitles, spinnernumber);
    }

    private void initializeViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);

        packquatyspn = (Spinner) view.findViewById(R.id.packquatyspn);
        prductOpt = (Spinner) view.findViewById(R.id.prductOpt);
        packConspn = (Spinner) view.findViewById(R.id.packConspn);
        groundspn = (Spinner) view.findViewById(R.id.groundspn);
        shippingspn = (Spinner) view.findViewById(R.id.shippingspn);

    }

    private void initializeRest(List<AllPostData> allPostdata) {

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


    private void adapterDataAssigingToSpinner(ArrayList<String> spinnerTitles, int spinnerSelction) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinnerTitles);
        dataAdapter.setDropDownViewResource(R.layout.list_item);
        if (spinnerSelction == 1) {
            ArrayAdapter<String> zonedataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, spinnerTitles);
            zonedataAdapter.setDropDownViewResource(R.layout.list_item);
            packquatyspn.setAdapter(zonedataAdapter);
        } else if (spinnerSelction == 2) {
            ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, spinnerTitles);
            routeAdapter.setDropDownViewResource(R.layout.list_item);
            prductOpt.setAdapter(routeAdapter);
        } else if (spinnerSelction == 3) {
            packConspn.setAdapter(dataAdapter);
        } else if (spinnerSelction == 4) {
            groundspn.setAdapter(dataAdapter);
        } else if (spinnerSelction == 5) {
            shippingspn.setAdapter(dataAdapter);
        }


    }
}
