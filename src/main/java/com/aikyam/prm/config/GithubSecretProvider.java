package com.aikyam.prm.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

@Component
public class GithubSecretProvider {

    private static final String GITHUB_SECRET_PARAMETER_NAME = "github-webhook-secret"; // Update with your actual parameter name
    private String webhookSecret;

    public String getWebhookSecret() {
        return webhookSecret;
    }

    @PostConstruct
    public void init() {
//        try (SsmClient ssmClient = SsmClient.builder().region(Region.AP_SOUTH_1).build()) {
//            GetParameterResponse response = ssmClient.getParameter(
//                    GetParameterRequest.builder()
//                            .name(GITHUB_SECRET_PARAMETER_NAME)
//                            .withDecryption(true)
//                            .build()
//            );
//            this.webhookSecret = response.parameter().value();
//            if (webhookSecret == null || webhookSecret.isEmpty()) {
//                throw new RuntimeException("GitHub secret is empty");
//            }
//        } catch (SsmException e) {
//            throw new RuntimeException("Failed to fetch GitHub secret from AWS SSM", e);
//        }
    }
}
