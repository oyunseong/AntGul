package com.antgul.antgul_android.ui.valueation.ranking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemIndicatorRecyclerBinding;
import com.antgul.antgul_android.model.Stock;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerTotalAdapter extends RecyclerView.Adapter<RecyclerTotalAdapter.ViewHolder> {
    private ArrayList<Stock> mData;

    public RecyclerTotalAdapter(ArrayList<Stock> mData) {
        this.mData = mData;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemIndicatorRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Stock stock =new Stock();
        holder.itemIndicatorRecyclerBinding.stockName.setText(stock.getStockName());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemIndicatorRecyclerBinding itemIndicatorRecyclerBinding;

        public ViewHolder(@NonNull ItemIndicatorRecyclerBinding itemIndicatorRecyclerBinding) {
            super(itemIndicatorRecyclerBinding.getRoot());
            this.itemIndicatorRecyclerBinding = itemIndicatorRecyclerBinding;
        }
    }
    public void addItem(Stock stock) {
        mData.add(stock);
        notifyDataSetChanged();
    }
}
