package com.rxzone.HomeActivity.ProductViewFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.simplearcloader.SimpleArcLoader;
import com.rxzone.HomeActivity.AddPostFragment.AddProductData;
import com.rxzone.HomeActivity.AllPostFragment.AllPostAdapter;
import com.rxzone.HomeActivity.AllPostFragment.AllPostData;
import com.rxzone.HomeActivity.HomeActivity;
import com.rxzone.Util.Common;
import com.rxzone.Util.DatePickerFragmentDailoge;
import com.rxzone.Util.SharedPrefsUtil;
import com.rxzone.model.CommonDropDownData;
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

public class ProductViewFragment extends Fragment implements View.OnClickListener {
    View view;
    RecyclerView common_recyclerview_recycler;
    FragmentManager fragmentManager;
    TextView nodataavailtxt;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    ProductSubInfoAdapter productSubInfoAdapter;
    Button resetBtn, proceedBtn;
    LinearLayout checklayout;
    ImageView decrementIv, incrementIv;

    TextView postTitle, productdes, pricetxt, counttxt;


    String gsdbRefID = "", ndcNum = "", lotNum = "", expDate = "", packageQuantity = "", pkgAvailable = "",
            priceOption = "", packPrice = "", wacDsicount = "", imagePath = "", strength = "", packagee = "",
            formcp = "", lastRefDate = "", packageConditionVal = "", otherPackageConditionText = "",
            groundShippingVal = "", shippingMethodVal = "", productID = "",
            gsddProductID = "",
            packageName = "", mfgName = "", wacPrice = "", partialQunatity = "";
    Boolean tornPharmacyLabel;
    Boolean otherPackageCondition;
    int positionvalue;
    List<AllPostData> allPostData;
    public String subInfoData[] = new String[7];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.productview_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((HomeActivity) getActivity()).changeToolBarText("Product View");
        initializeViews();
        initializeActions();
        return view;
    }

    private void initializeActions() {
        checklayout.setOnClickListener(this);
        proceedBtn.setOnClickListener(this);
        Bundle bundle = getArguments();
        positionvalue = bundle.getInt("POSITION_VALUE");
        allPostData = (List<AllPostData>) bundle.getSerializable("PRODUCT_DETAILS");


        if (allPostData.get(positionvalue).getPackagee() != null && !allPostData.get(positionvalue).getPackagee().isEmpty()) {
            Log.e("nameProfile", allPostData.get(positionvalue).getPackagee());
            postTitle.setText(Html.fromHtml(allPostData.get(positionvalue).getPackagee()) + "");
        }

        if (allPostData.get(positionvalue).getPackageName() != null && !allPostData.get(positionvalue).getPackageName().isEmpty()) {
            productdes.setText(Html.fromHtml("Note : " + allPostData.get(positionvalue).getPackageName()) + "");
        }

        if (allPostData.get(positionvalue).getPackPrice() != null && !allPostData.get(positionvalue).getPackPrice().isEmpty()) {
            pricetxt.setText(Html.fromHtml("$ " + allPostData.get(positionvalue).getPackPrice()) + "");
        }

        subInfoData[0] = allPostData.get(positionvalue).getNdcNum();
        subInfoData[1] = allPostData.get(positionvalue).getNdcNum();
        subInfoData[2] = allPostData.get(positionvalue).getPackageName();
        subInfoData[3] = allPostData.get(positionvalue).getExpDate();
        subInfoData[4] = allPostData.get(positionvalue).getNdcNum();
        subInfoData[5] = allPostData.get(positionvalue).getWacPrice();
        subInfoData[6] = allPostData.get(positionvalue).getPackageQuantity();
        subInfoAdapter(subInfoData);


    }

    private void initializeViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);

        postTitle = (TextView) view.findViewById(R.id.postTitle);
        productdes = (TextView) view.findViewById(R.id.productdes);
        pricetxt = (TextView) view.findViewById(R.id.pricetxt);
        checklayout = (LinearLayout) view.findViewById(R.id.checklayout);
        decrementIv = (ImageView) view.findViewById(R.id.decrementIv);
        incrementIv = (ImageView) view.findViewById(R.id.incrementIv);
        counttxt = (TextView) view.findViewById(R.id.counttxt);
        proceedBtn = (Button) view.findViewById(R.id.proceedBtn);

        common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.common_recyclerview_recycler);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checklayout) {
        } else if (v.getId() == R.id.resetBtn) {
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.detach(this).attach(this).commit();
            transactFragment(this, true);
        } else if (v.getId() == R.id.proceedBtn) {
//            boolean ValidatedSuccess = ValidatingAllFields();

           /* if (ValidatedSuccess) {
                submitDataServerwithretrofit();
                Toast.makeText(getContext(), "submitted", Toast.LENGTH_SHORT).show();
            }*/


        }
    }

    public void transactFragment(Fragment fragment, boolean reload) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (reload) {
            getFragmentManager().popBackStack();
        }
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void subInfoAdapter(String[] subInfoData) {
        ratingNowShowingLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        common_recyclerview_recycler.setLayoutManager(ratingNowShowingLayoutManager);
        productSubInfoAdapter = new ProductSubInfoAdapter(getContext(), fragmentManager, subInfoData);
        common_recyclerview_recycler.setAdapter(productSubInfoAdapter);

    }


    private void submitDataServerwithretrofit() {
        AddProductData addProductData = new AddProductData(gsdbRefID, ndcNum,
                lotNum, expDate, packageQuantity, pkgAvailable, priceOption,
                packPrice, wacDsicount, imagePath, strength, packagee, formcp, lastRefDate,
                packageConditionVal, tornPharmacyLabel, otherPackageCondition, otherPackageConditionText,
                groundShippingVal, shippingMethodVal, productID, gsddProductID, packageName,
                mfgName, wacPrice, partialQunatity);


        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClient(ApiClient.SAVE_PRODUCT_DETAILS_URL).create(ApiInterface.class);
        Call<AddProductData> call = apiInterface.submitPostReq(SharedPrefsUtil.getStringPreference(getContext(), "TOKEN_ID"), addProductData);

        call.enqueue(new Callback<AddProductData>() {
            @Override
            public void onResponse(Call<AddProductData> call,
                                   Response<AddProductData> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                String message = response.body().getMessage();
                if (message != null && !message.isEmpty()) {
                    if (message.equals("success")) {
                        Common.toastMessage(getContext(), "Product Data Submitted Successfully");
                    } else {
                        Common.toastMessage(getContext(), "Failed to submit");
                    }
                } else {
                    Common.toastMessage(getContext(), "Null Data From server");
                }


            }

            @Override
            public void onFailure(Call<AddProductData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }


}
