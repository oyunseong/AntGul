package com.antgul.antgul_android.ui.start;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentStartBinding;
import com.antgul.antgul_android.ui.start.login.LoginFragment;
import com.antgul.antgul_android.ui.start.signup.SignUpFragment;

import org.jetbrains.annotations.NotNull;

public class StartFragment extends BaseFragment<FragmentStartBinding> {

    @Override
    protected FragmentStartBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, ViewGroup container) {
        return FragmentStartBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {
        binding.startLoginButton.setOnClickListener(v -> mainActivity.replaceFragment(R.id.activity_main_container, new LoginFragment()));
        binding.startSignupButton.setOnClickListener(v -> mainActivity.replaceFragment(R.id.activity_main_container, new SignUpFragment()));
    }
}
