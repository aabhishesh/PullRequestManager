package com.draaggy.prm.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

@Component
public class GithubSecretProvider {

    private static final String GITHUB_SECRET_PARAMETER_NAME = "github-webhook-secret";
    private static final String GITHUB_TOKEN = "github-PRM-WEBAPP-token";
    private String webhookSecret;
    private String githubToken;

    public String getWebhookSecret() {
        return webhookSecret;
    }

    public String getGithubToken() {
        return githubToken;
    }

    @PostConstruct
    public void init() {
//        try (SecretsManagerClient client = SecretsManagerClient.builder().region(Region.AP_SOUTH_1).build()) {
//            GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
//                    .secretId(GITHUB_SECRET_PARAMETER_NAME)
//                    .build();
//
//            GetSecretValueResponse getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
//            this.webhookSecret = getSecretValueResponse.secretString();
//        } catch (SecretsManagerException e) {
//            throw new RuntimeException("Failed to retrieve secret: " + e.awsErrorDetails().errorMessage(), e);
//        }
//
//        try (SecretsManagerClient client = SecretsManagerClient.builder().region(Region.AP_SOUTH_1).build()) {
//            GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
//                    .secretId(GITHUB_TOKEN)
//                    .build();
//
//            GetSecretValueResponse getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
//            this.githubToken = getSecretValueResponse.secretString();
//        } catch (SecretsManagerException e) {
//            throw new RuntimeException("Failed to retrieve secret: " + e.awsErrorDetails().errorMessage(), e);
//        }

    }
}
