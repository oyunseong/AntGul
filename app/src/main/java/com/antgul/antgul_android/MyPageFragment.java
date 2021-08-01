package com.antgul.antgul_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentMyPageBinding;
import com.antgul.antgul_android.databinding.FragmentNotionBinding;
import com.antgul.antgul_android.ui.join.LoginActivity;
import com.antgul.antgul_android.ui.join.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class MyPageFragment extends BaseFragment<FragmentMyPageBinding> {

    private FirebaseAuth mAuth;


    @Override
    protected FragmentMyPageBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMyPageBinding.inflate(inflater, container, false);
    }


    @Override
    protected void initView() {
        mAuth = FirebaseAuth.getInstance();
        // 로그아웃 버튼 클릭 시 호출됩니다.
        onClickLogoutButton();

        onClickWithdrawalButton();
    }

    @Override
    protected void initClickListener() {

    }

    // 로그아웃 버튼
    private void onClickLogoutButton()
    {
        binding.myPageLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClickLogoutButton","logout button click");
                showToast("로그아웃 버튼 클릭");
                mAuth.signOut();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
    // 회원탈퇴 버튼
    private void onClickWithdrawalButton(){
        binding.myPageWithdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("회원탈퇴 버튼 클릭");
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(getContext());
                alert_confirm.setMessage("계정을 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                showToast("계정이 삭제 되었습니다.");
                                startActivity(new Intent(getContext(), MainActivity.class));
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
