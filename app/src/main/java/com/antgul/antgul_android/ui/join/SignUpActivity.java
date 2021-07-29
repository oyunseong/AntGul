package com.antgul.antgul_android.ui.join;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> {

    ActivitySignUpBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected ActivitySignUpBinding getViewBinding() {
        return ActivitySignUpBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        initButtonClickListener();
        binding.etConfirmPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.etPw.getText().toString().equals(binding.etConfirmPw.getText().toString())) {

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initButtonClickListener() {
        binding.signUpConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCreateUser();
            }
        });
    }

    private void validateCreateUser() {
        String inputId = binding.etId.getText().toString();
        String inputPassword = binding.etPw.getText().toString();


        //이메일 형식 검증 추가 하기. 정규표현식
        if (inputPassword.equals(binding.etConfirmPw.getText().toString())) {
            createUser(inputId, inputPassword); //호출
            showToast("비밀번호가 같지 않습니다");
            showToast("비밀번호가 같지 않습니다");
            showToast("비밀번호가 같지 않습니다");
            showToast("비밀번호가 같지 않습니다");
        } else {
            showToast("비밀번호가 같지 않습니다");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //액티비티 전환
        }
    }

    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //파이어 스토어에 데이터 추가.
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            //회원가입 성공 결과 토스트 메시지
            finish();
        } else {
            binding.etId.getText().clear();
        }
    }
}
