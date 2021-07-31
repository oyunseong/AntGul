package com.antgul.antgul_android.model;

public class User {
    public String ID;
    public String PW;

    User(String userID, String userPW)
    {
        this.ID = userID;
        this.PW = userPW;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }
}
