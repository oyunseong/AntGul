package com.antgul.antgul_android.ui.board;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.DetailBoardBinding;

import org.jetbrains.annotations.NotNull;

public class DetailBoardFragment extends BaseFragment<DetailBoardBinding> {
    private int mNumber = 0;

    @Override
    protected DetailBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return DetailBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}