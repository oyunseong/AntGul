package com.antgul.antgul_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.base.BaseActivity;
import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.antgul.antgul_android.ui.board.BoardFragment;
import com.antgul.antgul_android.ui.board.DetailBoardFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private final int FRAGMENT_HOME = 0;
    private final int FRAGMENT_BOARD = 1;
    private final int FRAGMENT_NOTION = 2;
    private final int FRAGMENT_MY_PAGE = 3;
    private final int FRAGMENT_LIKE_STOCK = 4;
    public final int FRAGMENT_DETAIL_BOARD = 5;

    private HomeFragment homeFragment;
    private BoardFragment boardFragment;
    private NotionFragment notionFragment;
    private MyPageFragment myPageFragment;
    private LikeStockFragment likeStockFragment;
    private DetailBoardFragment detailBoardFragment;

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
                        callFragment(FRAGMENT_BOARD);
                        break;
                    case R.id.menu_notion:
                        callFragment(FRAGMENT_NOTION);
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
        likeStockFragment = new LikeStockFragment();
        detailBoardFragment = new DetailBoardFragment();

        callFragment(FRAGMENT_HOME);
    }


    // 프래그먼트 호출 메서드입니다.
    public void callFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (index) {
            case FRAGMENT_HOME:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), homeFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_BOARD:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), boardFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_NOTION:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), notionFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_MY_PAGE:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), myPageFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_LIKE_STOCK:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), likeStockFragment);
                fragmentTransaction.commit();
                break;
            case FRAGMENT_DETAIL_BOARD:
                fragmentTransaction.replace(binding.fragmentFrame.getId(), detailBoardFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}