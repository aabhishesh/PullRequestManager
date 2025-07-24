package com.draaggy.prm.util;

import org.springframework.stereotype.Component;

@Component
public class PREventUtil {

    public static String getUserStoryNameFromBranchName(String branchName) {
        if (branchName == null || branchName.isEmpty()) {
            return null;
        }
        String[] parts = branchName.split("-");
        String userStoryPart = parts[0]; // Assuming user story is the first part of the branch name
        if (!userStoryPart.startsWith("US") && !userStoryPart.startsWith("DE") && !userStoryPart.startsWith("FE")
                && !userStoryPart.startsWith("us") && !userStoryPart.startsWith("de") && !userStoryPart.startsWith("fe")) {
            return null; // Should start with US-, DE-, FE- or their lowercase variants
        }
        return userStoryPart; // Return as is if no prefix

    }

    public static String getUserStoryNameFromPRTitle(String prTitle) {
        if (prTitle == null || prTitle.isEmpty()) {
            return null;
        }
        String[] parts = prTitle.split("-");
        String userStoryPart = parts[0]; // Assuming user story is the first part of the PR title
        if (!userStoryPart.startsWith("US-") && !userStoryPart.startsWith("DE-") && !userStoryPart.startsWith("FE-")
                && !userStoryPart.startsWith("us-") && !userStoryPart.startsWith("de-") && !userStoryPart.startsWith("fe-")) {
            return null; // Should start with US-, DE-, FE- or their lowercase variants
        }
        return userStoryPart; // Return as is if no prefix
    }
}
