package com.antgul.antgul_android.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Comment {

    private String writerId;         // 댓글 작성자
    private String writerNickname;   // 댓글 작성자 닉네임
    private String comment;          // 댓글 내용
    private String postUid;          // 댓글이 속한 게시글 Uid
    private String createAt;         // 작성 일시
    private ArrayList<String> likeList; // 좋아요 수
    private ArrayList<String> ToReplys; // 댓글의 답글 리스트
    private String postId;

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

    public String getWriterNickname() {
        return writerNickname;
    }

    public void setWriterNickname(String writerNickname) {
        this.writerNickname = writerNickname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPostUid() {
        return postUid;
    }

    public void setPostUid(String postUid) {
        this.postUid = postUid;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public ArrayList<String> getLikeList() {
        return likeList;
    }

    public void setLikeList(ArrayList<String> likeList) {
        this.likeList = likeList;
    }

    public ArrayList<String> getToReplys() {
        return ToReplys;
    }

    public void setToReplys(ArrayList<String> toReplys) {
        ToReplys = toReplys;
    }
}
