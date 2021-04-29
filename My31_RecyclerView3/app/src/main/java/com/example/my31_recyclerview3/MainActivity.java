package com.example.my31_recyclerview3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SingerAdapter adapter;
    ArrayList<SingerDto> dtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 반드시 생성해서 어댑터에 넘겨야 함
        dtos = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰에서 반드시 초기화 시켜야함
        LinearLayoutManager layoutManager = new LinearLayoutManager(
               this, RecyclerView.VERTICAL, false
        );
        recyclerView.setLayoutManager(layoutManager);

        // 어댑터 객체를 생성한다
        adapter = new SingerAdapter(dtos, MainActivity.this);

        // 어댑터에 있는 ArrayList에 dto를 6개 추가한다
        adapter.addDto(new SingerDto("신도림", "오세형",
                "9.95", R.drawable.webtoon1, "https://comic.naver.com/webtoon/list.nhn?titleId=683496&weekday=tue"));
        adapter.addDto(new SingerDto("더복서", "정지훈",
                "9.93", R.drawable.webtoon2, "https://comic.naver.com/webtoon/list.nhn?titleId=736989&weekday=thu"));
        adapter.addDto(new SingerDto("광장", "오세형/김균태",
                "9.97", R.drawable.webtoon3, "https://comic.naver.com/webtoon/list.nhn?titleId=753509&weekday=sat"));
        adapter.addDto(new SingerDto("싸움독학", "박태준/김정현",
                "9.80", R.drawable.webtoon4, "https://comic.naver.com/webtoon/list.nhn?titleId=736277&weekday=sun"));
        adapter.addDto(new SingerDto("외모지상주의", "박태준",
                "9.46", R.drawable.webtoon5,  "https://comic.naver.com/webtoon/list.nhn?titleId=641253&weekday=fri"));
        adapter.addDto(new SingerDto("캐슬", "정연",
                "9.97", R.drawable.webtoon6, "https://comic.naver.com/webtoon/list.nhn?titleId=736744&weekday=wed"));
        // 만든 어댑터를 리스트뷰에 붙인다
        recyclerView.setAdapter(adapter);

        //어댑터에 있는 클릭리스너를 이용해 클릭한 위치의 dto를가져온다
        adapter.setOnItemClickListerner(new OnSingerItemClickListener() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holderm, View view, int position) {
                // 클릭하면 어댑터에게 받은 클릭위치를 이용해 dto 가져온다
                SingerDto dto = adapter.getItem(position);
                /*Toast.makeText(MainActivity.this, "제목 : " + dto.getName()
                        + "\n작가 : " + dto.getWriter() + "\n평점 : " + dto.getScore(), Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dto.getUrl()));
                startActivity(intent);
            }
        });

    }
}
