package com.antgul.antgul_android;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.antgul.antgul_android.R.id.menu_board;
import static com.antgul.antgul_android.R.id.menu_home;
import static com.antgul.antgul_android.R.id.menu_my_page;
import static com.antgul.antgul_android.R.id.menu_notion;
import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private final int FRAGMENT_HOME = 0;
    private final int FRAGMENT_BOARD = 1;
    private final int FRAGMENT_NOTION = 2;
    private final int FRAGMENT_MY_PAGE = 3;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private HomeFragment homeFragment;
    private BoardFragment boardFragment;
    private NotionFragment notionFragment;
    private MyPageFragment myPageFragment;

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 바텀 네비게이션 클릭
        binding.bottomNav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        callFragment(0);
                        break;
                    case R.id.menu_board:
                        callFragment(1);
                        break;
                    case R.id.menu_notion:
                        callFragment(2);
                        break;
                    case R.id.menu_my_page:
                        callFragment(3);
                }
                return true;
            }
        });

        homeFragment = new HomeFragment();
        boardFragment = new BoardFragment();
//        notionFragment = new NotionFragment();
        myPageFragment = new MyPageFragment();

        // 첫 화면 fragment_home.xml을 호출합니다.
        callFragment(FRAGMENT_HOME);
    }


    // 프래그먼트 호출 메서드입니다.
    public void callFragment(int index) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (index) {
            case 0: // 홈
                fragmentTransaction.replace(binding.fragmentFrame.getId(), homeFragment);
                fragmentTransaction.commit();
                break;
            case 1: // 게시판
                fragmentTransaction.replace(binding.fragmentFrame.getId(), boardFragment);
                fragmentTransaction.commit();
                break;
            case 2: // 알림
                replaceFragment(new NotionFragment());
                break;
            case 3: // 마이페이지
                fragmentTransaction.replace(binding.fragmentFrame.getId(), myPageFragment);
                fragmentTransaction.commit();
                break;

        }
    }
}