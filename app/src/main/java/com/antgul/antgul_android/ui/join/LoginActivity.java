package com.antgul.antgul_android.ui.join;

import static com.antgul.antgul_android.util.PreferenceManager.PREF_AUTO_LOGIN;
import static com.antgul.antgul_android.util.ViewUtil.getEditTextValue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityLoginBinding;
import com.antgul.antgul_android.util.PreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    public ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        autoLogin();
    }

    protected void initView() {
        boolean isAutoLogin = PreferenceManager.getBoolean(getApplicationContext(), PREF_AUTO_LOGIN);
        binding.autoLoginCheckBox.setChecked(isAutoLogin);

        //TODO 로그인에 한번이라도 성공한 적이 있다면, 이메일 비밀번호 자동 입력 시켜주기.
    }

    @Override
    protected void initClickListener() {
        binding.loginButton.setOnClickListener(v -> {
            String id = getEditTextValue(binding.etId);
            String pw = getEditTextValue(binding.etPw);
            if (id.equals("") || pw.equals("")) {
                //아이디 비밀번호 확인해주세요
            } else {
                signInWithEmailAndPassword(id, pw);
            }
        });

        binding.signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        binding.autoLoginCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            PreferenceManager.setBoolean(getApplicationContext(), PREF_AUTO_LOGIN, isChecked);
        });
    }

    private void autoLogin() {
        boolean isAutoLogin = PreferenceManager.getBoolean(getApplicationContext(), PREF_AUTO_LOGIN);
        if (currentUser != null && isAutoLogin) {
            startNextActivity(MainActivity.class);
            finish();
        }
    }

    private void saveEmail() {
        //TODO 로그인 성공 시, SharedPreferences 에 이메일, 비밀번호 저장
    }

    private void signInWithEmailAndPassword(String email, String password) {
        progressDialog.showProgress();

        mAuth.signInWithEmailAndPassword(email, password) //request to firebase server
                .addOnCompleteListener(this, task -> {
                    progressDialog.hideProgress();
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("onComplete", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveEmail();
                            startNextActivity(MainActivity.class);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Email,Pw 불일치ddddddddd", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("onComplete", "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호", Toast.LENGTH_SHORT).show();
//                      updateUI(null);
                    }
                });
    }

}

