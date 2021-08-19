package com.antgul.antgul_android.ui.community;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.base.ProgressDialog;
import com.antgul.antgul_android.databinding.FragmentDetailBoardBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class DetailBoardFragment extends BaseFragment<FragmentDetailBoardBinding> {
    private String documentId;

    @Override
    protected FragmentDetailBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDetailBoardBinding.inflate(inflater, container, false);
    }




    @Override
    protected void initView() {
        if(getArguments() != null){
            documentId = getArguments().getString("docId");
            binding.boardContent.setText(documentId);
        }
    }

    @Override
    protected void initClickListener() {

    }

}
