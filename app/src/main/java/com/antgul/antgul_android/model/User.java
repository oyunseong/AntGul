package com.antgul.antgul_android.model;

import java.util.List;
import java.util.Objects;

public class User {
    public String uid;
    public String email;
    public String password;
    public String nickname;
    public String statusMessage; //추가
    public List<String> postList;
    public List<String> likeStockList;
    public String token;
    public String createAt; //timestamp 참고

    public User() {

    }

    public User(String uid, String email, String password, String nickname, List<String> postList, List<String> likeStockList, String token, String createAt) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.postList = postList;
        this.likeStockList = likeStockList;
        this.token = token;
        this.createAt = createAt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getPostList() {
        return postList;
    }

    public void setPostList(List<String> postList) {
        this.postList = postList;
    }

    public List<String> getLikeStockList() {
        return likeStockList;
    }

    public void setLikeStockList(List<String> likeStockList) {
        this.likeStockList = likeStockList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return uid.equals(user.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}
