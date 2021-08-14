package com.antgul.antgul_android;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.ui.start.SplashFragment;
import com.antgul.antgul_android.util.BackPressHandler;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private BackPressHandler backPressHandler;

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
        callFragmentWithoutBackStack(binding.fragmentFrame.getId(), new SplashFragment());
    }

    public int getFrameId(){
        Log.i(TAG,"++getFrameId");
        return binding.fragmentFrame.getId();
    }

    // TODO builder pattern 체크. java default parameter
    public void callFragmentWithBackStack(int id, Fragment fragment){        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // enter = 들어올 프래그먼트 , exit= 떠있는 프래그먼트
//        transaction.setCustomAnimations(R.anim.anim_enter_test, R.anim.anim_exit_test,R.anim.anim_enter_test, R.anim.anim_exit_test);
        transaction.replace(id,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void callFragmentWithoutBackStack(int id, Fragment fragment){        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id,fragment);
        transaction.commit();
    }

//    @Override
//    public void onBackPressed() {
//        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료");
//    }
}