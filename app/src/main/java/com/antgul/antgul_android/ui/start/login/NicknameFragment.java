package com.antgul.antgul_android.ui.start.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainFragment;
import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentNicknameBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.User;
import com.antgul.antgul_android.util.TimeStamp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.antgul.antgul_android.base.ApplicationClass.REGEX_NICK;
import static com.antgul.antgul_android.base.ApplicationClass.USERS_COLLECTION;

public class NicknameFragment extends BaseFragment<FragmentNicknameBinding> {
    private String UID;

    @Override
    protected FragmentNicknameBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentNicknameBinding.inflate(inflater, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UID = getArguments().getString("UID");
        }
        if (UID != null) {
            db.collection(USERS_COLLECTION).document(currentUser.getUid())
                    .get()
                    .addOnCompleteListener(task -> {
                        Log.i(TAG, "onComplete");
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            User user = document.toObject(User.class);
                            if (user != null) {
                                if (user.getNickname() != null) {
                                    replaceFragmentWithBottomNav(new MainFragment());
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        progressDialog.hideProgress();
                    });
        }
    }

    @Override
    protected void initView() {
        showToast(UID + "");
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
        TimeStamp timeStamp = new TimeStamp();
        String time = timeStamp.getTime();
        User user = new User();
        user.setNickname(nickname);
        user.setCreateAt(time);
        DocumentReference usersReference = db.collection(USERS_COLLECTION).document(currentUser.getUid());
        usersReference
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        showToast("구글 회원가입 성공");
                        mainActivity.replaceFragment(R.id.activity_main_container, new MainFragment());
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

    private void sendToDB(FirebaseUser firebaseUser, String nickname) {
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
