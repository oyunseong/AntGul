package com.antgul.antgul_android.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.ui.community.post.PostWriteFragment;
import com.google.firebase.auth.FirebaseAuth;

/**
 * 모든 액티비티는 해당 클래스를 상속받도록 수정하기.
 */
public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected VB binding;
    protected FirebaseAuth mAuth;
    protected abstract VB getViewBinding();
    protected abstract void initView();
    protected abstract void initClickListener();
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "++onCreate");
        binding = getViewBinding();
        mAuth = FirebaseAuth.getInstance();
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(this);
        initView();
        initClickListener();
    }

    public void replaceFragment(Fragment fragment){
        Log.i(TAG,TAG + "++ replace -> " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                .replace(R.id.activity_main_container, fragment)
                .commit();
    }
    /**
     * replace : remove()
     *+ add()
     */
    public void replaceFragment(@IdRes int fragmentContainerId, Fragment fragment){
        Log.i(TAG,"++" + TAG + " replace -> " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                .replace(fragmentContainerId, fragment)
                .commit();
    }

    public void replaceFragmentAddToBackStack(Fragment fragment){
        Log.i(TAG,TAG + " replace -> " + fragment.getTag());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                .replace(R.id.activity_main_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragmentAddToBackStack(@IdRes int fragmentContainerId,Fragment fragment){
        Log.i(TAG,TAG + " replace -> " + fragment.getTag());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                .replace(fragmentContainerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    //mainActivity 내에 있는 컨테이너
    public void addFragment(@IdRes int fragmentContainerId, Fragment fragment){
        Log.i(TAG,"++" + TAG + " add -> " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                .add(fragmentContainerId, fragment)
                .addToBackStack(null)
                .commit();
    }
    public void addFragmentAddToBackStack(Fragment fragment){
        Log.i(TAG,"++" + TAG + " add -> " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out)
                .add(R.id.activity_main_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void startNextActivity(Class<?> className) {
        Log.i(TAG,"startNextActivity");
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"++onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"++onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"++onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "++onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "++onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"++onDestroy");
        binding = null;
    }
}
