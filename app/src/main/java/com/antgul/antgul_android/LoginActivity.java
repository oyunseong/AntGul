package com.antgul.antgul_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.appchild.csc.antgul.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLoginActivity(this);
        User user = new User("gkrlsanj","qlapdls2");
        binding.setUser(user);
    }

    public void loginButton(View view)
    {
        Toast.makeText(this, "클릭되었습니다.", Toast.LENGTH_LONG).show();
    }

    public void goMainActivity(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}

