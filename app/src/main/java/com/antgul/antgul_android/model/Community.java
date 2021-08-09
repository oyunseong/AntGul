package com.antgul.antgul_android.model;

import android.widget.ImageView;

public class Community {
    private ImageView imageView;
    private String title;
    private String time;
    private String content;

    public Community(String title, String time, String content) {
        //this.imageView = imageView;
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
