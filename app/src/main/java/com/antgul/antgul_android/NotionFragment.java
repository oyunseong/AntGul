package com.antgul.antgul_android;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentNotionBinding;

public class NotionFragment extends BaseFragment<FragmentNotionBinding> {

    @Override
    protected FragmentNotionBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentNotionBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        Log.i(TAG, "start setUpView()");
        //뷰 초기화 작업.
        binding.notionBtn.setText("뷰 초기화");

        binding.notionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("클릭");
            }
        });
    }

    @Override
    protected void initClickListener() {

    }

}
