package com.antgul.antgul_android;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.model.Stock;
import com.antgul.antgul_android.databinding.ItemLikeStockBinding;


import java.util.ArrayList;

public class LikeStockAdapter extends RecyclerView.Adapter<LikeStockAdapter.ViewHolder> {
    private ArrayList<Stock> mData = null;

    public LikeStockAdapter(ArrayList<Stock> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemLikeStockBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull LikeStockAdapter.ViewHolder holder, int position) {
        Stock stock = mData.get(position);
        holder.likeStockRecyclerItemBinding.likeStockRecyclerItemName.setText(stock.getStockName());
        holder.likeStockRecyclerItemBinding.likeStockRecyclerItemNumber.setText(stock.getStockNumber());
        holder.likeStockRecyclerItemBinding.likeStockRecyclerItemCheckbox.setChecked(mData.get(position).isChecked());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemLikeStockBinding likeStockRecyclerItemBinding;

        public ViewHolder(@NonNull ItemLikeStockBinding binding) {
            super(binding.getRoot());
            this.likeStockRecyclerItemBinding = binding;
        }
    }

    public void addItem(Stock stock) {
        mData.add(stock);
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<Stock> filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }
}
