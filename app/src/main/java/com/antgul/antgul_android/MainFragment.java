package com.antgul.antgul_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.antgul.antgul_android.ui.community.CommunityTabFragment;
import com.antgul.antgul_android.ui.home.HomeTabFragment;
import com.antgul.antgul_android.ui.mypage.ProfileTabFragment;
import com.antgul.antgul_android.ui.valueation.ValuationTabFragment;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends BaseFragment<FragmentMainBinding> {
    HomeTabFragment homeTabFragment;
    ValuationTabFragment valuationTabFragment;
    CommunityTabFragment communityTabFragment;
    ProfileTabFragment profileTabFragment;

    FragmentManager fragmentManager;

    @Override
    protected FragmentMainBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getChildFragmentManager();

        homeTabFragment = new HomeTabFragment();
        valuationTabFragment = new ValuationTabFragment();
        communityTabFragment = new CommunityTabFragment();
        profileTabFragment = new ProfileTabFragment();
    }

    @Override
    protected void initView() {
        mainActivity.replaceFragment(binding.mainFragmentContainer.getId(), homeTabFragment);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void initClickListener() {
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuitem) {
                BottomNavigate(menuitem.getItemId());
                return true;
            }
        });
    }

    private void BottomNavigate(int id) {
        String tag = String.valueOf(id);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            if (id == R.id.menu_home) {
                fragment = new HomeTabFragment();
            } else if (id == R.id.menu_valuation) {
                fragment = new ValuationTabFragment();
            } else if (id == R.id.menu_community) {
                fragment = new CommunityTabFragment();
            } else if (id == R.id.menu_profile) {
                fragment = new ProfileTabFragment();
            }
            if (fragment != null) {
                transaction.add(binding.mainFragmentContainer.getId(), fragment, tag);
            }
        } else {
            transaction.show(fragment);
        }
        transaction.setPrimaryNavigationFragment(fragment);
        transaction.setReorderingAllowed(true);
        transaction.commitNow();

    }
}
