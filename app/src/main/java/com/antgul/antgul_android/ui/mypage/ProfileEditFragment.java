package com.antgul.antgul_android.ui.mypage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentProfileEditBinding;

import org.jetbrains.annotations.NotNull;

public class ProfileEditFragment extends BaseFragment<FragmentProfileEditBinding> {
    @Override
    protected FragmentProfileEditBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentProfileEditBinding.inflate(inflater,container,false);
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
