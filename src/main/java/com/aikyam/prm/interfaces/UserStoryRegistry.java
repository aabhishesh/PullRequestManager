package com.aikyam.prm.interfaces;

import com.aikyam.prm.model.PR;

import java.util.List;


public interface UserStoryRegistry {

    public boolean addPRToUserStorySet(PR pr, String userStoryNumber);

    public boolean removePRFromUserStorySet(String repoName, String prNumber, String userStoryNumber);

    public void displayRegistry();

    public List<PR> getPRSetForUserStory(String username);
}
