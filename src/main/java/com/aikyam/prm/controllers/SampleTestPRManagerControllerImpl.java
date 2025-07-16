package com.aikyam.prm.controllers;

import com.aikyam.prm.interfaces.MergeQueueRegistry;
import com.aikyam.prm.interfaces.PRManagerController;
import com.aikyam.prm.interfaces.UserStoryRegistry;
import com.aikyam.prm.model.CommentEnum;
import com.aikyam.prm.model.PR;
import com.aikyam.prm.model.PRCommentRequest;
import com.aikyam.prm.model.PRUserStoryRequest;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class SampleTestPRManagerControllerImpl implements PRManagerController {

    private UserStoryRegistry userStoryRegistryImpl;

    private MergeQueueRegistry mergeQueueRegistryImpl;

    @Autowired
    public SampleTestPRManagerControllerImpl(UserStoryRegistry userStoryRegistryImpl, MergeQueueRegistry mergeQueueRegistryImpl) {
        this.userStoryRegistryImpl = userStoryRegistryImpl;
        this.mergeQueueRegistryImpl = mergeQueueRegistryImpl;
    }

    @PostMapping(value = "/pr/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addPrToUserStory(@RequestBody PRUserStoryRequest request) {
        if(null == request) {
            System.out.println("Request is null");
            return false;
        }
        if(PR.isEmpty(request.getPr())) {
            System.out.println("PR object is null");
            return false;
        }
        if(StringUtils.isEmpty(request.getUserStoryNumber())) {
            System.out.println("UserStory number is empty");
            return false;
        }

        boolean result = userStoryRegistryImpl.addPRToUserStorySet(request.getPr(), request.getUserStoryNumber());
        userStoryRegistryImpl.displayRegistry();
        return result;
    }

    @DeleteMapping(value = "/pr", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean removePrFromUserStory(@RequestBody PRUserStoryRequest request) {
        if(null == request) {
            System.out.println("Request is null");
            return false;
        }
        if(PR.isEmpty(request.getPr())) {
            System.out.println("PR object is null");
            return false;
        }
        if(StringUtils.isEmpty(request.getUserStoryNumber())) {
            System.out.println("UserStory number is empty");
            return false;
        }

        boolean result = userStoryRegistryImpl.removePRFromUserStorySet(request.getPr().getRepoName(), request.getPr().getPrNumber(), request.getUserStoryNumber());
        userStoryRegistryImpl.displayRegistry();
        return result;
    }

    @PostMapping(value = "/pr/comment")
    public boolean addressComment(@RequestBody PRCommentRequest request) {
        if(null == request) {
            System.out.println("Comment is empty");
            return false;
        }
        if(PR.isEmpty(request.getPr())) {
            System.out.println("PR object is null");
            return false;
        }
        if(StringUtils.isEmpty(request.getComment())) {
            System.out.println("comment is empty");
            return false;
        }

        CommentEnum commentEnum = CommentEnum.valueOf(request.getComment());

        switch (commentEnum) {
            case TEST:
                // perform test activities
                System.out.println("perform test activities");
                break;

            case TEST_AND_MERGE:
                // call same function as TEST
                // once done
                // put this to merge queue

                mergeQueueRegistryImpl.addUserStoryToMergeQueue(request.getUserStoryNumber());
                break;

            default:
                System.out.println("comment is not defined");
                return false;
        }
        return true;
    }

    @GetMapping(value = "/pr/status")
    public String getPRMergeQueueNumber(@RequestParam(value = "userStoryNumber") String userStoryNumber) {
        if(StringUtils.isEmpty(userStoryNumber)) {
            return "user story number is empty";
        }
        Integer waitingNumber  = mergeQueueRegistryImpl.getQueueNumberForUserStory(userStoryNumber);
        return "Your current number in queue is - " + waitingNumber;
    }

    @PostMapping("/github/webhook")
    public ResponseEntity<String> handleWebhook (
            @RequestHeader("X-GitHub-Event") String event,
            @RequestBody String payload
    ) {
        System.out.println("🔔 Event Type: " + event);
        System.out.println("📦 Payload:\n" + payload);
        return ResponseEntity.ok("Webhook received");
    }
}
