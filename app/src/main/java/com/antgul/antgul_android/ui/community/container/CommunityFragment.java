package com.antgul.antgul_android.ui.community.container;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentCommunityBinding;
import com.antgul.antgul_android.ui.community.StockInformationFragment;
import com.antgul.antgul_android.ui.community.board.FreeBoardFragment;
import com.antgul.antgul_android.ui.valueation.ViewPagerValueationAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {

    @Override
    protected FragmentCommunityBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentCommunityBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        initViewPagerAndTab();
    }

    @Override
    protected void initClickListener() {


    }
    private void initViewPagerAndTab() {
        String[] titles = new String[]{"종목정보", "자유게시판","글쓰기"}; // tab title

        ViewPager2 viewPager2 = binding.viewpager2;
        ViewPagerCommunityAdapter mAdapter = new ViewPagerCommunityAdapter(mainActivity);
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

        new TabLayoutMediator(binding.tabLayout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();
    }
}


