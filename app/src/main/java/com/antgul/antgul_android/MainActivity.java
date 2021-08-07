package com.antgul.antgul_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
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

        callFragment(splashFragment);
    }


    // TODO builder pattern 체크. java default parameter
    public void callFragment(Fragment fragment){        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_tpo, R.anim.slide_out_top);
        transaction.replace(binding.fragmentFrame.getId(),fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    @Override
//    public void onBackPressed() {
//        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료");
//    }
}