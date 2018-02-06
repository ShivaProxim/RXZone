package com.rxzone.Activities.LoginActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.rxzone.Activities.DashBoardActivity.MainActivity;
import com.rxzone.Activities.ForgotPasswordActivity.ForgotPasswordActivity;
import com.rxzone.retrofitcall.ApiClient;
import com.rxzone.retrofitcall.ApiInterface;
import com.rxzone.rxzone.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PROXIM on 2/5/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username_et;
    EditText password_et;
    Button loginBtn, forgotbtn;
    RadioButton rememberRbtn;
    String userName = "";
    String passWord = "";
    RadioGroup radioGroup;
    boolean checked = true;

    Context mContext;
    public SimpleArcLoader mDialog;
    ApiInterface apiInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initializeViews(LoginActivity.this);
        initializeFunction();
    }

    private void initializeFunction() {
        loginBtn.setOnClickListener(this);
        forgotbtn.setOnClickListener(this);
    }

    private void initializeViews(Context context) {
        username_et = (EditText) findViewById(R.id.username_et);
        password_et = (EditText) findViewById(R.id.password_et);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        rememberRbtn = (RadioButton) findViewById(R.id.rememberRbtn);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        forgotbtn = (Button) findViewById(R.id.forgotbtn);

        mContext = context;
        mDialog = (SimpleArcLoader) findViewById(R.id.arc_loader);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                boolean validated = validateFields();
                if (validated) {
                    retrofitInit();
                }
                break;
            case R.id.forgotbtn:
                Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(i);
                break;
        }

    }

    private boolean validateFields() {
        boolean Valid = true;
        userName = username_et.getText().toString();
        passWord = password_et.getText().toString();

        if (userName.length() == 0) {
            Toast.makeText(mContext, "Please Enter Username", Toast.LENGTH_SHORT).show();
            Valid = false;
            return Valid;
        } else if (passWord.length() == 0) {
            Toast.makeText(mContext, "Please Enter Password", Toast.LENGTH_SHORT).show();
            Valid = false;
            return Valid;
        }
        return Valid;

    }



    private void retrofitInit() {
        userName = "user@rxzone.com";
        passWord = "test@123#";
        LoginData loginData = new LoginData(userName, passWord);
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClient(ApiClient.LOGIN_URL).create(ApiInterface.class);
        Call<LoginData> call = apiInterface.getLogInReq(loginData);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                try {
                    mDialog.setVisibility(View.GONE);
                    mDialog.stop();
                    String message = response.body().getMessage();
                    if (message != null && message.equals("success")) {
                        Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Login Details", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid Login Details", Toast.LENGTH_SHORT).show();
//                    SharedPrefsUtil.setStringPreference(getContext(), "INDUSTRY_TYPE");
                    Log.e("errorhits", "null");
                }

            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                Log.e("Failed", "vv" + call + t);
            }
        });
    }
}
