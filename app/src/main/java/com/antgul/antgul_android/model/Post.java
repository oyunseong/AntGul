package com.antgul.antgul_android.model;

import java.util.List;

public class Post {
    private  String postId;
    private  String writerId; //User - uid
    private  String title;
    private  String content;
    private  List<String> imageList;
    private  List<Comment> commentList;
    private  List<String> hashTags;
    private  int category;    // 0 : 관리자(종목정보) , 1 : 사용자(자유게시판)
    private  String createAt;
    public Post(){}

    public Post(String postId, String writerId, String title, String content, List<String> imageList, List<Comment> commentList, List<String> hashTags, int category, String createAt) {
        this.postId = postId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.imageList = imageList;
        this.commentList = commentList;
        this.hashTags = hashTags;
        this.category = category;
        this.createAt = createAt;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<String> hashTags) {
        this.hashTags = hashTags;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}