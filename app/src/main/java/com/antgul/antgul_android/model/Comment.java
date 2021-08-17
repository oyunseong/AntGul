package com.antgul.antgul_android.model;

public class Comment {
    public String id;
    public String content;
    public int like;
    public String createAt;
    public String writerId;

    public Comment(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
