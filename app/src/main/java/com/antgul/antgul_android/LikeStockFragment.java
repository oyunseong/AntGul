package com.antgul.antgul_android;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.model.Stock;
import com.antgul.antgul_android.databinding.FragmentLikeStockBinding;

import java.util.ArrayList;

public class LikeStockFragment extends BaseFragment<FragmentLikeStockBinding> {
    private LikeStockAdapter likeStockAdapter = null;
    ArrayList<Stock> mData,filteredList = null;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected FragmentLikeStockBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentLikeStockBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();
        filteredList = new ArrayList<>();
        likeStockAdapter = new LikeStockAdapter(mData);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.likeStockRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.likeStockRecyclerview.setAdapter(likeStockAdapter);

        for (int i = 0; i < 30; i++) {
            addItem("name"+i, "000000"+i, false);
        }

        likeStockAdapter.notifyDataSetChanged();

        binding.likeStockEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = binding.likeStockEtSearch.getText().toString();
                searchFilter(searchText);
            }
        });

    }

    @Override
    protected void initClickListener() {

    }

    public void addItem(String stockName, String stockNumber, boolean checked) {
        Stock stock = new Stock();
        stock.setStockName(stockName);
        stock.setStockNumber(stockNumber);
        stock.setChecked(checked);
        likeStockAdapter.addItem(stock);
    }

    public void searchFilter(String searchText) {
        mData.clear();

        for (int i = 0; i < likeStockAdapter.getItemCount(); i++) {
            if(mData.get(i).getStockName().toLowerCase().contains(searchText.toLowerCase())){
                filteredList.add(mData.get(i));
            }
        }
        likeStockAdapter.filterList(mData);
    }
}
