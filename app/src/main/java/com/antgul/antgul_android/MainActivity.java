package com.antgul.antgul_android;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.model.User;
import com.antgul.antgul_android.ui.start.SplashFragment;
import com.antgul.antgul_android.util.BackPressHandler;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private BackPressHandler backPressHandler;

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initClickListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backPressHandler = new BackPressHandler(this);
        callFragmentWithoutBackStack(binding.fragmentFrame.getId(), new SplashFragment());
        getHashKey();
    }

    public int getFrameId() {
        Log.i(TAG, "++getFrameId");
        return binding.fragmentFrame.getId();
    }

    // TODO builder pattern 체크. java default parameter
    public void callFragmentWithBackStack(int id, Fragment fragment) {        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void callFragmentWithoutBackStack(int id, Fragment fragment) {        //}, @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(id, fragment).commit();
    }

    public void replaceDetailFragment(Fragment fragment) {
        Log.i(TAG, "replaceFragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_detail_frame, fragment)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_in)
                .addToBackStack(null)
                .commit();
        binding.fragmentDetailFrame.setVisibility(View.VISIBLE);
        binding.fragmentFrame.setVisibility(View.GONE);
    }

    public void visibleMainFrame() {
        binding.fragmentDetailFrame.setVisibility(View.GONE);
        binding.fragmentFrame.setVisibility(View.VISIBLE);
    }

    private void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.antgul.antgul_android", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i(TAG, "--key_hash=" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    //    @Override
//    public void onBackPressed() {
//        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료");
//    }


    /* @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment parentFragment = fragmentManager.findFragmentByTag(TAG_PARENT);
        if (parentFragment != null && parentFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
            parentFragment.getChildFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }*/
}