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

public class MyPageFragment extends BaseFragment<FragmentMyPageBinding> {
    @Override
    protected FragmentMyPageBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMyPageBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initClickListener() {
        onClickLogoutButton();
        onClickRevokeButton();
//        onClickWithdrawalButton();
    }

    private void onClickRevokeButton() {
        binding.myPageWithdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser != null) {
                    currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            showToast("회원탈퇴 되었습니다.");
                        }
                    });
                } else {
                    showToast("회원을 찾을 수 없습니다.");
                }
            }
        });

    }

    private void onClickLogoutButton() {
        binding.myPageLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClickLogoutButton", "logout button click");
                showToast("로그아웃 버튼 클릭");
                if (currentUser != null) {
                    mAuth.signOut();
                    mainActivity.replaceFragment(R.id.main_activity_frame, new LoginFragment());
                } else {
                    showToast("로그인되어있지 않습니다.");
                }
            }
        });
    }

    private void onClickWithdrawalButton() {
        binding.myPageWithdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("회원탈퇴 버튼 클릭");
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                alert_confirm.setMessage("계정을 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                showToast("계정이 삭제 되었습니다.");
//                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                        });
                        alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast("취소되었습니다.");
                            }
                        });
                        alert_confirm.show();
                    }
                });
            }
        });
    }
}
