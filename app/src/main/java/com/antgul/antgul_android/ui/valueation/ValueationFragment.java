package com.antgul.antgul_android.ui.valueation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentValueationBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ValueationFragment extends BaseFragment<FragmentValueationBinding> {

    @Override
    protected FragmentValueationBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentValueationBinding.inflate(inflater, container, false);
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
        String[] titles = new String[]{"랭킹", "계산기", "관심종목", "배너1", "배너2"}; // tab title

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
