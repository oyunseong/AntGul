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
import com.antgul.antgul_android.ui.community.CommunityFragment;
import com.antgul.antgul_android.ui.home.HomeFragment;
import com.antgul.antgul_android.ui.mypage.MyPageFragment;
import com.antgul.antgul_android.ui.valueation.ValueationFragment;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends BaseFragment<FragmentMainBinding> {
    private HomeFragment homeFragment;
    private CommunityFragment communityFragment;
    private ValueationFragment valueationFragment;
    private MyPageFragment myPageFragment;

    @Override
    protected FragmentMainBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        homeFragment = new HomeFragment();
        valueationFragment = new ValueationFragment();
        communityFragment = new CommunityFragment();
        myPageFragment = new MyPageFragment();

        callFragmentWithBackStack(homeFragment);
    }

    @Override
    protected void initClickListener() {
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.menu_home:
                        callFragmentWithBackStack(homeFragment);
                        break;
                    case R.id.menu_valueation:
                        callFragmentWithBackStack(valueationFragment);
                        break;
                    case R.id.menu_community:
                        callFragmentWithBackStack(communityFragment);
                        break;
                    case R.id.menu_my_page:
                        callFragmentWithBackStack(myPageFragment);
                        break;
                }
                return true;
            }
        });
    }
    public void callFragmentWithBackStack(Fragment fragment){        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // enter = 들어올 프래그먼트 , exit= 떠있는 프래그먼트
//        transaction.setCustomAnimations(R.anim.anim_enter_test, R.anim.anim_exit_test,R.anim.anim_enter_test, R.anim.anim_exit_test);
        transaction.replace(binding.fragmentMainFrame.getId(),fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
