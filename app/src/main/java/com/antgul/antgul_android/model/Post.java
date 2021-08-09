package com.antgul.antgul_android.model;

import java.util.List;

public class Post {
    public String postId;
    public String title;
    public String content;
    public List<String> imageList;
    public String writerId; //User - uid
    public List<Comment> commentList;
    public int category;
    public String createAt;
    public List<String> hashTags;
}
