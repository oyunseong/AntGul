package com.antgul.antgul_android.ui.join;

import static com.antgul.antgul_android.util.ViewUtil.getEditTextValue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private FirebaseAuth mAuth;

    @Override
    public ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        // 이미 로그인 되어있다면 현재 액티비티를 종료합니다.
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        Log.d(TAG, binding.toString());
        onClickLogInButton();
        onClickSignUpButton();
    }


    private void onClickLogInButton() {
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = getEditTextValue(binding.etId);
                String pw = getEditTextValue(binding.etPw);
                if (id.equals("") || pw.equals("")) {
                    //아이디 비밀번호 확인해주세요
                } else {
                    signInWithEmailAndPassword(id, pw);
                }
            }
        });
    }

    // 회원가입 버튼 클릭
    private void onClickSignUpButton() {
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        progressDialog.showProgress();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // 로그인 성공
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("onComplete", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Email,Pw 불일치ddddddddd", Toast.LENGTH_LONG).show();
                            }
                        } else {    // 로그인 실패
                            // If sign in fails, display a message to the user.
                            Log.w("onComplete", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                        progressDialog.hideProgress();
                    }
                });
    }
}

