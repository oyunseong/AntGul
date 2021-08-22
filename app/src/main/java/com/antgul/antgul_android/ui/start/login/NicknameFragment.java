package com.antgul.antgul_android.ui.start.login;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainFragment;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentNicknameBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.antgul.antgul_android.base.ApplicationClass.REGEX_NICK;
import static com.antgul.antgul_android.base.ApplicationClass.USERS_COLLECTION;

public class NicknameFragment extends BaseFragment<FragmentNicknameBinding> {
    @Override
    protected FragmentNicknameBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentNicknameBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {
        binding.okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = binding.etNickname.getText().toString();
                String regexNick = REGEX_NICK;//
                Pattern patternNick = Pattern.compile(regexNick);
                Matcher matcherNick = patternNick.matcher(nickname);

                if (matcherNick.matches()) {
                    saveNickname(nickname);
                } else {
                    showToast("이메일 형식에 어긋납니다.");
                }
            }
        });
    }

    private void saveNickname(String nickname) {
        progressDialog.showProgress();
        mAuth.createUserWithEmailAndPassword(currentUser.getUid(), null)
                .addOnCompleteListener(mainActivity, task -> {
                    if (task.isSuccessful()) {
                        if (currentUser != null) {
                            sendToDB(currentUser,nickname);
                        }
                    }
                });
    }
    private void sendToDB(FirebaseUser firebaseUser,String nickname)
    {
        DocumentReference usersReference = db.collection(USERS_COLLECTION).document(firebaseUser.getUid());
        usersReference
                .set(firebaseUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        showToast("반갑습니다. " + nickname + "님");
                        replaceFragmentWithBottomNav(new MainFragment());
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
