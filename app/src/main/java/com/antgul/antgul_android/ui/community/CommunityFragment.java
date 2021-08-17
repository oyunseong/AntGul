package com.antgul.antgul_android.ui.community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentCommunityBinding;
import com.antgul.antgul_android.ui.community.board.FreeBoardFragment;
import com.antgul.antgul_android.ui.community.stock.StockInformationFragment;
import com.antgul.antgul_android.ui.home.HomeFragment;
import com.antgul.antgul_android.ui.valueation.ViewPagerValueationAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class CommunityFragment extends BaseFragment<FragmentCommunityBinding> {
    StockInformationFragment stockInformationFragment;
    FreeBoardFragment freeBoardFragment;

    @Override
    protected FragmentCommunityBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentCommunityBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        stockInformationFragment = new StockInformationFragment();
        freeBoardFragment = new FreeBoardFragment();
        mainActivity.callFragmentWithoutBackStack(binding.communityFrame.getId(),stockInformationFragment);
        binding.stockInfoButton.setSelected(true);
    }

    @Override
    protected void initClickListener() {
        onClickStockInfoButton();
        onClickFreeBoardButton();
    }

    private void onClickStockInfoButton() {
        //TODO addToBackStack 발생시 select 변화없음 수정필요
        binding.stockInfoButton.setOnClickListener(view -> {
            mainActivity.callFragmentWithoutBackStack(binding.communityFrame.getId(), new StockInformationFragment());
            binding.stockInfoButton.setSelected(true);
            binding.freeboardButton.setSelected(false);
        });

    }

    private void onClickFreeBoardButton() {
        binding.freeboardButton.setOnClickListener(view -> {
            mainActivity.callFragmentWithBackStack(binding.communityFrame.getId(), freeBoardFragment);;
            binding.freeboardButton.setSelected(true);
            binding.stockInfoButton.setSelected(false);
        });
    }
}


