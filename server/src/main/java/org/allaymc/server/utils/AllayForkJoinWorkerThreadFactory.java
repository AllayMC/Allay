package org.allaymc.server.utils;

import org.allaymc.api.utils.AllayStringUtils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * @author daoge_cmd
 */
public class AllayForkJoinWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        var thread = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
        var tid = AllayStringUtils.fastSplit(thread.getName(), "-").getLast();
        thread.setName("AllayComputeThread-" + tid);
        return thread;
    }
    
}
