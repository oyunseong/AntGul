package com.antgul.antgul_android.ui.board;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentWriteBoardBinding;

import org.jetbrains.annotations.NotNull;

public class WriteBoardFragment extends BaseFragment<FragmentWriteBoardBinding> {
    @Override
    protected FragmentWriteBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentWriteBoardBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
