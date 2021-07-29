package com.antgul.antgul_android.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.antgul.antgul_android.MainActivity;
import com.antgul.antgul_android.R;

/**
 * 모든 액티비티는 해당 클래스를 상속받도록 수정하기.
 */
public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    protected VB binding;
    protected abstract VB getViewBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "++onCreate"); //이런 식으로 모든 생명 주기에, 태그 달기

        binding = getViewBinding();
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void startNextActivity(Class<?> className) {
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
