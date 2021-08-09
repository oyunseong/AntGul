package com.antgul.antgul_android;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.ui.start.SplashFragment;
import com.antgul.antgul_android.util.BackPressHandler;

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

        callFragmentWithoutBackStack(splashFragment);
    }


    // TODO builder pattern 체크. java default parameter
    public void callFragmentWithBackStack(Fragment fragment){        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // enter = 들어올 프래그먼트 , exit= 떠있는 프래그먼트
//        transaction.setCustomAnimations(R.anim.anim_enter_test, R.anim.anim_exit_test,R.anim.anim_enter_test, R.anim.anim_exit_test);
        transaction.replace(binding.fragmentFrame.getId(),fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void callFragmentWithoutBackStack(Fragment fragment){        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(binding.fragmentFrame.getId(),fragment);
//        transaction.setCustomAnimations(R.anim.anim_exit_test, R.anim.anim_enter_test);
        transaction.commit();
    }

//    @Override
//    public void onBackPressed() {
//        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료");
//    }
}