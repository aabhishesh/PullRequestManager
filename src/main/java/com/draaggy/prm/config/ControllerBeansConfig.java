package com.draaggy.prm.config;

//import com.aikyam.prm.controllers.SampleTestPRManagerControllerImpl;
import com.draaggy.prm.interfaces.MergeQueueRegistry;
import com.draaggy.prm.interfaces.PRManagerController;
import com.draaggy.prm.interfaces.UserStoryRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerBeansConfig {

//    @Bean
//    @ConditionalOnMissingBean(PRManagerController.class)
//    public PRManagerController prManagerController(UserStoryRegistry userStoryRegistryImpl, MergeQueueRegistry mergeQueueRegistryImpl) {
//        return new SampleTestPRManagerControllerImpl(userStoryRegistryImpl, mergeQueueRegistryImpl);
//    }

}
