package com.antgul.antgul_android.ui.start;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainFragment;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentSplashBinding;
import com.antgul.antgul_android.util.PreferenceManager;

import org.jetbrains.annotations.NotNull;

import static com.antgul.antgul_android.util.PreferenceManager.PREF_AUTO_LOGIN;

public class SplashFragment extends BaseFragment<FragmentSplashBinding> {
    MainFragment mainFragment;
    StartFragment startFragment;

    @Override
    protected FragmentSplashBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSplashBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        mainFragment = new MainFragment();
        startFragment = new StartFragment();

        /**
         * 1초 간 지연 후, run() 콜백 실행됨.
         * 일정 시간동안 타이머를 돌리고, 시간이 되면, run()이 호출됨.
         */
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                autoLogin();
            }
        }, 1000L);

    }

    @Override
    protected void initClickListener() {

    }


    private void autoLogin() {
        boolean isAutoLoginButton = PreferenceManager.getBoolean(getActivity(), PREF_AUTO_LOGIN);
//        mainActivity.callFragmentWithoutBackStack(mainFragment);
        mainActivity.callFragmentWithoutBackStack(startFragment);
//        if (currentUser != null && isAutoLoginButton) {
//            mainActivity.callFragmentWithBackStack(mainFragment);
//        } else {
//            mainActivity.callFragmentWithBackStack(startFragment);
//        }
    }
}
