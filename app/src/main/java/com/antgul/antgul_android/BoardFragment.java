package com.antgul.antgul_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentBoardBinding;
import com.google.android.material.tabs.TabLayout;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {
    MainActivity mainActivity;
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

        binding.boardTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // tab의 상태가 선택되지 않음에서 선택 상태로 변경됨.
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

            // tab의 상태가 선택 상태에서 선택되지 않음으로 변경됨
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            // 이미 선택된 상태의 tab이 사용자에 의해 다시 선택됨
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void changeView(int index) {
        switch (index) {
            case 0:
                binding.boardTab1.setVisibility(View.VISIBLE);
                binding.boardTab2.setVisibility(View.INVISIBLE);
                binding.boardTab3.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
