package com.antgul.antgul_android.ui.community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBoardAdapter.ViewHolder holder, int position) {

        Board board = mData.get(position);
//        holder.boardRecyclerItemBinding.boardImage.getDrawable();
        holder.itemBoardRecyclerBinding.boardNickName.setText("익명" + position);
        holder.itemBoardRecyclerBinding.boardTime.setText("10분 전");
        holder.itemBoardRecyclerBinding.boardContent.setText("게시글 내용입니다.");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemBoardRecyclerBinding itemBoardRecyclerBinding;

        public ViewHolder(@NonNull ItemBoardRecyclerBinding itemBoardRecyclerBinding) {
            super(itemBoardRecyclerBinding.getRoot());
            this.itemBoardRecyclerBinding = itemBoardRecyclerBinding;

            itemBoardRecyclerBinding.getRoot().setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, pos);
                        //TODO 리사이클러뷰 아이템 클릭시 DetailBoardFragment로 이동시키기
                    }
                }
            });
        }
    }

    public void addItem(Board board) {
        mData.add(board);
        notifyItemInserted(getItemCount());
    }
}
