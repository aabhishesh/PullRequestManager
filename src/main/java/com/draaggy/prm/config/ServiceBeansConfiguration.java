package com.draaggy.prm.config;

import com.draaggy.prm.async.AsyncQueueLauncher;
import com.draaggy.prm.async.MergeQueueManager;
import com.draaggy.prm.interfaces.*;
import com.draaggy.prm.registry.MergeQueueRegistryImpl;
import com.draaggy.prm.registry.UserStoryRegistryImpl;
import com.draaggy.prm.services.GithubPRManagingService;
import com.draaggy.prm.services.JenkinsBuildService;
import com.draaggy.prm.services.JenkinsTestingService;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeansConfiguration {

    @Bean
    @ConditionalOnMissingBean(MergeQueueRegistry.class)
    public MergeQueueRegistry mergeQueueRegistry() {
        return new MergeQueueRegistryImpl();
    }

    @Bean
    @ConditionalOnMissingBean(UserStoryRegistry.class)
    public UserStoryRegistry userStoryRegistry() {
        return new UserStoryRegistryImpl();
    }

    @Bean
    @ConditionalOnMissingBean(PRManagingService.class)
    public PRManagingService prManagingService(OkHttpClient okHttpClient, GithubSecretProvider githubSecretProvider) {
        return new GithubPRManagingService(okHttpClient, githubSecretProvider);
    }

    @Bean
    @ConditionalOnMissingBean(BuildService.class)
    public BuildService buildService(OkHttpClient okHttpClient) {
        return new JenkinsBuildService(okHttpClient);
    }

    @Bean
    @ConditionalOnMissingBean(TestingService.class)
    public TestingService testingService(OkHttpClient okHttpClient) {
        return new JenkinsTestingService(okHttpClient);
    }

    @Bean
    @ConditionalOnMissingBean(MergeQueueManager.class)
    public MergeQueueManager mergeQueueManager(MergeQueueRegistry mergeQueueRegistry, UserStoryRegistry userStoryRegistry, PRManagingService prManagingService) {
        return new MergeQueueManager(mergeQueueRegistry, userStoryRegistry, prManagingService);
    }

    @Bean
    @ConditionalOnMissingBean(AsyncQueueLauncher.class)
    public AsyncQueueLauncher asyncQueueLauncher(MergeQueueManager queueManager) {
        return new AsyncQueueLauncher(queueManager);
    }

}
