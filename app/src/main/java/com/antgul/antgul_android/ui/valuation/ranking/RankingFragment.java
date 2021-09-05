package com.antgul.antgul_android.ui.valuation.ranking;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentRankingBinding;
import com.antgul.antgul_android.ui.valuation.ViewPagerValueationAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

public class RankingFragment extends BaseFragment<FragmentRankingBinding> {
    @Override
    protected FragmentRankingBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentRankingBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {
        Log.i(TAG, "start setUpView()");
        initViewPagerAndTab();
    }

    @Override
    protected void initClickListener() {

    }

    private void initViewPagerAndTab() {
        String[] titles = new String[]{"종합", "PER", "PBR", "PSR", "배너2"}; // tab title

        ViewPager2 viewPager2 = binding.valueationViewpager;
        ViewPagerValueationAdapter mAdapter = new ViewPagerValueationAdapter(mainActivity);
        viewPager2.setAdapter(mAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setOffscreenPageLimit(6);
        viewPager2.setCurrentItem(100);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        new TabLayoutMediator(binding.valueationTabLayout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();
    }
}
