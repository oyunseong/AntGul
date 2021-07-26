package com.antgul.antgul_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentBoardBinding;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {
    MainActivity mainActivity;
    @Override
    protected FragmentBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setUpView() {
        //init code..
        binding.textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.callFragment(4);
            }
        });
    }
}
