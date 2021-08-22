package com.antgul.antgul_android;

import android.os.Bundle;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.ui.start.SplashFragment;
import com.antgul.antgul_android.util.BackPressHandler;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private BackPressHandler backPressHandler;

    @Override
    public ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backPressHandler = new BackPressHandler(this);
    }

    protected void initView() {
        replaceFragment(R.id.activity_main_container, new SplashFragment());
    }

    @Override
    protected void initClickListener() {

    }


//    // writeFragment 에서 사용 중
//    public void replaceDetailFragment(Fragment fragment) {
//        Log.i(TAG, "replaceFragment");
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.main_activity_detail_frame, fragment)
//                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_in)
//                .addToBackStack(null)
//                .commit();
//        binding.mainActivityDetailFrame.setVisibility(View.VISIBLE);
//        binding.mainActivityFrame.setVisibility(View.GONE);
//    }
//
//    public void visibleMainFrame() {
//        binding.mainActivityDetailFrame.setVisibility(View.GONE);
//        binding.mainActivityFrame.setVisibility(View.VISIBLE);
//    }


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