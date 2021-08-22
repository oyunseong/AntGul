package com.antgul.antgul_android.ui.valueation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentValueationBinding;
import com.antgul.antgul_android.ui.valueation.ranking.RankingFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class ValuationTabFragment extends BaseFragment<FragmentValueationBinding> {
    private RankingFragment rankingFragment;

    @Override
    protected FragmentValueationBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentValueationBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        Log.i(TAG, "start setUpView()");
        rankingFragment = new RankingFragment();
//        mainActivity.replaceFragment(R.id.valueation_frame,rankingFragment);
    }

    @Override
    protected void initClickListener() {

    }

}
