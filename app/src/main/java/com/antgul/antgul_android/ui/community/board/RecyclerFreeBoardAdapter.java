package com.antgul.antgul_android.ui.community.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Post;

import java.util.ArrayList;

public class RecyclerFreeBoardAdapter extends RecyclerView.Adapter<RecyclerFreeBoardAdapter.ViewHolder> {
    private ArrayList<Post> mData = null;
    private OnItemClickListener itemClickListener = null;

    public RecyclerFreeBoardAdapter(ArrayList<Post> mData) {
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
        return new ViewHolder(ItemStockInfoRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerFreeBoardAdapter.ViewHolder holder, int position) {
        Post post = mData.get(position);
        holder.itemStockInfoRecyclerBinding.title.setText(post.getPostId());
        holder.itemStockInfoRecyclerBinding.stockName.setText(post.getWriterId());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding;


        public ViewHolder(@NonNull ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding) {
            super(itemStockInfoRecyclerBinding.getRoot());
            this.itemStockInfoRecyclerBinding = itemStockInfoRecyclerBinding;

            itemStockInfoRecyclerBinding.getRoot().setOnClickListener(v -> {
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

    public void addItem(Post Post) {
        mData.add(Post);
        notifyItemInserted(getItemCount());
    }
}
