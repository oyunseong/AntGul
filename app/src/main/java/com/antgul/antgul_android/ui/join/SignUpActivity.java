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
        String inputPasswordConfirm = binding.etConfirmPw.getText().toString();
        String inputNick = binding.etNickName.getText().toString();

        String regexEmail = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String regexPw = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$";                    // 숫자/영문/특수문자를 최소 1개를 포함하고 공백은 허용되지 않음 8~16글자
        String regexNick = "^[가-힣a-zA-Z0-9]{2,12}$";                                               // 한글/영문/숫자 포함 2~12

        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternNick = Pattern.compile(regexNick);
        Pattern patternPw = Pattern.compile(regexPw);

        Matcher matcherEmail = patternEmail.matcher(inputEmail);
        Matcher matcherNick = patternNick.matcher(inputNick);
        Matcher matcherPw = patternPw.matcher(inputPassword);


        //TODO 회원가입 정규식 알고리즘 수정

        if (matcherEmail.matches() && matcherPw.matches() && matcherNick.matches() && inputPassword.equals(inputPasswordConfirm)) {
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            createUser(inputEmail, inputPassword, inputNick);
            finish();
        } else if (inputEmail.equals("")) {
            showToast("이메일을 입력해주세요");
        } else if (!matcherEmail.matches()) {
            showToast("이메일 형식에 어긋납니다");
        } else if (inputNick.equals("")) {
            showToast("닉네임을 입력해주세요");
        } else if (!matcherNick.matches()) {
            showToast("닉네임 형식에 어긋납니다");
        } else if (inputPassword.equals("")) {
            showToast("비밀번호를 입력해주세요");
        } else if (!matcherPw.matches()) {
            showToast("비밀번호 형식에 어긋납니다");
        } else if (inputPasswordConfirm.equals("")) {
            showToast("비밀번호 확인을 입력해주세요");
        } else if (!inputPassword.equals(inputPasswordConfirm)) {
            showToast("비밀번호가 일치하지 않습니다.");
        }
    }

    //TODO 프로그레스바 추가
    private void createUser(String email, String password, String nickName) {
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
            //회원가입 성공 결과 토스트 메시지.
            Toast.makeText(SignUpActivity.this, "updateUI", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class)); //TODO 로그인 화면에 이메일 넣어주기. Intent 로 통신 가능. 검색어 : 액티비티간 데이터 전달(통신)

            // 회원가입 실패했을 때 ( 1. 네트워크 연결 실패 시 발생 )
        } else {
            Toast.makeText(this, "updateUI Error", Toast.LENGTH_SHORT).show();
        }
    }

}
