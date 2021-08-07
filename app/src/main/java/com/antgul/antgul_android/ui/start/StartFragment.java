package com.antgul.antgul_android.ui.start;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentStartBinding;
import com.antgul.antgul_android.ui.start.LoginFragment;
import com.antgul.antgul_android.ui.start.SignUpFragment;

import org.jetbrains.annotations.NotNull;

public class StartFragment extends BaseFragment<FragmentStartBinding> {
    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;

    @Override
    protected FragmentStartBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, ViewGroup container) {
        return FragmentStartBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment();
    }

    @Override
    protected void initClickListener() {
        binding.startLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO LoginFragment 로 이동
                mainActivity.callFragment(loginFragment);
            }
        });
        binding.startSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO SignUpFragment 로 이동
                mainActivity.callFragment(signUpFragment);
            }
        });

    }
}
