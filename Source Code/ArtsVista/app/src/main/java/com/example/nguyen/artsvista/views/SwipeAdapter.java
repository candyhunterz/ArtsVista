package com.example.nguyen.artsvista.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by rythxms on 19/10/15.
 */
public class SwipeAdapter extends FragmentStatePagerAdapter {
    //SWIPE ADAPTER USED FOR THE INTRO PAGES
    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new pageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count", i + 1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}


