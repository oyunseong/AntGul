package com.antgul.antgul_android;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.data.Stock;
import com.antgul.antgul_android.databinding.LikeStockRecyclerItemBinding;


import java.util.ArrayList;

public class LikeStockAdapter extends RecyclerView.Adapter<LikeStockAdapter.ViewHolder> {
    private ArrayList<Stock> mData = null;

    public LikeStockAdapter(ArrayList<Stock> mData) {
        this.mData = mData;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰 홀더 객체를 생성하여 리턴합니다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LikeStockRecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false));

    }

    //position에 해당하는 데이터 뷰홀더의 아이템뷰 표시
    @Override
    public void onBindViewHolder(@NonNull LikeStockAdapter.ViewHolder holder, int position) {
        // 뷰홀더 선언하고 mData 전달
        Stock stock = mData.get(position);
        holder.likeStockRecyclerItemBinding.likeStockRecyclerItemName.setText(stock.getStockName());
        holder.likeStockRecyclerItemBinding.likeStockRecyclerItemNumber.setText(stock.getStockNumber());
        holder.likeStockRecyclerItemBinding.likeStockRecyclerItemCheckbox.setChecked(mData.get(position).isChecked());
    }

    // 전체 아이템 개수 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LikeStockRecyclerItemBinding likeStockRecyclerItemBinding;

        public ViewHolder(@NonNull LikeStockRecyclerItemBinding likeStockRecyclerItemBinding) {
            super(likeStockRecyclerItemBinding.getRoot());
            this.likeStockRecyclerItemBinding = likeStockRecyclerItemBinding;
        }
    }

    // ArrayList에 item을 추가하는 함수입니다.
    public void addItem(Stock stock) {
        mData.add(stock);
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<Stock> filteredList) {
        mData = filteredList;
        notifyDataSetChanged();
    }
}
