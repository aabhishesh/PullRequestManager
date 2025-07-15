package com.aikyam.prm.registry;

import com.aikyam.prm.interfaces.UserStoryRegistry;
import com.aikyam.prm.model.PR;
import io.micrometer.common.util.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class UserStoryRegistryImpl implements UserStoryRegistry {

    private Map<String, List<PR>> userStoryRegistry = new ConcurrentHashMap<>();

    @Override
    public boolean addPRToUserStorySet(PR pr, String userStoryNumber) {
        if(PR.isEmpty(pr)) {
            return false;
        }
        if(this.userStoryRegistry.containsKey(userStoryNumber)) {
            this.userStoryRegistry.get(userStoryNumber).add(pr);
        } else {
            List<PR> prList = new ArrayList<>();
            prList.add(pr);
            this.userStoryRegistry.put(userStoryNumber, prList);
        }
        return true;
    }

    @Override
    public boolean removePRFromUserStorySet(String repoName, String prNumber, String userStoryNumber) {
        if(StringUtils.isEmpty(repoName) || StringUtils.isEmpty(prNumber) || StringUtils.isEmpty(userStoryNumber)) {
            return false;
        }
        if(this.userStoryRegistry.containsKey(userStoryNumber)) {
            this.userStoryRegistry.get(userStoryNumber).removeIf(pr -> !(pr.getPrNumber() == prNumber && pr.getRepoName() == repoName));
            return true;
        }
        return false;
    }


    @Override
    public void displayRegistry() {
        System.out.println(userStoryRegistry);
    }

    @Override
    public List<PR> getPRSetForUserStory(String userStoryNumber) {
        return userStoryRegistry.get(userStoryNumber);
    }


}
