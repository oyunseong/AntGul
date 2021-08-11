package com.antgul.antgul_android.util;

import android.icu.text.SimpleDateFormat;

import java.util.Calendar;

public class TimeStamp {

    public String getTime() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar time = Calendar.getInstance();
        return format1.format(time.getTime());
    }
}
