package com.aikyam.prm.interfaces;

public interface MergeQueueRegistry {

    public boolean addUserStoryToMergeQueue(String userStoryNumber);

    public boolean removeUserStoryFromMergeQueue(String userStoryNumber);

    public void displayMergeQueue();

    public String getUserStoryToMerge();

    public boolean postProcessingRemovalOfUserStory();

    public Integer getQueueNumberForUserStory(String userStoryNumber);
}
