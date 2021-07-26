package com.antgul.antgul_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentLikeStockBinding;
import com.antgul.antgul_android.databinding.FragmentMyPageBinding;

import java.util.ArrayList;

public class LikeStockFragment extends BaseFragment<FragmentLikeStockBinding> {
    private RecyclerView recyclerView = null;
    private LikeStockAdapter likeStockAdapter = null;
    ArrayList<Stock> mData = null;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected FragmentLikeStockBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentLikeStockBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setUpView() {
        // 관심종목 주식이름, 번호 담을 리스트 생성
        mData = new ArrayList<>();
        // 리스트 어댑터에 전달
        likeStockAdapter =new LikeStockAdapter(mData);
        // 수직으로 아이템뷰 배치
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        // 리사이클러뷰 모양
        recyclerView.setLayoutManager(layoutManager);
        // 리사이클러뷰 적용
        recyclerView.setAdapter(likeStockAdapter);

        // 초기 데이터 생성
        for(int i=0; i<10; i++)
        {
            addItem("삼성전자","0000000",false);
        }
        likeStockAdapter.notifyDataSetChanged();
    }
    public void addItem(String stockName, String stockNumber, boolean checked)
    {
        Stock stock = new Stock();
        stock.setStockName(stockName);
        stock.setStockNumber(stockNumber);
        stock.setChecked(checked);

        likeStockAdapter.addItem(stock);
        likeStockAdapter.notifyDataSetChanged();
    }
}
