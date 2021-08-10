package com.antgul.antgul_android.util;

import android.icu.text.SimpleDateFormat;
import android.icu.util.ULocale;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Date;

public class TimeStamp {
    public void getTimeStamp(){
        try{
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy K:mm aa");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy");
            Date parsedDate = inputFormat.parse("18-10-2019 01:05 pm");
            String outputDateStr = outputFormat.format(parsedDate);
            Log.d("***parsedDate",parsedDate.toString());
            Log.d("***timestamp",outputDateStr);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
