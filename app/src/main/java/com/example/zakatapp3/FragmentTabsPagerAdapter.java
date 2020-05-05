package com.example.zakatapp3;

import com.example.zakatapp3.Fragments.AssetsFragment;
import com.example.zakatapp3.Fragments.CalculateFragment;
import com.example.zakatapp3.Fragments.LiabilityFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentTabsPagerAdapter extends FragmentPagerAdapter {


    public FragmentTabsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
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
            title = "Assets";
        }
        else if (position == 1) {
            title = "Liabilities";
        }
        else {
            title = "Calculate";
        }
        return (title);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
