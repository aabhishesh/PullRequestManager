package com.aikyam.prm.controllers;

import com.aikyam.prm.config.GithubSecretProvider;
import com.aikyam.prm.model.IssueCommentEvent;
import com.aikyam.prm.model.PullRequestEvent;
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

    @PostMapping("/github/webhook")
    public ResponseEntity<String> onWebhook(
            @RequestHeader("X-GitHub-Event") String event,
            @RequestHeader(value = "X-Hub-Signature", required = false) String signature,
            @RequestBody String body
    ) {
//        if (!verifySignature(body, signature)) {
//            return ResponseEntity.status(403).body("Invalid signature");
//        }

        try {
            switch (event) {
                case "pull_request":
                    PullRequestEvent pr = mapper.readValue(body, PullRequestEvent.class);
                    System.out.println("Pull Request from: " + pr.getPull_request().getUser().getLogin());
                    System.out.println(pr);
                    break;
                case "issue_comment":
                    IssueCommentEvent comment = mapper.readValue(body, IssueCommentEvent.class);
                    System.out.println("Comment: " + comment.getComment().getBody());
                    System.out.println(comment);
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
