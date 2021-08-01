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
    protected void initView() {
        //init code..
        setInit();
    }

    @Override
    protected void initClickListener() {
    }

    // viewPager2 실행 메서드
    private void setInit() {
        final float pageMargin = (float) getResources().getDimensionPixelOffset(R.dimen.pageMargin);// 페이지끼리 보이는 간격
        final float pageOffset = (float) getResources().getDimensionPixelOffset(R.dimen.pageOffset);// 페이지 보이는 정도
        String[] titles = new String[]{"전체", "관심종목","테스트1","테스트2","테스트3"}; // tab title

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

        // 스와이프 애니메이션 효과
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float offset = position * -(2 * pageOffset + pageMargin);
                if (-1 > position) {
                    page.setTranslationX(-offset);
                } else if (1 >= position) {
                    float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                    page.setTranslationX(offset);
                    page.setScaleY(scaleFactor);
                    page.setAlpha(scaleFactor);
                } else {
                    page.setAlpha(0f);
                    page.setTranslationX(offset);
                }

            }
        });
        /*
        *    displaying tabLayout
        *    attach 메서드가 ViewPage2가 어댑터를 가진후에 호출되기 때문에 어댑터에 setAdapter 적용 후에
        *    TabLayoutMediator 를 선언해야합니다.
        */
        new TabLayoutMediator(binding.boardTabLayout, viewPager2,(tab, position) -> tab.setText(titles[position])).attach();
    }
}

