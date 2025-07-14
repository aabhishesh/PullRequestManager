package com.aikyam.prm.async;

import com.aikyam.prm.interfaces.MergeQueueRegistry;
import com.aikyam.prm.interfaces.UserStoryRegistry;
import com.aikyam.prm.model.PR;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@ConditionalOnMissingBean(MergeQueueManager.class)
public class MergeQueueManager {


    @Autowired
    private MergeQueueRegistry mergeQueueRegistryImpl;

    @Autowired
    private UserStoryRegistry userStoryRegistryImpl;

    private final AtomicBoolean running = new AtomicBoolean(true);
    private final AtomicBoolean processing = new AtomicBoolean(false);


    @Async("applicationTaskExecutor")
    public void processQueue() {

        if (!processing.compareAndSet(false, true)) {
            System.out.println("[QueueManager] Processor already running.");
            return; // Already running
        }

        String threadName = Thread.currentThread().getName();
        System.out.println("[QueueManager] Processor started in thread: " + threadName);

        String userStory = null;
        try {
            while(running.get() && !Thread.currentThread().isInterrupted()) {

                System.out.println("trying to merge something if present");
                userStory = mergeQueueRegistryImpl.getUserStoryToMerge();
                if (StringUtils.isEmpty(userStory)) {
                    System.out.println("Less work, nothing to merge, sleeping for 5 secs, ciao amigo");
                    try {
                        Thread.sleep(5000);
                        System.out.println("Sleep is done, trying again");
                        continue;
                    } catch (InterruptedException e) {
                        System.err.println("Queue processing failed for user story - " + userStory + " with reason - " + e.getMessage());
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                System.out.println("[Processing] User story: " + userStory + " | Thread: " + Thread.currentThread().getName());

                List<PR> prSet = userStoryRegistryImpl.getPRSetForUserStory(userStory);

                System.out.println("PR set retrieved to be merged - " + prSet);

                //check if all checks are passed
                // wait for deployment and checks to happen
                Thread.sleep(1000);
                //wait for regression to happen
                Thread.sleep(10000);
                // trigger regression


                //if fine, merge them automatically.
                System.out.println("merging is done" + Thread.currentThread().getName());

                System.out.println("merge queue before removal of story" + Thread.currentThread().getName());
                mergeQueueRegistryImpl.displayMergeQueue();


                mergeQueueRegistryImpl.removeUserStoryFromMergeQueue(userStory);

                System.out.println("merge queue after removal of story");
                mergeQueueRegistryImpl.displayMergeQueue();
            }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                   processing.set(false);
                   System.out.println("[QueueManager] Processor stopped in thread: " + Thread.currentThread().getName());
        }
    }

    @PreDestroy
    public void shutDown() {
        System.out.println("[Shutdown] Initiated.");
        running.set(false);
        System.out.println("[Queue] Processor shutting down...");
    }


    public boolean isProcessing() {
        return this.processing.get();
    }

}
