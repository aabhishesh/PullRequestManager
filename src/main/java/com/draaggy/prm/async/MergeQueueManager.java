package com.draaggy.prm.async;

import com.draaggy.prm.interfaces.MergeQueueRegistry;
import com.draaggy.prm.interfaces.PRManagingService;
import com.draaggy.prm.interfaces.UserStoryRegistry;
import com.draaggy.prm.model.PullRequestEvent;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MergeQueueManager {

    private MergeQueueRegistry mergeQueueRegistry;

    private UserStoryRegistry userStoryRegistry;

    private PRManagingService prManagingService;

    @Autowired
    public MergeQueueManager(MergeQueueRegistry mergeQueueRegistryImpl, UserStoryRegistry userStoryRegistryImpl, PRManagingService prManagingService) {
        this.mergeQueueRegistry = mergeQueueRegistryImpl;
        this.userStoryRegistry = userStoryRegistryImpl;
        this.prManagingService = prManagingService;
    }

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
                userStory = mergeQueueRegistry.getUserStoryToMerge();
                if (StringUtils.isEmpty(userStory)) {
                    System.out.println("Less work, nothing to merge, sleeping for 5 secs, ciao amigo");
                    try {
                        Thread.sleep(30000);
                        continue;
                    } catch (InterruptedException e) {
                        System.err.println("Queue processing failed for user story - " + userStory + " with reason - " + e.getMessage());
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                System.out.println("[Processing] User story: " + userStory + " | Thread: " + Thread.currentThread().getName());

                List<PullRequestEvent> prSet = userStoryRegistry.getPRSetForUserStory(userStory);

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
                mergeQueueRegistry.displayMergeQueue();
//                boolean allMerged = prManagingService.mergeSet(prSet);
                boolean allMerged = true; // Simulating successful merge for demonstration purposes
                if(allMerged) {
                    mergeQueueRegistry.removeUserStoryFromMergeQueue(userStory);
                } else {
                    mergeQueueRegistry.removeUserStoryFromMergeQueue(userStory);
                    mergeQueueRegistry.addUserStoryToMergeQueue(userStory);
                }
                mergeQueueRegistry.displayMergeQueue();
            }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                   processing.set(false);
                   System.out.println("[QueueManager] Processor stopped in thread: " + Thread.currentThread().getName());
        }
    }

    public void shutDown() {
        System.out.println("[Shutdown] Initiated.");
        running.set(false);
        System.out.println("[Queue] Processor shutting down...");
    }


    public boolean isProcessing() {
        return this.processing.get();
    }

}
