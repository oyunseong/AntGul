package com.antgul.antgul_android.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.antgul.antgul_android.R;

//TODO 타임아웃 기능 추가하기. 일정 시간동안, 프로그레스바가 사라지지 않는다면, 10초 후 프로그레스바 없애고, 토스트 메시지 띄우기
//관련 개념: 쓰레드, 핸들러 등등
public class ProgressDialog extends Dialog {

    public ProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_progress);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void showProgress() {
        if (!isShowing()) {
            show();
        }
    }

    public void hideProgress() {
        dismiss();
    }

}
