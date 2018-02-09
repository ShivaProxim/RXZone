package com.rxzone.HomeActivity.AddPostFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.simplearcloader.SimpleArcLoader;
import com.rxzone.HomeActivity.HomeActivity;
import com.rxzone.HomeActivity.AllPostFragment.AllPostAdapter;
import com.rxzone.HomeActivity.AllPostFragment.AllPostData;
import com.rxzone.Util.DatePickerFragmentDailoge;
import com.rxzone.Util.SharedPrefsUtil;
import com.rxzone.model.CommonDropDownData;
import com.rxzone.retrofitcall.ApiClient;
import com.rxzone.retrofitcall.ApiInterface;
import com.rxzone.rxzone.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROXIM on 2/7/2018.
 */

public class AddPostFragment extends Fragment implements View.OnClickListener {
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

    TextView expirationdatetxt;

    EditText ndcnoEt, lotnoEt, packageavailEt, wacpriceEt, wacdiscountEt, packpriceEt;
    Button resetBtn, submitBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_post_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((HomeActivity) getActivity()).changeToolBarText("Add Post");
        setHasOptionsMenu(true);
        initializeViews();
        initializeActions();


        retrofitInit(1, ApiClient.SHIPING_METHOD_DROP_DATA_URL);
        retrofitInit(2, ApiClient.SHIPING_METHOD_DROP_DATA_URL);
        retrofitInit(3, ApiClient.PACKAGE_CON_DROP_DATA_URL);
        retrofitInit(4, ApiClient.GROUND_SHIPPING_DROP_DATA_URL);
        retrofitInit(5, ApiClient.SHIPING_METHOD_DROP_DATA_URL);


