package com.antgul.antgul_android.ui.start;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentSplashBinding;
import com.antgul.antgul_android.util.PreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import static com.antgul.antgul_android.util.PreferenceManager.PREF_AUTO_LOGIN;

public class SplashFragment extends BaseFragment<FragmentSplashBinding> {

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected FragmentSplashBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSplashBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
//        mAuth = FirebaseAuth.getInstance();
        autoLogin();
    }

    @Override
    protected void initClickListener() {
    }


    private void autoLogin() {
        boolean isAutoLoginButton = PreferenceManager.getBoolean(getActivity(), PREF_AUTO_LOGIN);
        if (currentUser != null && isAutoLoginButton) {

        }
    }
}
