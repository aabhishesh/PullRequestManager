package com.aikyam.prm.services;

import com.aikyam.prm.interfaces.BuildService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

public class JenkinsBuildService implements BuildService {


    private OkHttpClient client;

    public JenkinsBuildService(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    private static final String JENKINS_URL = "https://your-jenkins-domain";
    private static final String JOB_NAME = "api-automation"; // make this to be read from config and fail if value is not present.
    private static final String USERNAME = "tbBuildUser";
    private static final String AUTH_TOKEN = "auth-token";



    @Override
    public void build(String userStoryNumber) {
        String params = "param1=value1&param2=value2";
        String url = String.format("%s/job/%s/buildWithParameters?%s", JENKINS_URL, JOB_NAME, params);
        String auth = USERNAME + ":" + AUTH_TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create("", null))
                .header("Authorization", "Basic " + encodedAuth)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Jenkins trigger status: " + response.code());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
