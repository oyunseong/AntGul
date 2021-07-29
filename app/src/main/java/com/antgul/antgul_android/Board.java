package com.antgul.antgul_android;

import android.widget.ImageView;
import android.widget.TextView;

public class Board {
    private ImageView imageView;
    private String nickName;
    private String time;
    private String content;

    public Board( String nickName, String time, String content) {
        //this.imageView = imageView;
        this.nickName = nickName;
        this.time = time;
        this.content = content;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
