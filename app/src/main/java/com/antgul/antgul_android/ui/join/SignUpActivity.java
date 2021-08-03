package com.antgul.antgul_android.ui.join;


import android.content.Intent;
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

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityLoginBinding;
import com.antgul.antgul_android.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO BaseActivity 적용
public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> {
    private FirebaseAuth mAuth;

    @Override
    protected ActivitySignUpBinding getViewBinding() {
        return ActivitySignUpBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        initButtonClickListener();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {

    }

    private void initButtonClickListener() {
        binding.signUpConfirmButton.setOnClickListener(v -> validateCreateUser());
    }

    //TODO 유효성 검증 제대로 해주기
    private void validateCreateUser() {
        String inputEmail = binding.etId.getText().toString();
        String inputPassword = binding.etPw.getText().toString();

        // 이메일형식 체크, 비밀번호 8~20자리 + 특수문자 포함
        String regexEmail = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String regexPw = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,20}$";
        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternPw = Pattern.compile(regexPw);

        Matcher matcherId = patternEmail.matcher(inputEmail);
        Matcher matcherPw = patternPw.matcher(inputPassword);

        // email,pw == true
        if (matcherId.matches() && matcherPw.matches()) {
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show();
            createUser(inputEmail, inputPassword);
            finish();
            // email == true, pw == false
        } else if (matcherId.matches() && !matcherPw.matches()) {
            Toast.makeText(this, "비밀번호 형식이 다릅니다.", Toast.LENGTH_SHORT).show();
            binding.etPw.getText().clear();
            binding.etConfirmPw.getText().clear();
            // email == false , pw == true
        } else if (!matcherId.matches() && matcherPw.matches()) {
            Toast.makeText(this, "이메일 형식이 다릅니다.", Toast.LENGTH_SHORT).show();
            binding.etId.getText().clear();
            // email, pw  == false
        } else {
            Toast.makeText(this, "이메일, 비밀번호 형식이 다릅니다.", Toast.LENGTH_SHORT).show();
            binding.etId.getText().clear();
            binding.etPw.getText().clear();
            binding.etConfirmPw.getText().clear();
        }
    }

    //TODO 프로그레스바 추가
    private void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("createUser", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //파이어 스토어에 데이터 추가.
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("createUser", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            //회원가입 성공 결과 토스트 메시지
            Toast.makeText(SignUpActivity.this, "updateUI", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        } else {
            Toast.makeText(this, "updateUI Error", Toast.LENGTH_SHORT).show();
        }
    }

}
