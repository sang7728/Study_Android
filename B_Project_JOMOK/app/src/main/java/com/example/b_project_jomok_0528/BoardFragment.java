package com.example.b_project_jomok_0528;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.b_project_jomok_0528.Dto.FreeBoardDTO;


public class BoardFragment extends Fragment {
    public static FreeBoardDTO BoardItem = null;

  //  MainActivity activity ;
   // TabLayout tabs;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);

      //  activity = (MainActivity) getActivity();
/*
        tabs = activity.tabs ;
        tabs.setVisibility(View.VISIBLE);*/



        //프래그먼트에서 다은 프레그먼트 할 떄는 getChildFragmentManager 이거 씀
       // getChildFragmentManager().beginTransaction().replace(R.id.frame_board, Board_Free_List).commit();

        /* tabs = (TabLayout) view.findViewById(R.id.tabs_board);
    *//*    tabs.setVisibility(View.GONE);*//*

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position == 0){

                    selected = Board_Free_List;

                }else if (position == 1){

                    selected = Board_Notice_List;

                }else if (position == 2){

                    selected = Board_FAQ_List;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}