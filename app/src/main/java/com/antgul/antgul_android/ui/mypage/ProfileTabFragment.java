package com.antgul.antgul_android.ui.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentProfileTabBinding;

public class ProfileTabFragment extends BaseFragment<FragmentProfileTabBinding> {
    @Override
    protected FragmentProfileTabBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProfileTabBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        binding.myPageAppbar.appbarSettingButton.setVisibility(View.VISIBLE);
        binding.myPageAppbar.appbarBellButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initClickListener() {
        binding.myPageAppbar.appbarSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mainActivity.replaceFragmentAddToBackStack(new PreferencesFragment());
                mainActivity.addFragmentAddToBackStack(new PreferencesFragment());
//                mainActivity.replaceFragmentAddToBackStack(new PreferencesFragment());
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
