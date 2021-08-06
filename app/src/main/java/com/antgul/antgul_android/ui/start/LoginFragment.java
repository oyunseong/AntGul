package com.antgul.antgul_android.ui.start;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentLoginBinding;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {
    @Override
    protected FragmentLoginBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable  ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
