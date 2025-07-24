package com.draaggy.prm.controllers;

import com.draaggy.prm.config.GithubSecretProvider;
import com.draaggy.prm.interfaces.MergeQueueRegistry;
import com.draaggy.prm.interfaces.UserStoryRegistry;
import com.draaggy.prm.model.CommentEnum;
import com.draaggy.prm.model.IssueCommentEvent;
import com.draaggy.prm.model.PullRequestEvent;
import com.draaggy.prm.util.PREventUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
public class GithubWebhookPRManagerControllerImpl {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GithubSecretProvider githubSecretProvider;

    @Autowired
    private UserStoryRegistry userStoryRegistry;

    @Autowired
    private MergeQueueRegistry mergeQueueRegistry;

    @PostMapping("/github/webhook")
    public ResponseEntity<String> onWebhook(
            @RequestHeader("X-GitHub-Event") String event,
            @RequestHeader(value = "X-Hub-Signature", required = false) String signature,
            @RequestBody String body
    ) {

        System.out.println("body received : " + body);

//        if (!verifySignature(body, signature)) {
//            return ResponseEntity.status(403).body("Invalid signature");
//        }

        try {
            switch (event) {
                case "pull_request":
                    PullRequestEvent pr = mapper.readValue(body, PullRequestEvent.class);
                    System.out.println(pr);
                    if (pr.getAction().equals("opened") || pr.getAction().equals("reopened")) {
                        System.out.println("Pull request opened or reopened: ");
                        String userStoryNumber = PREventUtil.getUserStoryNameFromBranchName(pr.getPull_request().getHead().getRef());
                        userStoryRegistry.addPRToUserStorySet(pr, userStoryNumber);
                    } else if (pr.getAction().equals("closed")) {
                        System.out.println("Pull request closed: ");
                        String userStoryNumber = PREventUtil.getUserStoryNameFromBranchName(pr.getPull_request().getHead().getRef());
                        userStoryRegistry.removePRFromUserStorySet(pr, userStoryNumber);
                    } else if (pr.getAction().equals("synchronize")) {
                        System.out.println("Pull request synchronized: ");
                    }
                    break;
                case "issue_comment":
                    System.out.println("Issue Comment event received");
                    IssueCommentEvent comment = mapper.readValue(body, IssueCommentEvent.class);
                    String commentBody = comment.getComment().getBody().toUpperCase();
                    System.out.println(comment.getIssue().getTitle());
                    if (commentBody.startsWith("/")) {
                        commentBody = commentBody.substring(1);
                    }
                    String userStoryNumber = PREventUtil.getUserStoryNameFromBranchName(comment.getIssue().getTitle());
                    switch (CommentEnum.getEnum(commentBody)) {
                        case TEST : {
                            System.out.println("Performing test activities for user story: " + userStoryNumber);
                            Thread.sleep(100000);
                        }

                        case TEST_AND_MERGE: {
                            Thread.sleep(10000);
                            mergeQueueRegistry.addUserStoryToMergeQueue(userStoryNumber);
                            break;
                        }
                        case STATUS: {
                            System.out.println(mergeQueueRegistry.getQueueNumberForUserStory(userStoryNumber));
                            // comment on the PR with the status
                        }


                    }
                    System.out.println(comment);
                    break;
                case "check_suite":
                    System.out.println("Check Suite event received");
                    break;
                default:
                    System.out.println("Unhandled event: " + event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error parsing event");
        }

        return ResponseEntity.ok("Event processed");
    }

    private boolean verifySignature(String payload, String signature) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(githubSecretProvider.getWebhookSecret().getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(keySpec);
            byte[] hmacBytes = mac.doFinal(payload.getBytes());
            String calculated = "sha1=" + Hex.encodeHexString(hmacBytes);
            return calculated.equals(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
