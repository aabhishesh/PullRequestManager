package com.draaggy.prm.model;

import io.micrometer.common.util.StringUtils;

public class PR {
    private String prLink;
    private String prNumber;
    private String repoName;
    private String authorName;

    public PR(String prLink, String prNumber, String repoName, String authorName) {
        this.prLink = prLink;
        this.prNumber = prNumber;
        this.repoName = repoName;
        this.authorName = authorName;
    }

    public String getPrLink() {
        return prLink;
    }

    public void setPrLink(String prLink) {
        this.prLink = prLink;
    }

    public String getPrNumber() {
        return prNumber;
    }

    public void setPrNumber(String prNumber) {
        this.prNumber = prNumber;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public static boolean isEmpty(PR pr) {
        if(StringUtils.isEmpty(pr.authorName) || StringUtils.isEmpty(pr.prLink) || StringUtils.isEmpty(pr.repoName) || StringUtils.isEmpty(pr.prNumber)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PR{" +
                "prLink='" + prLink + '\'' +
                ", prNumber='" + prNumber + '\'' +
                ", repoName='" + repoName + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
