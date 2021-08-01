package com.antgul.antgul_android.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemBoardRecyclerBinding;
import com.antgul.antgul_android.model.Board;

import java.util.ArrayList;

public class RecyclerViewBoardAdapter extends RecyclerView.Adapter<RecyclerViewBoardAdapter.ViewHolder> {
    private ArrayList<Board> mData = null;
    private OnItemClickListener itemClickListener = null;

    public RecyclerViewBoardAdapter(ArrayList<Board> mData) {
        this.mData = mData;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBoardRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    //// 변수 초기화
    //
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBoardAdapter.ViewHolder holder, int position) {

        Board board = mData.get(position);
//        holder.boardRecyclerItemBinding.boardImage.getDrawable();
        holder.ItemBoardRecyclerBinding.boardNickName.setText("익명" + position);
        holder.ItemBoardRecyclerBinding.boardTime.setText("10분 전");
        holder.ItemBoardRecyclerBinding.boardContent.setText("게시글 내용입니다.");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemBoardRecyclerBinding ItemBoardRecyclerBinding;

        public ViewHolder(@NonNull ItemBoardRecyclerBinding itemBoardRecyclerBinding) {
            super(itemBoardRecyclerBinding.getRoot());
            this.ItemBoardRecyclerBinding = itemBoardRecyclerBinding;

            itemBoardRecyclerBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (itemClickListener != null) {
                            itemClickListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }


    public void addItem(Board board) {
        mData.add(board);
        notifyDataSetChanged();
    }
}
