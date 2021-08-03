package com.antgul.antgul_android.ui.community;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentBoardBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {

    @Override
    protected FragmentBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        initViewPagerAndTab();
    }

    @Override
    protected void initClickListener() {
        binding.writeButton.setOnClickListener(v -> {
           // mainActivity.callFragment(MainActivity.FRAGMENT_WRITE_BOARD);
        });
    }

    private void initViewPagerAndTab() {
        String[] titles = new String[]{"전체", "관심종목","테스트1","테스트2","테스트3"}; // tab title

        ViewPager2 viewPager2 = binding.boardViewpager;
        ViewPagerBoardAdapter viewPagerBoardAdapter = new ViewPagerBoardAdapter(mainActivity);
        viewPager2.setAdapter(viewPagerBoardAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setOffscreenPageLimit(6);
        viewPager2.setCurrentItem(100);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        new TabLayoutMediator(binding.boardTabLayout, viewPager2,(tab, position) -> tab.setText(titles[position])).attach();

        binding.writeButton.bringToFront();
    }
}

