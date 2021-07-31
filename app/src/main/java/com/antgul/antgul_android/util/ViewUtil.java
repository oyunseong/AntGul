package com.antgul.antgul_android.util;

import android.util.Log;
import android.widget.EditText;

public class ViewUtil {
    public static String getEditTextValue(EditText editText) {
        if (editText.getText().toString().equals("")) {
            return "";
        }
        return editText.getText().toString();

    }
}
