package com.antgul.antgul_android.ui.join;

import static com.antgul.antgul_android.util.ViewUtil.getEditTextValue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 로그인 버튼 클릭
        onClickLogInButton();
        // 회원가입 버튼 클릭
        onClickSignUpButton();
    }

    private void onClickLogInButton(){
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithEmailAndPassword(getEditTextValue(binding.etId), getEditTextValue(binding.etPw));
            }
        });
    }

    private void onClickSignUpButton(){
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        //show progress
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("onComplete", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //startNextActivity(MainActivity.class);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("onComplete", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                        //hideProgress()
                    }
                });
    }
}

