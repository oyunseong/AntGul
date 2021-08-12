package com.antgul.antgul_android.ui.valueation.ranking;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentIndicatorBinding;

import org.jetbrains.annotations.NotNull;

public class PerFragment extends BaseFragment<FragmentIndicatorBinding> {
    @Override
    protected FragmentIndicatorBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentIndicatorBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
