package com.draaggy.prm.interfaces;

import com.draaggy.prm.model.PR;
import com.draaggy.prm.model.PullRequestEvent;

import java.util.List;
import java.util.Map;

public interface PRManagingService {

    public boolean merge(PullRequestEvent pr);

    public boolean mergeSet(List<PullRequestEvent> prList);

    public boolean isCheckPassed(String sha, String repoName);

    public boolean isCheckPassedForAll(List<PullRequestEvent> prList, Map<String, String> shaMap);
}
