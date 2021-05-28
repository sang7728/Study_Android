package com.example.b_project_jomok_0528;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;


public class myPageFragment extends Fragment {

    private Fragment myPageFragement_1;
    private Fragment myPageFragement_2;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_mypage, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        imageView.setClipToOutline(true);


        myPageFragement_1 = new myPageFragement_1();
        myPageFragement_2 = new myPageFragement_2();

        //프래그먼트에서 다은 프레그먼트 할 떄는 getChildFragmentManager 이거 씀
        getChildFragmentManager().beginTransaction().replace(R.id.frame,myPageFragement_1).commit();
        
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);


        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position == 0){

                    selected = myPageFragement_1;

                }else if (position == 1){

                    selected = myPageFragement_2;

                }
                getChildFragmentManager().beginTransaction().replace(R.id.frame, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



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