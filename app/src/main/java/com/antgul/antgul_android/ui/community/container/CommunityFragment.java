package com.antgul.antgul_android.ui.community.container;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentCommunityBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {

    @Override
    protected FragmentCommunityBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentCommunityBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }
}


