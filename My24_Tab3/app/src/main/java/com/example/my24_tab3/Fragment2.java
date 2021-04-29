package com.example.my24_tab3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class Fragment2 extends Fragment {
    MainActivity activity;
    String sendData, receiveData;
    Person person2;

    Button btn2;
    TextView textView2;

    //화면이 붙을때 초기화 시키기
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        //프래그먼트가 속한 액티비티 가져옴
        activity = (MainActivity) getActivity();
        //프래그먼트2에서 보낼 문자열
        sendData = "프래그먼트2에서 보낸 데이터입니다";
        person2 = new Person("LEE", 27);
        //프래그먼트1에서 받을 문자열 초기화
        receiveData = "";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        textView2 = viewGroup.findViewById(R.id.textView2);
        btn2 = viewGroup.findViewById(R.id.btn2);

        //프래그먼트1에서 보낸 데이터 받기
        if (activity.mBundle != null) {
            Bundle bundle = activity.mBundle;
            receiveData = bundle.getString("sendData");
            Person person1 = (Person) bundle.getSerializable("person1");
            String name = person1.getName();
            int age = person1.getAge();

            textView2.setText(receiveData + "\n");
            textView2.append("name : " + name + "\nage : " + age);

            activity.mBundle = null;
        }

        //프래그먼트3으로 데이터 보내기
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("sendData", sendData);
                bundle.putSerializable("person2", person2);
                bundle.putInt("index", 0);

                //메인 액티비티에 번들 만들어서 보내기
                activity.fragBtnClicked(bundle);

                //메인 액티비티에 프래그먼트2로 화면 전환 요청
                TabLayout.Tab tab = activity.tabs.getTabAt(2);
                tab.select();
            }
        });

        return viewGroup;
    }
}
