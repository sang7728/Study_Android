package com.example.b_project_jomok_0528;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNV;

    TabLayout tabs;
    private Board_Free_List Board_Free_List;
    private Board_Notice_List Board_Notice_List;
    private Board_FAQ_List Board_FAQ_List;
    private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = (TabLayout) findViewById(R.id.tabs_board);
        mBottomNV = findViewById(R.id.nav_view);
        mainFragment = new MainFragment();
        Board_Free_List = new Board_Free_List();
        Board_Notice_List = new Board_Notice_List();
        Board_FAQ_List = new Board_FAQ_List();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, mainFragment).commit();

        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                BottomNavigate(menuItem.getItemId());

                return true;
            }
        });

        //tabEvent
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
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout , Board_Free_List).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //mBottomNV.setSelectedItemId(R.id.tab1);
    }

    private void BottomNavigate(int id) {  //BottomNavigation 페이지 변경
      //  String tag = String.valueOf(id);
        tabs.setVisibility(View.GONE);
        Fragment fragment;
       // FragmentManager fragmentManager = getSupportFragmentManager();
       // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

       /// Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
      //  if (currentFragment != null) {
       //     fragmentTransaction.hide(currentFragment);
      //  }

      //  Fragment fragment = fragmentManager.findFragmentByTag(tag);
      //  if (fragment == null) {
            if (id == R.id.tab1) {
                /*MainFragment mainFragment = new MainFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, mainFragment).commit();*/
                fragment = new MainFragment();
                /*fragment = new MainFragment();*/
            } else if (id == R.id.tab2){
                /*tabs.setVisibility(View.VISIBLE);
                BoardFragment boardFragment = new BoardFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, boardFragment).commit();*/
                fragment = new BoardFragment();
                 /*fragment = new BoardFragment();*/
            }else if(id == R.id.tab3){
                /*PortFragment portFragment = new PortFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, portFragment).commit();*/
                fragment = new PortFragment();
                /*fragment = new PortFragment();*/
            } else {
                /*myPageFragment myPageFragment = new myPageFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, myPageFragment).commit();*/
                fragment = new myPageFragment();
                /*fragment = new myPageFragment();*/
            }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout, fragment).commit();
            //fragmentTransaction.add(R.id.content_layout, fragment, tag);
     //   } else {
        //    fragmentTransaction.show(fragment);
      //  }

        //fragmentTransaction.setPrimaryNavigationFragment(fragment);
        //fragmentTransaction.setReorderingAllowed(true);
        //fragmentTransaction.commitNow();
    }

}