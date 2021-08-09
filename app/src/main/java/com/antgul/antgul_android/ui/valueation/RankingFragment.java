package com.antgul.antgul_android.ui.valueation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentRankingBinding;

import org.jetbrains.annotations.NotNull;

public class RankingFragment extends BaseFragment<FragmentRankingBinding> {
    @Override
    protected FragmentRankingBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentRankingBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
