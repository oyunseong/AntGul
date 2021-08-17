//package com.antgul.antgul_android.ui.community;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//
//import com.antgul.antgul_android.ui.community.board.FreeBoardFragment;
//import com.antgul.antgul_android.ui.community.stock.StockInformationFragment;
//import com.antgul.antgul_android.ui.valueation.ranking.PbrFragment;
//import com.antgul.antgul_android.ui.valueation.ranking.PerFragment;
//import com.antgul.antgul_android.ui.valueation.ranking.PsrFragment;
//import com.antgul.antgul_android.ui.valueation.ranking.TotalFragment;
//
//public class ViewPagerCommunityAdapter extends FragmentStateAdapter {
//    private final int fragmentCount = 2;
//    protected final String TAG = this.getClass().getSimpleName();
//
//    public ViewPagerCommunityAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        Log.i(TAG, "++createFragment");
//        int index = getRealPosition(position);
//        switch (index) {
//            case 0: {
//                return new StockInformationFragment();
//            }
//            case 1: {
//                return new FreeBoardFragment();
//            }
//            default:
//                return new StockInformationFragment();
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return fragmentCount;
//    }
//
//    public int getRealPosition(int position) {
//        return position % fragmentCount;
//    }
//
//}
