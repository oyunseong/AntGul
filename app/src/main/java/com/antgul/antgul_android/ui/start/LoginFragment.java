package com.antgul.antgul_android.ui.start;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentLoginBinding;
import com.antgul.antgul_android.util.PreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

import static com.antgul.antgul_android.util.PreferenceManager.PREF_AUTO_LOGIN;
import static com.antgul.antgul_android.util.PreferenceManager.PREF_SAVE_EMAIL;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {
    @Override
    protected FragmentLoginBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
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
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.showProgress();
            }
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        progressDialog.showProgress();

        mAuth.signInWithEmailAndPassword(email, password) //request to firebase server
                .addOnCompleteListener((Executor) this, task -> {
                    progressDialog.hideProgress();
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("onComplete", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveEmail(email);
                            showToast("로그인 성공!!");
                            // TODO mainFragment로 이동
//                            startNextActivity(MainActivity.class);
//                            finish();
                        } else {
                            Log.e(TAG, "user is null");
                            showToast("user is null");
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("onComplete", "signInWithEmail:failure", task.getException());
                        showToast("회원정보를 찾을 수 없습니다.");
//                      updateUI(null);
                    }
                });
    }

    private void autoLogin() {
        boolean isAutoLoginButton = PreferenceManager.getBoolean(getActivity(), PREF_AUTO_LOGIN);
        if (currentUser != null && isAutoLoginButton) {
            // TODO  MainFragment로 이동
        }
    }

    private void saveEmail(String email) {
        PreferenceManager.setString(getActivity(), PREF_SAVE_EMAIL, email);
        //binding.etId.setText(email);
    }
}
