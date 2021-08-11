package com.antgul.antgul_android.ui.community.stock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Post;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewStockInfoAdapter extends RecyclerView.Adapter<RecyclerViewStockInfoAdapter.ViewHolder> {
    private ArrayList<Post> mData = null;
    private OnItemClickListener onItemClickListener = null;

    public RecyclerViewStockInfoAdapter(ArrayList<Post> mData) {
        this.mData = mData;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemStockInfoRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding;
        public ViewHolder(@NonNull @NotNull ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding) {
            super(itemStockInfoRecyclerBinding.getRoot());
            this.itemStockInfoRecyclerBinding = itemStockInfoRecyclerBinding;
            itemStockInfoRecyclerBinding.getRoot().setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, pos);
                        //TODO 리사이클러뷰 아이템 클릭시 DetailBoardFragment로 이동시키기
                    }
                }
            });
        }
    }
}
