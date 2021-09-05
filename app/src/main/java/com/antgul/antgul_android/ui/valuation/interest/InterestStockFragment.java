package com.antgul.antgul_android.ui.valuation.interest;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentInterestStockBinding;

import org.jetbrains.annotations.NotNull;

public class InterestStockFragment extends BaseFragment<FragmentInterestStockBinding> {
    @Override
    protected FragmentInterestStockBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentInterestStockBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
