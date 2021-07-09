package com.example.coretech_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.coretech_mobile.auth.AuthActivity;

public class MainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.main_fragment, container, false);
        MainActivity parent = (MainActivity) getActivity();
        root.findViewById(R.id.main_info_button).setOnClickListener(v -> parent.setPagerItem(0));
        root.findViewById(R.id.main_auth_button).setOnClickListener(v -> startActivity(new Intent(root.getContext(), AuthActivity.class)));
        return root;
    }
}