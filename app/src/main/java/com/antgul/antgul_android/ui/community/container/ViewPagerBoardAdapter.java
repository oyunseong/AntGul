package com.antgul.antgul_android.ui.community.container;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.antgul.antgul_android.ui.community.board.FreeBoardFragment;
import com.antgul.antgul_android.ui.community.StockInformationFragment;

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
                return new StockInformationFragment();
            }
            case 1: {
                return new FreeBoardFragment();
            }
            default:
                return new StockInformationFragment();
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
