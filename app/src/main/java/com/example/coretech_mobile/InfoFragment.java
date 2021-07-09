package com.example.coretech_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.info_main_fragment, container, false);
        MainActivity parent = (MainActivity) getActivity();
        root.findViewById(R.id.info_back).setOnClickListener(v -> parent.setPagerItem(1));
        return root;
    }
}
