package com.draaggy.prm.registry;

import com.draaggy.prm.interfaces.UserStoryRegistry;
import com.draaggy.prm.model.PullRequestEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class UserStoryRegistryImpl implements UserStoryRegistry {

    private Map<String, List<PullRequestEvent>> userStoryRegistry = new ConcurrentHashMap<>();

    @Override
    public boolean addPRToUserStorySet(PullRequestEvent pr, String userStoryNumber) {
        if(this.userStoryRegistry.containsKey(userStoryNumber)) {
            this.userStoryRegistry.get(userStoryNumber).add(pr);
        } else {
            List<PullRequestEvent> prList = new ArrayList<>();
            prList.add(pr);
            this.userStoryRegistry.put(userStoryNumber, prList);
        }
        return true;
    }

    @Override
    public boolean removePRFromUserStorySet(PullRequestEvent prToRemove, String userStoryNumber) {
        if(this.userStoryRegistry.containsKey(userStoryNumber)) {
            this.userStoryRegistry.get(userStoryNumber).removeIf(pr -> !(pr.getNumber() == prToRemove.getNumber() && pr.getRepository().getName() == prToRemove.getRepository().getName()));
            return true;
        }
        return false;
    }


    @Override
    public void displayRegistry() {
        System.out.println(userStoryRegistry);
    }

    @Override
    public List<PullRequestEvent> getPRSetForUserStory(String userStoryNumber) {
        return userStoryRegistry.get(userStoryNumber);
    }


}
