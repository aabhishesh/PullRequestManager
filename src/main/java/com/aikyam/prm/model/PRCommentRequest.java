package com.aikyam.prm.model;

public class PRCommentRequest {
    private PR pr;
    private String comment;
    private String userStoryNumber;

    public PR getPr() {
        return pr;
    }

    public void setPr(PR pr) {
        this.pr = pr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserStoryNumber() {
        return userStoryNumber;
    }

    public void setUserStoryNumber(String userStoryNumber) {
        this.userStoryNumber = userStoryNumber;
    }
}
