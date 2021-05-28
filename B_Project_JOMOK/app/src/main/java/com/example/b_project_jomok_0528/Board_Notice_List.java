package com.example.b_project_jomok_0528;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Board_Notice_List extends Fragment {
    //MainActivity activity ;
    //TabLayout tabs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //activity = (MainActivity) getActivity();

       // tabs = activity.tabs ;
       // tabs.setVisibility(View.VISIBLE);
        return inflater.inflate(R.layout.fragment_board_notice_list, container, false);

    }
}