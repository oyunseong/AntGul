package com.antgul.antgul_android.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import com.antgul.antgul_android.R;

//TODO 타임아웃 기능 추가하기
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
