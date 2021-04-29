package com.example.my07_alllayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnImg, btnImg2;
    ImageView imageView2, imageView3, imageView4, imageView5;
    int selImg1 = 2;
    int selImg2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        btnImg = findViewById(R.id.btnImg);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selImg1 == 1) {
                    imageView2.setVisibility(View.VISIBLE);
                    imageView3.setVisibility(View.GONE);
                    selImg1 = 2;
                } else if (selImg1 == 2) {
                    imageView2.setVisibility(View.GONE);
                    imageView3.setVisibility(View.VISIBLE);
                    selImg1 = 1;
                }
            }
        });

        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

        btnImg2 = findViewById(R.id.btnImg2);
        btnImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selImg2 == 1) {
                    imageView4.setVisibility(View.VISIBLE);
                    imageView5.setVisibility(View.GONE);
                    selImg2 = 2;
                } else if (selImg2 == 2) {
                    imageView4.setVisibility(View.GONE);
                    imageView5.setVisibility(View.VISIBLE);
                    selImg2 = 1;
                }
            }
        });
    }
}