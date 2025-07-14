package com.aikyam.prm.interfaces;

import com.aikyam.prm.model.PR;

import java.util.List;
import java.util.Map;

public interface PRManagingService {

    public boolean merge(PR pr);

    public boolean mergeSet(List<PR> prList);

    public boolean isCheckPassed(String sha, String repoName);

    public boolean isCheckPassedForAll(List<PR> prList, Map<String, String> shaMap);
}
