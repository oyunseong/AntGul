package com.antgul.antgul_android;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.BoardRecyclerItemBinding;

import java.util.ArrayList;

public class RecyclerViewBoardAdapter extends RecyclerView.Adapter<RecyclerViewBoardAdapter.ViewHolder> {
    private ArrayList<Board> mData = null;

    RecyclerViewBoardAdapter(ArrayList<Board> mData){this.mData = mData;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(BoardRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    //// 변수 초기화
    //
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBoardAdapter.ViewHolder holder, int position) {

        Board board = mData.get(position);
//        holder.boardRecyclerItemBinding.boardImage.getDrawable();
        holder.boardRecyclerItemBinding.boardNickName.setText("익명"+position);
        holder.boardRecyclerItemBinding.boardTime.setText("10분 전");
        holder.boardRecyclerItemBinding.boardContent.setText("게시글 내용입니다.");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final BoardRecyclerItemBinding boardRecyclerItemBinding;

        public ViewHolder(@NonNull BoardRecyclerItemBinding boardRecyclerItemBinding) {
            super(boardRecyclerItemBinding.getRoot());
            this.boardRecyclerItemBinding = boardRecyclerItemBinding;

        }
    }
    public void addItem(Board board){
        mData.add(board);
        notifyDataSetChanged();;
    }
}
