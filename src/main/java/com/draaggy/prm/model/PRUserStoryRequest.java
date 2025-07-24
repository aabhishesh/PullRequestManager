package com.draaggy.prm.model;

public class PRUserStoryRequest {
    private PR pr;
    private String userStoryNumber;

    public PR getPr() {
        return pr;
    }

    public void setPr(PR pr) {
        this.pr = pr;
    }

    public String getUserStoryNumber() {
        return userStoryNumber;
    }

    public void setUserStoryNumber(String userStoryNumber) {
        this.userStoryNumber = userStoryNumber;
    }
}
