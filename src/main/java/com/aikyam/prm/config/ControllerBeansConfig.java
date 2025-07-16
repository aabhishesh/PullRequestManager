package com.aikyam.prm.config;

import com.aikyam.prm.controllers.SampleTestPRManagerControllerImpl;
import com.aikyam.prm.interfaces.MergeQueueRegistry;
import com.aikyam.prm.interfaces.PRManagerController;
import com.aikyam.prm.interfaces.UserStoryRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerBeansConfig {

    @Bean
    @ConditionalOnMissingBean(PRManagerController.class)
    public PRManagerController prManagerController(UserStoryRegistry userStoryRegistryImpl, MergeQueueRegistry mergeQueueRegistryImpl) {
        return new SampleTestPRManagerControllerImpl(userStoryRegistryImpl, mergeQueueRegistryImpl);
    }

}