        return view;
    }

    private void initializeActions() {
        expirationdatetxt.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
    }

    private void initializeViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);

        packquatyspn = (Spinner) view.findViewById(R.id.packquatyspn);
        prductOpt = (Spinner) view.findViewById(R.id.prductOpt);
        packConspn = (Spinner) view.findViewById(R.id.packConspn);
        groundspn = (Spinner) view.findViewById(R.id.groundspn);
        shippingspn = (Spinner) view.findViewById(R.id.shippingspn);

        expirationdatetxt = (TextView) view.findViewById(R.id.expirationdatetxt);

        ndcnoEt = (EditText) view.findViewById(R.id.ndcnoEt);
        lotnoEt = (EditText) view.findViewById(R.id.lotnoEt);
        packageavailEt = (EditText) view.findViewById(R.id.packageavailEt);
        wacpriceEt = (EditText) view.findViewById(R.id.wacpriceEt);
        wacdiscountEt = (EditText) view.findViewById(R.id.wacdiscountEt);
        packpriceEt = (EditText) view.findViewById(R.id.packpriceEt);

        resetBtn = (Button) view.findViewById(R.id.resetBtn);
        submitBtn = (Button) view.findViewById(R.id.submitBtn);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void adapterDataAssigingToSpinner(ArrayList<String> spinnerTitles, int spinnerSelction) {
        if (spinnerSelction == 1) {
            packageQtyTitles = spinnerTitles;
            ArrayAdapter<String> zonedataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, packageQtyTitles);
            zonedataAdapter.setDropDownViewResource(R.layout.list_item);
            packquatyspn.setAdapter(zonedataAdapter);
        } else if (spinnerSelction == 2) {
            prductOptTitles = spinnerTitles;
            ArrayAdapter<String> routeAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, prductOptTitles);
            routeAdapter.setDropDownViewResource(R.layout.list_item);
            prductOpt.setAdapter(routeAdapter);
        } else if (spinnerSelction == 3) {
            packConTitles = spinnerTitles;
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, packConTitles);
            packConspn.setAdapter(dataAdapter);
        } else if (spinnerSelction == 4) {
            groundTitles = spinnerTitles;
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, groundTitles);
            groundspn.setAdapter(dataAdapter);
        } else if (spinnerSelction == 5) {
            shippingTitles = spinnerTitles;
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, shippingTitles);
            shippingspn.setAdapter(dataAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.expirationdatetxt) {
            DialogFragment newFragment = new DatePickerFragmentDailoge(fragmentManager, expirationdatetxt);
            newFragment.show(getFragmentManager(), "datePicker");
        } else if (v.getId() == R.id.resetBtn) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        } else if (v.getId() == R.id.submitBtn) {
            Toast.makeText(getContext(), "submitted", Toast.LENGTH_SHORT).show();

        }
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
                if (spinnerPositionNolocal == 1) {
                    packageQtyAdapter(response.body(), spinnerPositionNolocal);
                } else if (spinnerPositionNolocal == 2) {
                    prductOptAdapter(response.body(), spinnerPositionNolocal);
                } else if (spinnerPositionNolocal == 3) {
                    packConAdapter(response.body(), spinnerPositionNolocal);
                } else if (spinnerPositionNolocal == 4) {
                    groundAdapter(response.body(), spinnerPositionNolocal);
                } else if (spinnerPositionNolocal == 5) {
                    shippingAdapter(response.body(), spinnerPositionNolocal);
                }
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
    //Product Option Spinner
    private void prductOptAdapter(ArrayList<CommonDropDownData> dropDownArrayData, int spinnernumber) {
        try {
            _prductOptData.clear();
            prductOptTitles.clear();
            _prductOptData = new ArrayList<CommonDropDownData>();
            for (int i = 0; i < dropDownArrayData.size(); i++) {
                String id = dropDownArrayData.get(i).get_id();
                String title = dropDownArrayData.get(i).getName();
                _prductOptData.add(new CommonDropDownData(id, title));
            }
            prductOptTitles.add("Select");
            if (_prductOptData.size() > 0) {
                for (int i = 0; i < _prductOptData.size(); i++) {
                    prductOptTitles.add(_prductOptData.get(i).getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapterDataAssigingToSpinner(prductOptTitles, spinnernumber);
    }

    //Product Quantity Spinner
    private void packageQtyAdapter(ArrayList<CommonDropDownData> dropDownArrayData, int spinnernumber) {
        try {
            _packageQtyData.clear();
            packageQtyTitles.clear();
            _packageQtyData = new ArrayList<CommonDropDownData>();
            for (int i = 0; i < dropDownArrayData.size(); i++) {
                String id = dropDownArrayData.get(i).get_id();
                String title = dropDownArrayData.get(i).getName();
                _packageQtyData.add(new CommonDropDownData(id, title));
            }
            packageQtyTitles.add("Select");
            if (_packageQtyData.size() > 0) {
                for (int i = 0; i < _packageQtyData.size(); i++) {
                    packageQtyTitles.add(_packageQtyData.get(i).getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapterDataAssigingToSpinner(packageQtyTitles, spinnernumber);
    }

    private void packConAdapter(ArrayList<CommonDropDownData> dropDownArrayData, int spinnernumber) {
        try {
            _packConData.clear();
            packConTitles.clear();
            _packConData = new ArrayList<CommonDropDownData>();
            for (int i = 0; i < dropDownArrayData.size(); i++) {
                String id = dropDownArrayData.get(i).get_id();
                String title = dropDownArrayData.get(i).getName();
                _packConData.add(new CommonDropDownData(id, title));
            }
            packConTitles.add("Select");
            if (_packConData.size() > 0) {
                for (int i = 0; i < _packConData.size(); i++) {
                    packConTitles.add(_packConData.get(i).getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapterDataAssigingToSpinner(packConTitles, spinnernumber);
    }

    private void groundAdapter(ArrayList<CommonDropDownData> dropDownArrayData, int spinnernumber) {
        try {
            _groundData.clear();
            groundTitles.clear();
            _groundData = new ArrayList<CommonDropDownData>();
            for (int i = 0; i < dropDownArrayData.size(); i++) {
                String id = dropDownArrayData.get(i).get_id();
                String title = dropDownArrayData.get(i).getName();
                _groundData.add(new CommonDropDownData(id, title));
            }
            groundTitles.add("Select");
            if (_groundData.size() > 0) {
                for (int i = 0; i < _groundData.size(); i++) {
                    groundTitles.add(_groundData.get(i).getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapterDataAssigingToSpinner(groundTitles, spinnernumber);
    }

    private void shippingAdapter(ArrayList<CommonDropDownData> dropDownArrayData, int spinnernumber) {
        try {
            _shippingData.clear();
            shippingTitles.clear();
            _shippingData = new ArrayList<CommonDropDownData>();
            for (int i = 0; i < dropDownArrayData.size(); i++) {
                String id = dropDownArrayData.get(i).get_id();
                String title = dropDownArrayData.get(i).getName();
                _shippingData.add(new CommonDropDownData(id, title));
            }
            shippingTitles.add("Select");
            if (_shippingData.size() > 0) {
                for (int i = 0; i < _shippingData.size(); i++) {
                    shippingTitles.add(_shippingData.get(i).getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapterDataAssigingToSpinner(shippingTitles, spinnernumber);
    }
}
