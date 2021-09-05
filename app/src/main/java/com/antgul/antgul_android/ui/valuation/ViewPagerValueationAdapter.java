package com.antgul.antgul_android.ui.valuation;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.antgul.antgul_android.ui.valuation.ranking.PbrFragment;
import com.antgul.antgul_android.ui.valuation.ranking.PerFragment;
import com.antgul.antgul_android.ui.valuation.ranking.PsrFragment;
import com.antgul.antgul_android.ui.valuation.ranking.TotalFragment;

public class ViewPagerValueationAdapter extends FragmentStateAdapter {
    private final int fragmentCount = 4;
    protected final String TAG = this.getClass().getSimpleName();

    public ViewPagerValueationAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i(TAG, "++createFragment");
        int index = getRealPosition(position);
        switch (index) {
            case 0: {
                return new TotalFragment();
            }
            case 1: {
                return new PerFragment();
            }
            case 2: {
                return new PbrFragment();
            }
            case 3:{
                return new PsrFragment();
            }
            default:
                return new TotalFragment();
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
