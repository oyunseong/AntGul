package com.antgul.antgul_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.ui.community.BoardFragment;
import com.antgul.antgul_android.ui.home.HomeFragment;
import com.antgul.antgul_android.ui.mypage.MyPageFragment;
import com.antgul.antgul_android.ui.start.SplashFragment;
import com.antgul.antgul_android.util.BackPressHandler;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private BackPressHandler backPressHandler;
    private SplashFragment splashFragment;

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backPressHandler= new BackPressHandler(this);
        splashFragment = new SplashFragment();

        callFragment(binding.fragmentFrame.getId(),splashFragment);
    }

    public int getFrameLayoutId(){
        return binding.fragmentFrame.getId();
    }

    public void callFragment(int id, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id,fragment);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료");
    }
}