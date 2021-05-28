package com.example.b_project_jomok_0528.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.b_project_jomok_0528.Dto.FreeBoardDTO;
import com.example.b_project_jomok_0528.R;
import static com.example.b_project_jomok_0528.BoardFragment.BoardItem;


import java.util.ArrayList;


public class FreeBoardAdapter extends RecyclerView.Adapter<FreeBoardAdapter.ItemViewHolder>{
    private static final String TAG = "FreeBoardAdapter";

    Context mContext;
    ArrayList<FreeBoardDTO> arrayList;

    public FreeBoardAdapter(Context mContext, ArrayList<FreeBoardDTO> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fragment_board_free_list, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d("main:adater", "" + position);

        FreeBoardDTO freeBoardDTO = arrayList.get(position);
        holder.BoardItem(freeBoardDTO);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);

                BoardItem = arrayList.get(position);

                Toast.makeText(mContext, "Onclick " + arrayList.get(position).getBoard_id(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // 어댑터에 매소드 만들기

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public FreeBoardDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, FreeBoardDTO item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<FreeBoardDTO> arrayList){
        this.arrayList = arrayList;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout parentLayout;
        public TextView Board_id;
        public TextView Board_subject;
        public ImageView Board_file;
        public ProgressBar progressBar;

        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parentLayout_Free);
            Board_subject = itemView.findViewById(R.id.tv_subject);
            Board_id = itemView.findViewById(R.id.tv_name);
            Board_file = itemView.findViewById(R.id.iv_img);
            progressBar = itemView.findViewById(R.id.progressBar_Free);

            Log.d(TAG, "main:Adapter108: " + Board_id);

        }

        public void BoardItem(FreeBoardDTO freeBoardDTO){

            Board_subject.setText(freeBoardDTO.getBoard_id());
            Board_id.setText(freeBoardDTO.getBoard_content());

            Glide.with(itemView).load(freeBoardDTO.getBoard_file()).into(Board_file);
        }
    }
}