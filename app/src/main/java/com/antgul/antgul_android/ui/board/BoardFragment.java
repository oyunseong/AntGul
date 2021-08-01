package com.antgul.antgul_android.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentBoardBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {

    @Override
    protected FragmentBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setUpView() {
        setInit();
    }

    private void setInit() {
        String[] titles = new String[]{"전체", "자유게시판","테스트1","테스트2","테스트3"}; // tab title

        ViewPager2 viewPager2 = binding.boardViewpager;                                             // 뷰페이저를 참조합니다.
        ViewPagerBoardAdapter viewPagerBoardAdapter = new ViewPagerBoardAdapter(mainActivity);     // 프래그먼트에서는 getActivity로 참조합니다.
        viewPager2.setAdapter(viewPagerBoardAdapter);                                               // 어댑터를 파라미터로 받고 viewpager2에 전달합니다.
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);                               // 방향은 가로로 설정합니다.
        viewPager2.setOffscreenPageLimit(6);                                                        // 페이지 개수 한정
        viewPager2.setCurrentItem(100);                                                             // 무제한 스크롤 처럼 보이기
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

        new TabLayoutMediator(binding.boardTabLayout, viewPager2,(tab, position) -> tab.setText(titles[position])).attach();
    }
}

