package com.antgul.antgul_android;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentFreeBoardBinding;

public class FreeBoardFragment extends BaseFragment<FragmentFreeBoardBinding> {
    @Override
    protected FragmentFreeBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFreeBoardBinding.inflate(inflater,container,false);
    }

    @Override
    protected void setUpView() {

    }
}
