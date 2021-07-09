package com.example.coretech_mobile.auth;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AuthPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;

    public AuthPagerAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SignInTabFragment signInTabFragment = new SignInTabFragment();
                return signInTabFragment;
            case 1:
                SignUpTabFragment signUpTabFragment = new SignUpTabFragment();
                return signUpTabFragment;
            default:
                return null;
        }
    }
}

