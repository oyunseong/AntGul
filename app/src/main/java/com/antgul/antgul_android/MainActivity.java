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

import com.antgul.antgul_android.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.antgul.antgul_android.R.id.menu_board;
import static com.antgul.antgul_android.R.id.menu_home;
import static com.antgul.antgul_android.R.id.menu_my_page;
import static com.antgul.antgul_android.R.id.menu_notion;
import static com.google.android.material.bottomnavigation.BottomNavigationView.*;

public class MainActivity extends AppCompatActivity {

    private final int MAIN_ACTIVITY = 0;
    private final int FRAGMENT_HOME = 1;
    private final int FRAGMENT_BOARD = 2;

    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeActivity homeActivity;
    private BoardActivity boardActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.bottomNav.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.menu_home:
                        callFragment(0);
                        break;
                    case menu_board:
                        callFragment(1);
                        break;
                    case menu_notion:
                        callFragment(2);
                        break;
                    case menu_my_page:
                        callFragment(3);
                }
                return true;
            }
        });

        homeActivity = new HomeActivity();
        boardActivity = new BoardActivity();

        // 액티비티 호출 시점에 있는 어느 플래그먼트를 프레임레이아웃에 올릴건지 정함
        callFragment(FRAGMENT_HOME);
    }


    public void callFragment(int index) {
        // 프래그먼트 사용을 위해
        fragmentManager= getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (index) {
            // HomeActivity 프래그먼트 호출
            case 0:
                fragmentTransaction.replace(binding.fragmentFrame.getId(),homeActivity);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(binding.fragmentFrame.getId(),boardActivity);
                fragmentTransaction.commit();
                break;
        }
    }
}