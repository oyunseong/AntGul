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
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

//        autoLogin();
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


        binding.signUpButton.setOnClickListener(v -> {
            //Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            //startActivity(intent);
        });




    }





}

