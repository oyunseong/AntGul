package com.antgul.antgul_android.ui.mypage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentMyPageBinding;
import com.antgul.antgul_android.ui.start.login.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

public class ProfileTabFragment extends BaseFragment<FragmentMyPageBinding> {
    @Override
    protected FragmentMyPageBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMyPageBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        binding.myPageAppbar.settingButton.setVisibility(View.VISIBLE);
        binding.myPageAppbar.bellButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initClickListener() {
        binding.myPageAppbar.settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.preferencesAddFragment(new PreferencesFragment());
            }
        });
//        onClickLogoutButton();
//        onClickRevokeButton();
//        onClickWithdrawalButton();
    }


//    private void onClickLogoutButton() {
//        binding.myPageLogoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("onClickLogoutButton", "logout button click");
//                showToast("로그아웃 버튼 클릭");
//                if (currentUser != null) {
//                    mAuth.signOut();
//                    mainActivity.replaceFragment(new LoginFragment());
//                } else {
//                    showToast("로그인되어있지 않습니다.");
//                }
//            }
//        });
//    }


}
