package com.antgul.antgul_android.ui.join;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivitySignUpBinding;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
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
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initClickListener() {
        binding.signUpButton.setOnClickListener(v -> validateCreateUser());
    }

    private void validateCreateUser() {
        String inputEmail = binding.etId.getText().toString();
        String inputPassword = binding.etPw.getText().toString();
        String inputPasswordConfirm = binding.etConfirmPw.getText().toString();
        String inputNick = binding.etNickName.getText().toString();
        boolean checkBox = binding.termsOfServiceCheckBox.isChecked();

        String regexEmail = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String regexPw = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$";                    // 숫자/영문/특수문자를 최소 1개를 포함하고 공백은 허용되지 않음 8~16글자
        String regexNick = "^[가-힣a-zA-Z0-9]{2,12}$";                                               // 한글/영문/숫자 포함 2~12

        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternNick = Pattern.compile(regexNick);
        Pattern patternPw = Pattern.compile(regexPw);
        Matcher matcherEmail = patternEmail.matcher(inputEmail);
        Matcher matcherNick = patternNick.matcher(inputNick);
        Matcher matcherPw = patternPw.matcher(inputPassword);


        if (matcherEmail.matches() && matcherPw.matches() && matcherNick.matches() && inputPassword.equals(inputPasswordConfirm) && checkBox) {
            sendUserDataToDB(inputEmail, inputPassword, inputNick);
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
        } else if (!checkBox) {
            showToast("이용약관에 동의해주세요.");
        }
    }

    private void sendUserDataToDB(String email, String password, String nickName) {
        progressDialog.showProgress();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressDialog.hideProgress();
                    if (task.isSuccessful()) {
                        //TODO 파이어 스토어에 nickName 데이터 추가.
                        Log.d("createUser", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        createUser(user);
                    } else {
                        Log.e("createUser", "createUserWithEmail:failure " + task.getException());
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            showToast("이미 가입된 정보입니다.");
                        } else if (task.getException() instanceof FirebaseNetworkException) {
                            showToast("네트워크 연결상태를 확인해주세요");
                        } else {
                            showToast("회원가입을 실패했습니다");
                        }
                    }
                });
    }

    private void createUser(FirebaseUser user) {
        //TODO 로그인 화면에 이메일 넣어주기. Intent 로 통신 가능. 검색어 : 액티비티간 데이터 전달(통신)
        if (user != null) {
            showToast("개미굴에 오신것을 환영합니다.");
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}
