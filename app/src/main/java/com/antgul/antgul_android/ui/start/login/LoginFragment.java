package com.antgul.antgul_android.ui.start.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.MainFragment;
import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentLoginBinding;
import com.antgul.antgul_android.ui.start.signup.SignUpFragment;
import com.antgul.antgul_android.util.PreferenceManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

import static com.antgul.antgul_android.util.PreferenceManager.PREF_AUTO_LOGIN;
import static com.antgul.antgul_android.util.PreferenceManager.PREF_SAVE_EMAIL;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {
    private GoogleSignInClient googleSignClient;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected FragmentLoginBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignClient = GoogleSignIn.getClient(requireActivity(), gso);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {
        boolean isAutoLoginButton = PreferenceManager.getBoolean(getActivity(), PREF_AUTO_LOGIN);
        binding.autoLoginCheckBox.setChecked(isAutoLoginButton);
        binding.autoLoginCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            PreferenceManager.setBoolean(getActivity(), PREF_AUTO_LOGIN, isChecked);
        });
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager =requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);
                transaction.replace(R.id.activity_main_container, new SignUpFragment());
                transaction.addToBackStack("LoginFragment");
                transaction.commit();
            }
        });

        onClickLoginButton();
        onClickSignInGoogleButton();
    }

    private void onClickSignInGoogleButton() {
        binding.googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void onClickLoginButton() {
        binding.loginButton.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPw.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                showToast("아이디 또는 비밀번호를 입력해주세요");
            } else {
                signInWithEmailAndPassword(email, password);
            }
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        progressDialog.showProgress();
        mAuth.signInWithEmailAndPassword(email, password) //request to firebase server
                .addOnCompleteListener(mainActivity, task -> {
                    progressDialog.hideProgress();
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveEmail(email);
                            showToast("로그인 성공!");
                            replaceFragmentWithBottomNav(new MainFragment());
                        } else {
                            Log.e(TAG, "user is null");
                            showToast("user is null");
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        showToast("회원정보를 찾을 수 없습니다.");
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "++onActivityResult");
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        progressDialog.showProgress();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    progressDialog.hideProgress();
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithGoogleEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // TODO 닉네임이 없는 회원이라면 닉네임 입력창으로 이동시키고 스토어에 저장하기
//                            db.collection(USERS_COLLECTION).document(currentUser.getUid())
//                                    .get()
//                                    .addOnCompleteListener(task ->{
//
//                                    })
                            showToast("구글 로그인 성공");
                            replaceFragmentWithBottomNav(new MainFragment());
                        }else{
                            Log.e(TAG, "user is null");
                            showToast("user is null");
                        }
                    } else {
                        Log.w(TAG, "signInWithGoogleEmail:failure" + task.getException());
                        showToast("구글 회원정보를 찾을 수 없습니다.");
                    }
                });
    }

    private void autoLogin() {
//        boolean isAutoLoginButton = PreferenceManager.getBoolean(getActivity(), PREF_AUTO_LOGIN);
        if (currentUser != null) {
//            mainActivity.replaceFragment(new MainFragment());
        }
    }

    private void saveEmail(String email) {
        PreferenceManager.setString(getActivity(), PREF_SAVE_EMAIL, email);
    }

}
