//package com.rxzone.Activities.AddPostActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.leo.simplearcloader.SimpleArcLoader;
//import com.rxzone.Activities.LoginActivity.LoginActivity;
//import com.rxzone.Activities.LoginActivity.LoginData;
//import com.rxzone.retrofitcall.ApiClient;
//import com.rxzone.retrofitcall.ApiInterface;
//import com.rxzone.rxzone.R;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by PROXIM on 2/5/2018.
// */
//
//public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {
//    EditText username_et;
//    Button loginBtn, forgotbtn;
//    String userName = "";
//    String passWord = "";
//
//    Context mContext;
//    public SimpleArcLoader mDialog;
//    ApiInterface apiInterface;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_post_activity);
//        initializeViews(AddPostActivity.this);
//        initializeFunction();
//    }
//
//    private void initializeFunction() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//     /*   loginBtn.setOnClickListener(this);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);*/
//    }
//
//    private void initializeViews(Context context) {
//       /* username_et = (EditText) findViewById(R.id.password_et);
//        loginBtn = (Button) findViewById(R.id.loginBtn);
//
//        mContext = context;
//        mDialog = (SimpleArcLoader) findViewById(R.id.arc_loader);*/
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.loginBtn:
//                boolean validated = validateFields();
//                if (validated) {
//                    retrofitInit();
//                }
//                break;
//
//        }
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //onOptionsItemSelected
////        return super.onOptionsItemSelected(item);
//        switch (item.getItemId())
//        {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//        Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//
//    private boolean validateFields() {
//        boolean Valid = true;
//        userName = username_et.getText().toString();
//
//        if (userName.length() == 0) {
//            Toast.makeText(mContext, "Please Enter UserId", Toast.LENGTH_SHORT).show();
//            Valid = false;
//            return Valid;
//        }
//        return Valid;
//
//    }
//
//
//    private void retrofitInit() {
//        userName = "user@rxzone.com";
//        LoginData loginData = new LoginData(userName, passWord);
//        mDialog.setVisibility(View.VISIBLE);
//        mDialog.start();
//        apiInterface = ApiClient.getClient(ApiClient.FORGOT_URL).create(ApiInterface.class);
//        Call<LoginData> call = apiInterface.getLogInReq(loginData);
//        call.enqueue(new Callback<LoginData>() {
//            @Override
//            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
//                try {
//                    mDialog.setVisibility(View.GONE);
//                    mDialog.stop();
//                    String message = response.body().getMessage();
//                    if (message != null && message.equals("success")) {
//                        Toast.makeText(getApplicationContext(), "Password sent your mail id..", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(AddPostActivity.this, LoginActivity.class);
//                        startActivity(i);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Invalid Mailid", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "Invalid Mailid", Toast.LENGTH_SHORT).show();
////                    SharedPrefsUtil.setStringPreference(getContext(), "INDUSTRY_TYPE");
//                    Log.e("errorhits", "null");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginData> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Invalid Mailid", Toast.LENGTH_SHORT).show();
//                Log.e("Failed", "vv" + call + t);
//            }
//        });
//    }
//}
