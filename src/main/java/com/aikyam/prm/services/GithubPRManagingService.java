package com.aikyam.prm.services;

import com.aikyam.prm.interfaces.PRManagingService;
import com.aikyam.prm.model.PR;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConditionalOnMissingBean(PRManagingService.class)
public class GithubPRManagingService implements PRManagingService {

    @Autowired
    private OkHttpClient client;

    private final String GITHUB_TOKEN = "your-github-token";
    private final String GITHUB_API_URL = "https://api.github.com";
    private final String GITHUB_OWNER = "aikyam";

    @Override
    public boolean merge(PR pr) {
        String url = String.format("%s/repos/%s/%s/pulls/%d/merge", GITHUB_API_URL, GITHUB_OWNER, pr.getRepoName(), pr.getPrNumber());

        Request request = new Request.Builder()
                .url(url)
                .put(RequestBody.create("", null))
                .header("Authorization", "Bearer " + GITHUB_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Merge PR #" + pr.getPrNumber() + " status: " + response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean mergeSet(List<PR> prList) {
        Map<String, String> shaMap = new HashMap<>();
        for (PR pr : prList) {
            String sha = getPRHeadSha(pr.getPrNumber(), pr.getRepoName());
            if (sha == null) {
                System.out.println("Failed to get SHA for PR #" + pr.getPrNumber());
                continue;
                //fail here TODO
            }
            shaMap.putIfAbsent(pr.getPrNumber(), sha);
        }

        for(PR pr : prList) {
           merge(pr);
        }
        return true;
    }

    @Override
    public boolean isCheckPassed(String sha, String repoName) {
        String url = String.format("%s/repos/%s/%s/commits/%s/status", GITHUB_API_URL, GITHUB_OWNER, repoName, sha);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + GITHUB_TOKEN)
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
    public boolean isCheckPassedForAll(List<PR> prList, Map<String, String> shaMap) {
        for(PR pr : prList) {
            if (!isCheckPassed(shaMap.get(pr.getPrNumber()), pr.getRepoName())) {
                //TODO - remove this from queue and exit.
                return false;
            }
        }
        return true;
    }

    private String getPRHeadSha(String prNumber, String repoName) {
        String url = String.format("%s/repos/%s/%s/pulls/%s", GITHUB_API_URL, GITHUB_OWNER, repoName, prNumber);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + GITHUB_TOKEN)
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
