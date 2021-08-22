package com.antgul.antgul_android.ui.community;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentCommunityBinding;
import com.antgul.antgul_android.ui.community.post.FreeBoardFragment;
import com.antgul.antgul_android.ui.community.post.StockInfoFragment;

public class CommunityTabFragment extends BaseFragment<FragmentCommunityBinding> {
    StockInfoFragment stockInfoFragment;
    FreeBoardFragment freeBoardFragment;

    @Override
    protected FragmentCommunityBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentCommunityBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        stockInfoFragment = new StockInfoFragment();
        freeBoardFragment = new FreeBoardFragment();
        mainActivity.addFragment(binding.communityTabContainer.getId(), stockInfoFragment);
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
            mainActivity.replaceFragment(binding.communityTabContainer.getId(), new StockInfoFragment());
            binding.stockInfoButton.setSelected(true);
            binding.freeboardButton.setSelected(false);
        });
    }

    private void onClickFreeBoardButton() {
        binding.freeboardButton.setOnClickListener(view -> {
            mainActivity.replaceFragment(binding.communityTabContainer.getId(), freeBoardFragment);;
            binding.freeboardButton.setSelected(true);
            binding.stockInfoButton.setSelected(false);
        });
    }
}


