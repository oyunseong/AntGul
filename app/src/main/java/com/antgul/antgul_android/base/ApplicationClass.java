package com.antgul.antgul_android.base;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ApplicationClass extends Application {
    public static String USERS_COLLECTION = "User";
    public static String POSTS_COLLECTION = "Post";
    public static String COMMENT_COLLECTION = "Comment";
    public static String REGEX_NICK = "^[가-힣a-zA-Z0-9]{2,12}$";                                               // 한글/영문/숫자 포함 2~12

    @Override
    public void onCreate() {
        super.onCreate();
//        getHashKey();
    }

    private void getHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.antgul.antgul_android", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i(this.getClass().getSimpleName(), "--key_hash=" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
