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

    public Post(){}

    public Post(String postId, String title, String content, List<String> imageList, String writerId, List<Comment> commentList, int category, String createAt, List<String> hashTags) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.imageList = imageList;
        this.writerId = writerId;
        this.commentList = commentList;
        this.category = category;
        this.createAt = createAt;
        this.hashTags = hashTags;
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getWriterId() {
        return writerId;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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

    public List<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<String> hashTags) {
        this.hashTags = hashTags;
    }
}
