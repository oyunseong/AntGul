package com.antgul.antgul_android.ui.start;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainFragment;
import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentSplashBinding;

import org.jetbrains.annotations.NotNull;


public class SplashFragment extends BaseFragment<FragmentSplashBinding> {

    @Override
    protected FragmentSplashBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSplashBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        /**
         * 1초 간 지연 후, run() 콜백 실행됨.
         * 일정 시간동안 타이머를 돌리고, 시간이 되면, run()이 호출됨.
         */
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                autoLogin();
            }
        }, 800L);

    }

    @Override
    protected void initClickListener() {

    }

    private void autoLogin() {
        mainActivity.replaceFragment(R.id.activity_main_container, new MainFragment());
    }
}
