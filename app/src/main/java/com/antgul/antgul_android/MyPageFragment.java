package com.antgul.antgul_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentMyPageBinding;
import com.antgul.antgul_android.databinding.FragmentNotionBinding;

public class MyPageFragment extends BaseFragment<FragmentMyPageBinding> {
    MainActivity mainActivity;


    @Override
    protected FragmentMyPageBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMyPageBinding.inflate(inflater, container, false);
    }


    @Override
    protected void setUpView() {

    }
}
