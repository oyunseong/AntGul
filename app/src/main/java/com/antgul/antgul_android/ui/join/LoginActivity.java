package com.antgul.antgul_android.ui.join;

import static com.antgul.antgul_android.util.PreferenceManager.PREF_AUTO_LOGIN;
import static com.antgul.antgul_android.util.PreferenceManager.PREF_SAVE_EMAIL;
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
        setTheme(R.style.Theme_Antden);
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        autoLogin();
    }

    @Override
    protected void initView() {
        boolean isAutoLoginButton = PreferenceManager.getBoolean(getApplicationContext(), PREF_AUTO_LOGIN);
        binding.autoLoginCheckBox.setChecked(isAutoLoginButton);

        String isSaveEmail = PreferenceManager.getString(getApplicationContext(),PREF_SAVE_EMAIL);
        binding.etId.setText(isSaveEmail);
    }

    @Override
    protected void initClickListener() {
        binding.loginButton.setOnClickListener(v -> {
            String id = getEditTextValue(binding.etId);
            String pw = getEditTextValue(binding.etPw);
            if (id.equals("") || pw.equals("")) {
                showToast("email,pw를 입력해주세요.");
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
        boolean isAutoLoginButton = PreferenceManager.getBoolean(getApplicationContext(), PREF_AUTO_LOGIN);
        if (currentUser != null && isAutoLoginButton) {
            startNextActivity(MainActivity.class);
            finish();
        }
    }

    private void saveEmail(String email) {
        PreferenceManager.setString(getApplicationContext(),PREF_SAVE_EMAIL,email);
        binding.etId.setText(email);
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
                            saveEmail(email);
                            startNextActivity(MainActivity.class);
                            finish();
                        } else {
                            showToast("틀림");
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("onComplete", "signInWithEmail:failure", task.getException());
                        showToast("네트워크 불안정");
//                      updateUI(null);
                    }
                });
    }

}

