package com.antgul.antgul_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.LikeStockRecyclerItemBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LikeStockAdapter extends RecyclerView.Adapter<LikeStockAdapter.ViewHolder> {
    private LikeStockRecyclerItemBinding binding;
    private ArrayList<Stock> mData = null;

    public LikeStockAdapter(ArrayList<Stock> mData){
        this.mData = mData;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰 홀더 객체를 생성하여 리턴합니다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view =inflater.inflate(R.layout.like_stock_recycler_item,parent,false);
        LikeStockAdapter.ViewHolder holder = new LikeStockAdapter.ViewHolder(view);
        return holder;
    }

    //position에 해당하는 데이터 뷰홀더의 아이템뷰 표시
    @Override
    public void onBindViewHolder(@NonNull  LikeStockAdapter.ViewHolder holder, int position) {
        // 뷰홀더 선언하고 mData 전달
        Stock stock = mData.get(position);
        holder.itemName.setText(stock.getStockName());
        holder.itemNumber.setText(stock.getStockNumber());
        holder.checkBox.setChecked(mData.get(position).isChecked());

    }

    // 전체 아이템 개수 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView itemName;
        protected TextView itemNumber;
        protected CheckBox checkBox;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.like_stock_recycler_item_name);
            itemNumber = itemView.findViewById(R.id.like_stock_recycler_item_number);
            checkBox = itemView.findViewById(R.id.like_stock_recycler_item_checkbox);
        }
    }

    // ArrayList에 item을 추가하는 함수입니다.
    public void addItem(Stock stock) {
        mData.add(stock);
        notifyDataSetChanged();
    }
    public void filterList(ArrayList<Stock> filteredList)
    {
        mData = filteredList;
        notifyDataSetChanged();
    }
}
