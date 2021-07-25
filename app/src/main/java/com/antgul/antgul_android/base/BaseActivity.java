package com.antgul.antgul_android.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 모든 액티비티는 해당 클래스를 상속받도록 수정하기.
 *
 */
public class BaseActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "++onCreate"); //이런 식으로 모든 생명 주기에, 태그 달기
    }
}
