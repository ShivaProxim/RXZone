package com.rxzone.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rxzone.HomeActivity.AddPostFragment.AddPostFragment;
import com.rxzone.HomeActivity.AllPostFragment.AllPostLandingFragment;
import com.rxzone.HomeActivity.CheckoutFragment.CheckoutFragment;
import com.rxzone.HomeActivity.LoginActivity.LoginActivity;
import com.rxzone.Util.SharedPrefsUtil;
import com.rxzone.rxzone.R;


public class HomeActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    private String[] activityTitles;

    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    EditText toolbarsearch;
    TextView  toolbarcusttext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarsearch = (EditText) toolbar.findViewById(R.id.toolbarsearch);
        toolbarcusttext = (TextView) toolbar.findViewById(R.id.toolbarcusttext);

        mHandler = new Handler();
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);



//        navHeader = navigationView.getHeaderView(0);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        if(SharedPrefsUtil.getStringPreference(getApplicationContext(), "TOKEN_ID") != null){
            Log.e("TokenId", SharedPrefsUtil.getStringPreference(getApplicationContext(), "TOKEN_ID") + "");
        }

        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }


        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        drawer.closeDrawers();
        invalidateOptionsMenu();
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                changeToolBarText("Search");
                AllPostLandingFragment homeFragment = new AllPostLandingFragment();
                return homeFragment;
            case 1:
                // photos
                AddPostFragment addPostFragment = new AddPostFragment();
                return addPostFragment;
            case 2:
                // movies fragment
                CheckoutFragment checkoutFragment = new CheckoutFragment();
                return checkoutFragment;
            case 3:
                // notifications fragment
                CheckoutFragment notificationsFragment = new CheckoutFragment();
                return notificationsFragment;
            default:
                return new AllPostLandingFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.addpost:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.checkoutitem:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.wishListitem:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.logoutitem:
                        SharedPrefsUtil.setStringPreference(getApplicationContext(), "TOKEN_ID", "");
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        return true;

                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.wishList) {
            Toast.makeText(getApplicationContext(), "Wishlist Come soon", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.checkout) {
            Toast.makeText(getApplicationContext(), "Checkout Come soon", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeToolBarText(String headerdata){
        if (headerdata.equals("Add Post")){
            toolbarTextChange(headerdata);
        }else if (headerdata.equals("Checkout")){
            toolbarTextChange(headerdata);
        }else if (headerdata.equals("Product View")){
            toolbarTextChange(headerdata);
        }
        else {
            toolbarsearch.setVisibility(View.VISIBLE);
            toolbarcusttext.setVisibility(View.GONE);
        }

    }

    private void toolbarTextChange(String headerdata) {
        toolbarsearch.setVisibility(View.GONE);
        toolbarcusttext.setVisibility(View.VISIBLE);
        toolbarcusttext.setText(headerdata);
    }

}
