package com.antgul.antgul_android.ui.start.signup;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainFragment;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentSignUpBinding;
import com.antgul.antgul_android.model.User;
import com.antgul.antgul_android.util.TimeStamp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.antgul.antgul_android.base.ApplicationClass.REGEX_NICK;
import static com.antgul.antgul_android.base.ApplicationClass.USERS_COLLECTION;

public class SignUpFragment extends BaseFragment<FragmentSignUpBinding> {

    @Override
    protected FragmentSignUpBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSignUpBinding.inflate(inflater, container, false);
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
        String regexNick = REGEX_NICK;//                                                            // 한글/영문/숫자 포함 2~12

        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternNick = Pattern.compile(regexNick);
        Pattern patternPw = Pattern.compile(regexPw);
        Matcher matcherEmail = patternEmail.matcher(inputEmail);
        Matcher matcherNick = patternNick.matcher(inputNick);
        Matcher matcherPw = patternPw.matcher(inputPassword);


        if (matcherEmail.matches()
                && matcherPw.matches()
                && matcherNick.matches()
                && inputPassword.equals(inputPasswordConfirm)
                && checkBox) {
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

    private void sendUserDataToDB(String email, String password, String nickname) {
        progressDialog.showProgress();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mainActivity, task -> {
                    if (task.isSuccessful()) {
                        Log.d("createUser", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            postUserInfo(user, nickname, password);
                        }
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

    private void postUserInfo(FirebaseUser firebaseUser, String nickname, String password) {
        TimeStamp timeStamp = new TimeStamp();
        String time = timeStamp.getTime();
        User user = new User();
        user.setUid(firebaseUser.getUid());
        user.setEmail(firebaseUser.getEmail());
        user.setPassword(password);
        user.setNickname(nickname);
        user.setCreateAt(time);

        DocumentReference usersReference = db.collection(USERS_COLLECTION).document(firebaseUser.getUid());
        usersReference
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        showToast("반갑습니다. " + nickname + "님");
                        replaceFragment(new MainFragment());
                        progressDialog.hideProgress();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        showToast("회원가입 실패");
                        progressDialog.hideProgress();
                    }
                });
    }
}
