package com.antgul.antgul_android.ui.board;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.antgul.antgul_android.TestFragment;

public class ViewPagerBoardAdapter extends FragmentStateAdapter {
    private final int fragmentCount = 2;


    public ViewPagerBoardAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        switch (index) {
            case 0: {
                return new TestFragment();
            }
            case 1: {
                return new FreeBoardFragment();
            }
            default:
                return new TestFragment();
        }
    }

    @Override
    public int getItemCount() {
        return fragmentCount;
    }

    public int getRealPosition(int position) {
        return position % fragmentCount;
    }
}
