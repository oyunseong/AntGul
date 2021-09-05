package com.antgul.antgul_android.ui.valuation.calculator;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentCalculatorBinding;

import org.jetbrains.annotations.NotNull;

public class CalculatorFragment extends BaseFragment<FragmentCalculatorBinding> {
    @Override
    protected FragmentCalculatorBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentCalculatorBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
