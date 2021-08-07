package com.antgul.antgul_android.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.antgul.antgul_android.R;

public class ProgressDialog extends Dialog {

    private final Handler handler;

    public ProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_progress);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        // TODO Looper.getMainLooper 검색
        handler = new Handler(Looper.getMainLooper());
    }

    public void showProgress() {
        if (!isShowing()) {
            show();
            startTimer();
        }
    }

    public void hideProgress() {
        handler.removeCallbacksAndMessages(null);
        dismiss();
    }

    private void startTimer() {
        if (handler != null) {
            handler.postDelayed(() -> {
                Toast.makeText(getContext(), "네트워크 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                hideProgress();
            }, 10000L);
        }
    }

}
