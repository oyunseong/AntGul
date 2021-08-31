package com.antgul.antgul_android.ui.valueation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentValuationBinding;
import com.antgul.antgul_android.ui.valueation.ranking.RankingFragment;

public class ValuationTabFragment extends BaseFragment<FragmentValuationBinding> {
    private RankingFragment rankingFragment;

    @Override
    protected FragmentValuationBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentValuationBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        Log.i(TAG, "start setUpView()");
        rankingFragment = new RankingFragment();
//        mainActivity.replaceFragment(R.id.valueation_frame,rankingFragment);
    }

    @Override
    protected void initClickListener() {
        binding.valueationCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("coming soon");
            }
        });
        binding.valueationLikeStockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("coming soon");
            }
        });
    }

}
