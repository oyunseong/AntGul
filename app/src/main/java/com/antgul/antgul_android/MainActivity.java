package com.antgul.antgul_android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.ui.start.SplashFragment;
import com.antgul.antgul_android.util.BackPressHandler;

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
        replaceFragment(R.id.main_activity_frame, new SplashFragment());
        getHashKey();
    }


    // writeFragment 에서 사용 중
    public void replaceDetailFragment(Fragment fragment) {
        Log.i(TAG, "replaceFragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_activity_detail_frame, fragment)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_in)
                .addToBackStack(null)
                .commit();
        binding.mainActivityDetailFrame.setVisibility(View.VISIBLE);
        binding.mainActivityFrame.setVisibility(View.GONE);
    }

    public void visibleMainFrame() {
        binding.mainActivityDetailFrame.setVisibility(View.GONE);
        binding.mainActivityFrame.setVisibility(View.VISIBLE);
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