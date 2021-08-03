package com.antgul.antgul_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.ui.community.BoardFragment;
import com.antgul.antgul_android.ui.home.HomeFragment;
import com.antgul.antgul_android.ui.mypage.MyPageFragment;
import com.antgul.antgul_android.util.BackPressHandler;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private final int FRAGMENT_HOME = 0;
    private final int FRAGMENT_VALUEATION = 1;
    private final int FRAGMENT_COMMUNITY = 2;
    private final int FRAGMENT_MY_PAGE = 3;

    private HomeFragment homeFragment;
    private BoardFragment boardFragment;
    private NotionFragment notionFragment;
    private MyPageFragment myPageFragment;
 

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

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        callFragment(FRAGMENT_HOME);
                        break;
                    case R.id.menu_board:
                        callFragment(FRAGMENT_VALUEATION);
                        break;
                    case R.id.menu_notion:
                        callFragment(FRAGMENT_COMMUNITY);
                        break;
                    case R.id.menu_my_page:
                        callFragment(FRAGMENT_MY_PAGE);
                        break;
                }
                return true;
            }
        });

        homeFragment = new HomeFragment();
        boardFragment = new BoardFragment();
        notionFragment = new NotionFragment();
        myPageFragment = new MyPageFragment();
        backPressHandler= new BackPressHandler(this);

        callFragment(FRAGMENT_HOME);
    }

    public void callFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (index) {
            case FRAGMENT_HOME:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), homeFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_VALUEATION:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), boardFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_COMMUNITY:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), notionFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_MY_PAGE:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), myPageFragment);
                fragmentTransaction.commit();
                break;
            
        }
    }

    @Override
    public void onBackPressed() {
        backPressHandler.onBackPressed("뒤로가기 버튼 한번 더 누르면 종료");
    }
}