package com.example.my27_listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView listView;

    SingerAdapter adapter;
    ArrayList<SingerDto> dtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dtos = new ArrayList<>();

        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        //어댑터 객체를 생성한다
        adapter = new SingerAdapter(MainActivity.this, dtos);


        //어댑터에 있는 ArrayList에 dto를 5개 추가한다
        adapter.addDto(new SingerDto("레드벨벳", "010-1234-5678", 26, R.drawable.singer1));
        adapter.addDto(new SingerDto("블랙핑크", "010-2345-6789", 24, R.drawable.singer2));
        adapter.addDto(new SingerDto("소녀시대", "010-3456-7890", 30, R.drawable.singer3));
        adapter.addDto(new SingerDto("걸스데이", "010-1111-2222", 29, R.drawable.singer4));
        adapter.addDto(new SingerDto("트와이스", "010-4555-6777", 25, R.drawable.singer5));

        //만든 어댑터를 리스트뷰에 붙인다
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerDto dto = (SingerDto) adapter.getItem(position);

                Toast.makeText(MainActivity.this, "선택 : " + position
                        + "\n이름 : " + dto.getName() + "\n전화번호 : " + dto.getMobile()
                        + "\n나이 : " + dto.getAge() + "\n이미지 : " + dto.getResId(), Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //어댑터에 있는 ArrayList에 dto를 1개 추가
                adapter.addDto(new SingerDto("오마이걸", "010-6666-6666", 23, R.drawable.singer3));
                //화면 갱신
                adapter.notifyDataSetChanged();
            }
        });
    }
}