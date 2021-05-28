package com.example.b_project_jomok_0528;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b_project_jomok_0528.ATask.FreeSelect;
import com.example.b_project_jomok_0528.Adapter.FreeBoardAdapter;
import com.example.b_project_jomok_0528.Dto.FreeBoardDTO;

import java.util.ArrayList;

import static com.example.b_project_jomok_0528.Common.CommonMethod.isNetworkConnected;

public class Board_Free_List extends Fragment {
    FreeSelect freeSelect;

    ArrayList<FreeBoardDTO> arrayList;

    RecyclerView recyclerView;
    FreeBoardAdapter adapter;

    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);


        // 리사이클러 뷰 시작
        arrayList = new ArrayList<>();
        adapter = new FreeBoardAdapter(getActivity(), arrayList);
        recyclerView = view.findViewById(R.id.recyclerView_board);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        if(isNetworkConnected(getActivity()) == true){
            freeSelect = new FreeSelect(arrayList, adapter, progressDialog);
            freeSelect.execute();
        }else {
            Toast.makeText(getActivity(), "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void processIntent(Intent intent){
        if(intent != null){
            freeSelect = new FreeSelect(arrayList, adapter, progressDialog);
            freeSelect.execute();
        }
    }

}
