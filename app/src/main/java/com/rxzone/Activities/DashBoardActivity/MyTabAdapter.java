package com.rxzone.Activities.DashBoardActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rxzone.Activities.Fragments.AllPostFragment.AllPostListFragment;

/**
 * Created by PROXIM on 2/5/2018.
 */

public class MyTabAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public MyTabAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                AllPostListFragment tab1 = new AllPostListFragment();
                return tab1;
            case 1:
                AllPostListFragment tab2 = new AllPostListFragment();
                return tab2;
            case 2:
                AllPostListFragment tab3 = new AllPostListFragment();
                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

}
