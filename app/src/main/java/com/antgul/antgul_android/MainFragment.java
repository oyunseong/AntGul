package com.antgul.antgul_android;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentMainBinding;
import com.antgul.antgul_android.ui.community.container.CommunityFragment;
import com.antgul.antgul_android.ui.home.HomeFragment;
import com.antgul.antgul_android.ui.mypage.MyPageFragment;
import com.antgul.antgul_android.ui.valueation.ValueationFragment;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends BaseFragment<FragmentMainBinding> {
    HomeFragment homeFragment;
    ValueationFragment valueationFragment;
    CommunityFragment communityFragment;
    MyPageFragment myPageFragment;
    @Override
    protected FragmentMainBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        homeFragment = new HomeFragment();
        valueationFragment =new ValueationFragment();
        communityFragment = new CommunityFragment();
        myPageFragment = new MyPageFragment();
        mainActivity.callFragmentWithoutBackStack(binding.fragmentMainFrame.getId(), new HomeFragment());
    }

    @Override
    protected void initClickListener() {
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.menu_home:
                        mainActivity.callFragmentWithoutBackStack(binding.fragmentMainFrame.getId(), homeFragment);
                        break;
                    case R.id.menu_valueation:
                        mainActivity.callFragmentWithoutBackStack(binding.fragmentMainFrame.getId(), valueationFragment);
                        break;
                    case R.id.menu_community:
                        mainActivity.callFragmentWithoutBackStack(binding.fragmentMainFrame.getId(), communityFragment);
                        break;
                    case R.id.menu_my_page:
                        mainActivity.callFragmentWithoutBackStack(binding.fragmentMainFrame.getId(), myPageFragment);
                        break;
                }
                return true;
            }
        });
    }

}
