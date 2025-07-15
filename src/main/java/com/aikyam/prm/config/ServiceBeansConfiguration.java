package com.aikyam.prm.config;

import com.aikyam.prm.async.AsyncQueueLauncher;
import com.aikyam.prm.async.MergeQueueManager;
import com.aikyam.prm.interfaces.*;
import com.aikyam.prm.registry.MergeQueueRegistryImpl;
import com.aikyam.prm.registry.UserStoryRegistryImpl;
import com.aikyam.prm.services.GithubPRManagingService;
import com.aikyam.prm.services.JenkinsBuildService;
import com.aikyam.prm.services.JenkinsTestingService;
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
    public PRManagingService prManagingService(OkHttpClient okHttpClient) {
        return new GithubPRManagingService(okHttpClient);
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
    public MergeQueueManager mergeQueueManager(MergeQueueRegistry mergeQueueRegistry, UserStoryRegistry userStoryRegistry) {
        return new MergeQueueManager(mergeQueueRegistry, userStoryRegistry);
    }

    @Bean
    @ConditionalOnMissingBean(AsyncQueueLauncher.class)
    public AsyncQueueLauncher asyncQueueLauncher(MergeQueueManager queueManager) {
        return new AsyncQueueLauncher(queueManager);
    }

}
