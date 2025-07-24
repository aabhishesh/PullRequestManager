package com.draaggy.prm.registry;


import com.draaggy.prm.interfaces.MergeQueueRegistry;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MergeQueueRegistryImpl implements MergeQueueRegistry {

    private final Queue<String> mergeQueue = new ConcurrentLinkedQueue<>();

    @Override
    public boolean addUserStoryToMergeQueue(String userStoryNumber) {
        System.out.println("before adding user story to merge queue: " + Thread.currentThread().getName());
        this.displayMergeQueue();
        if(mergeQueue.contains(userStoryNumber)) {
            System.out.println("user story already present in queue");
            return false;
        }

        mergeQueue.add(userStoryNumber);
        return true;
    }

    @Override
    public boolean removeUserStoryFromMergeQueue(String userStoryNumber) {
        System.out.println("before removing user story to merge queue: " + Thread.currentThread().getName());
        this.displayMergeQueue();
        if(!mergeQueue.peek().equals(userStoryNumber)) {
            System.out.println("user story is not present in queue" + Thread.currentThread().getName());
            return false;
        }

        return this.postProcessingRemovalOfUserStory();
    }

    @Override
    public void displayMergeQueue() {
        System.out.println(mergeQueue);
    }

    @Override
    public String getUserStoryToMerge() {
        return mergeQueue.peek();
    }

    @Override
    public boolean postProcessingRemovalOfUserStory() {
        return mergeQueue.poll() != null;
    }

    @Override
    public Integer getQueueNumberForUserStory(String userStoryNumber) {
        Iterator<String> iterator = mergeQueue.iterator();
        int statusPos = 1;
        while(iterator.hasNext()) {
            if(userStoryNumber.equals(iterator.next())) {
                return statusPos;
            }
            statusPos++;
        }
        return statusPos;
    }


}
