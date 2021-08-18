package com.antgul.antgul_android.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentDetailBoardBinding;

import org.jetbrains.annotations.NotNull;

public class DetailBoardFragment extends BaseFragment<FragmentDetailBoardBinding> {

    @Override
    protected FragmentDetailBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDetailBoardBinding.inflate(inflater, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO BoardFragment 에서 보낸 데이터 수신.

    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if(bundle != null){
            String str = bundle.getString("strKey");
            binding.boardNickName.setText(str);
        }
    }

    @Override
    protected void initClickListener() {

    }

}
