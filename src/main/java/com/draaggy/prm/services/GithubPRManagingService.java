package com.draaggy.prm.services;

import com.draaggy.prm.config.GithubSecretProvider;
import com.draaggy.prm.interfaces.PRManagingService;
import com.draaggy.prm.model.PR;
import com.draaggy.prm.model.PullRequestEvent;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GithubPRManagingService implements PRManagingService {


    private OkHttpClient client;

    private GithubSecretProvider githubSecretProvider;

    public GithubPRManagingService(OkHttpClient client,  GithubSecretProvider githubSecretProvider) {
        this.client = client;
        this.githubSecretProvider = githubSecretProvider;
    }

    private final String GITHUB_API_URL = "https://api.github.com";
    private final String GITHUB_OWNER = "aabhishesh";

    @Override
    public boolean merge(PullRequestEvent pr) {
        String url = String.format("%s/repos/%s/%s/pulls/%d/merge", GITHUB_API_URL, GITHUB_OWNER, pr.getRepository().getName(), pr.getNumber());
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .put(RequestBody.create("", null))
                .header("Authorization", "Bearer " + githubSecretProvider.getGithubToken())
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Merge PR #" + pr.getNumber() + " status: " + response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean mergeSet(List<PullRequestEvent> prList) {
        Map<Integer, String> shaMap = new HashMap<>();
        for (PullRequestEvent pr : prList) {
            String sha = getPRHeadSha(pr.getNumber(), pr.getRepository().getName());
            if (sha == null) {
                System.out.println("Failed to get SHA for PR #" + pr.getNumber());
                continue;
                //fail here TODO
            }
            shaMap.putIfAbsent(pr.getNumber(), sha);
        }

        for(PullRequestEvent pr : prList) {
           merge(pr);
        }
        return true;
    }

    @Override
    public boolean isCheckPassed(String sha, String repoName) {
        String url = String.format("%s/repos/%s/%s/commits/%s/status", GITHUB_API_URL, GITHUB_OWNER, repoName, sha);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + githubSecretProvider.getGithubToken())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) return false;

            String body = response.body().string();
            JsonObject json = JsonParser.parseString(body).getAsJsonObject();
            String state = json.get("state").getAsString();
            return state.equalsIgnoreCase("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isCheckPassedForAll(List<PullRequestEvent> prList, Map<String, String> shaMap) {
        for(PullRequestEvent pr : prList) {
            if (!isCheckPassed(shaMap.get(pr.getNumber()), pr.getRepository().getName())) {
                //TODO - remove this from queue and exit.
                return false;
            }
        }
        return true;
    }

    private String getPRHeadSha(int prNumber, String repoName) {
        String url = String.format("%s/repos/%s/%s/pulls/%s", GITHUB_API_URL, GITHUB_OWNER, repoName, prNumber);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + githubSecretProvider.getGithubToken())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) return null;

            String body = response.body().string();
            JsonObject json = JsonParser.parseString(body).getAsJsonObject();
            return json.get("head").getAsJsonObject().get("sha").getAsString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
