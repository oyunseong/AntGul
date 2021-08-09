package com.antgul.antgul_android.ui.valueation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerValueationAdapter extends FragmentStateAdapter {
    private final int fragmentCount = 3;

    public ViewPagerValueationAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        switch (index) {
            case 0: {
                return new RankingFragment();
            }
            case 1: {
                return new CalculatorFragment();
            }
            case 2: {
                return new InterestStockFragment();
            }
            default:
                return new RankingFragment();
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
