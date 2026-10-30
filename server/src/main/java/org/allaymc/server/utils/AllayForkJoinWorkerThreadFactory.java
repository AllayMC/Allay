package org.allaymc.server.utils;

import org.allaymc.api.utils.AllayStringUtils;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * {@code AllayForkJoinWorkerThreadFactory} creates pool workers with the server's compute-thread
 * name prefix. The JDK's default factory supplies each worker and pool association, and the
 * factory retains the final numeric name segment to distinguish workers in diagnostics.
 *
 * @author daoge_cmd
 */
public class AllayForkJoinWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    /**
     * Creates a default pool worker and applies the Allay compute-thread name.
     */
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        var thread = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
        var tid = AllayStringUtils.fastSplit(thread.getName(), "-").getLast();
        thread.setName("AllayComputeThread-" + tid);
        return thread;
    }

}
