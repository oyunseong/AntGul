package com.antgul.antgul_android.ui.mypage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentPreferencesBinding;

import org.jetbrains.annotations.NotNull;

public class PreferencesFragment extends BaseFragment<FragmentPreferencesBinding> {
    @Override
    protected FragmentPreferencesBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentPreferencesBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}
