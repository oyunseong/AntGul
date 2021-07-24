package com.antgul.antgul_android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.antgul.antgul_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final int MAIN_ACTIVITY = 0;
    private final int FRAGMENT_HOME = 1;
    private final int FRAGMENT_BOARD = 2;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // 홈버튼 클릭
        binding.bottomNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        activity_main_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick","home button");
                callFragment(FRAGMENT_HOME);
            }
        });
        activity_main_board_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick","board button");
                callFragment(FRAGMENT_BOARD);
            }
        });

        // 액티비티 호출 시점에 있는 어느 플래그먼트를 프레임레이아웃에 올릴건지 정함
        callFragment(FRAGMENT_HOME);
    }


    public void callFragment(int index) {
        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (index) {
            // HomeActivity 프래그먼트 호출
            case 1:
                HomeActivity homeActivity = new HomeActivity();
                transaction.replace(R.id.frame_layout, homeActivity);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 2:
                BoardActivity boardActivity = new BoardActivity();
                transaction.replace(R.id.frame_layout, boardActivity);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }
}