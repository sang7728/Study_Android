package com.example.my19_fragment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ListFragment listFragment;
    ViewerFragment viewerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) getSupportFragmentManager().findFragmentById(R.id.viewerFragment);

    }

    //프래그먼트에서 접근할 수 있도록 만든 매소드
    public void onImageSelected(int resId) {
        viewerFragment.ImgChange(resId);
    }

}