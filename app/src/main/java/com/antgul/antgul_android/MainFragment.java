package com.antgul.antgul_android;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentMainBinding;
import com.antgul.antgul_android.ui.community.BoardFragment;
import com.antgul.antgul_android.ui.community.TestFragment;
import com.antgul.antgul_android.ui.home.HomeFragment;
import com.antgul.antgul_android.ui.mypage.MyPageFragment;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends BaseFragment<FragmentMainBinding> {
    private HomeFragment homeFragment;
    private BoardFragment boardFragment;
    private NotionFragment notionFragment;
    private MyPageFragment myPageFragment;

    @Override
    protected FragmentMainBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentMainBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        homeFragment = new HomeFragment();
        boardFragment = new BoardFragment();
        notionFragment = new NotionFragment();
        myPageFragment = new MyPageFragment();
    }

    @Override
    protected void initClickListener() {
        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.menu_home:
                        mainActivity.callFragment(homeFragment);
                        break;
                        // 임시
                    case R.id.menu_valueation:
                        mainActivity.callFragment(notionFragment);
                        break;
                        //TODO 네이밍 변경필요
                    case R.id.menu_community:
                        mainActivity.callFragment(boardFragment);
                        break;
                    case R.id.menu_my_page:
                        mainActivity.callFragment(myPageFragment);
                        break;
                }
                return true;
            }
        });
    }

}
