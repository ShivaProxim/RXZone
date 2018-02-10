package com.rxzone.HomeActivity.OrderSummaryActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.rxzone.HomeActivity.ForgotPasswordActivity.ForgotPasswordActivity;
import com.rxzone.HomeActivity.LoginActivity.LoginActivity;
import com.rxzone.rxzone.R;

/**
 * Created by PROXIM on 2/10/2018.
 */

public class OrderSummeryActivity extends AppCompatActivity implements View.OnClickListener {
    Button proceedBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summary_fragment);
        proceedBtn = (Button) findViewById(R.id.proceedBtn);
        proceedBtn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.proceedBtn) {
            Toast.makeText(getApplicationContext(), "Will get back to you soon", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
//        Intent intent = new Intent(OrderSummeryActivity.this, LoginActivity.class);
//        startActivity(intent);
        finish();
    }
}
