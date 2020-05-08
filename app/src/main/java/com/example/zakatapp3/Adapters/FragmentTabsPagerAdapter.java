package com.example.zakatapp3.Adapters;

import com.example.zakatapp3.Fragments.AssetsFragment;
import com.example.zakatapp3.Fragments.CalculateFragment;
import com.example.zakatapp3.Fragments.LiabilityFragment;
import com.example.zakatapp3.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentTabsPagerAdapter extends FragmentPagerAdapter {

    private static int NUMBER_OF_FRAGMENTS = 3;
    private static String FRAGMENT_TAB1 = "Assets";
    private static String FRAGMENT_TAB2 = "Liabilities";
    private static String FRAGMENT_TAB3 = "Calculate";


    public FragmentTabsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position == 0) {
            fragment = new AssetsFragment();
        }
        else if (position == 1) {
            fragment = new LiabilityFragment();
        }
        else {
            fragment = new CalculateFragment();
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        if (position == 0) {
            title = FRAGMENT_TAB1;
        }
        else if (position == 1) {
            title = FRAGMENT_TAB2;
        }
        else {
            title = FRAGMENT_TAB3;
        }
        return (title);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }
}
