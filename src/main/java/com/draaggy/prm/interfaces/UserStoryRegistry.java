package com.draaggy.prm.interfaces;

import com.draaggy.prm.model.PullRequestEvent;

import java.util.List;


public interface UserStoryRegistry {

    public boolean addPRToUserStorySet(PullRequestEvent pr, String userStoryNumber);

    public boolean removePRFromUserStorySet(PullRequestEvent pr, String userStoryNumber);

    public void displayRegistry();

    public List<PullRequestEvent> getPRSetForUserStory(String userStoryNumber);
}
