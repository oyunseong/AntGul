package com.antgul.antgul_android.model;

import java.util.List;

public class Post {
    private final String postId;
    private final String writerId; //User - uid
    private final String title;
    private final String content;
    private final List<String> imageList;
    private final List<Comment> commentList;
    private final List<String> hashTags;
    private final int category;    // 0 : 관리자(종목정보) , 1 : 사용자(자유게시판)
    private final String createAt;

    private Post(Builder builder) {
        postId = builder.postId;
        writerId = builder.writerId;
        title = builder.title;
        content = builder.content;
        imageList = builder.imageList;
        commentList = builder.commentList;
        hashTags = builder.hashTags;
        category = builder.category;
        createAt= builder.createAt;
    }

    public static class Builder {
        private String postId;
        private String writerId;
        private String title;
        private String content;
        private List<String> imageList;
        private List<Comment> commentList;
        private List<String> hashTags;
        private int category = 1;
        private String createAt = "0000:00:00";

        public Builder() {
//            this.title = title;
//            this.content = content;
            // TODO 해쉬태그 추가
        }

        public Builder postId(String val) {
            postId = val;
            return this;
        }

        public Builder writeId(String val) {
            writerId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder imageList(List<String> val) {
            imageList = val;
            return this;
        }

        public Builder commentList(List<Comment> val) {
            commentList = val;
            return this;
        }
        public Builder hashTags(List<String> val) {
            hashTags = val;
            return this;
        }

        public Builder category(int val) {
            category = val;
            return this;
        }

        public Builder createAt(String val) {
            createAt = val;
            return this;
        }
        public Post build(){
            return new Post(this);
        }
    }
}