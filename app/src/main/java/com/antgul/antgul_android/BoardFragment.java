package com.antgul.antgul_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentBoardBinding;
import com.google.android.material.tabs.TabLayout;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {
    private MainActivity mainActivity;
    private ViewGroup viewGroup;

    @Override
    protected FragmentBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBoardBinding.inflate(inflater, container, false);
    }

    // 프래그먼트가 액티비티에 올라는 순간 호출됩니다.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    //
    @Override
    public void onDetach() {
        super.onDetach();

        mainActivity = null;
    }

    @Override
    protected void setUpView() {
        //init code..
        setInit();

    }

    private void setInit() {
        ViewPager2 viewPager2 = binding.boardViewpager;
        ViewPagerBoardAdapter viewPagerBoardAdapter = new ViewPagerBoardAdapter(getActivity());
        viewPager2.setAdapter(viewPagerBoardAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL); // 방향은 가로로 설정합니다.
        viewPager2.setOffscreenPageLimit(3); // 페이지 개수 한정
        viewPager2.setCurrentItem(100); // 무제한 스크롤 처럼 보이기

        final float pageMargin = (float) getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = (float) getResources().getDimensionPixelOffset(R.dimen.pageOffset);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float offset = position * -(2 * pageOffset + pageMargin);
                if (-1 > position) {
                    page.setTranslationX(-offset);
                } else if (1 >= position)
                {
                    float scaleFactor = Math.max(0.7f,1-Math.abs(position-0.14285715f));
                    page.setTranslationX(offset);
                    page.setScaleY(scaleFactor);
                    page.setAlpha(scaleFactor);
                }else
                {
                    page.setAlpha(0f);
                    page.setTranslationX(offset);
                }

            }
        });
    }
}

