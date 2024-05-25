package org.allaymc.api.scheduler;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.server.Server;

import java.util.concurrent.ExecutorService;

/**
 * scheduler
 * <p>
 * Allay Project 2023/3/12
 *
 * @author daoge_cmd
 */
public interface Scheduler {

    static Scheduler createScheduler() {
        return SchedulerFactory.FACTORY.get().createScheduler(null);
    }

    static Scheduler createScheduler(ExecutorService asyncTaskExecutor) {
        return SchedulerFactory.FACTORY.get().createScheduler(asyncTaskExecutor);
    }

    void tick();

    void stop();

    default void runLater(TaskCreator creator, Runnable runnable) {
        scheduleDelayed(creator, () -> {
            runnable.run();
            return true;
        }, 0);
    }

    default void scheduleDelayed(TaskCreator creator, Task task, int delay) {
        scheduleDelayed(creator, task, delay, false);
    }

    void scheduleDelayed(TaskCreator creator, Task task, int delay, boolean async);

    default void scheduleRepeating(TaskCreator creator, Task task, int period) {
        scheduleRepeating(creator, task, period, false);
    }

    void scheduleRepeating(TaskCreator creator, Task task, int period, boolean async);

    //TODO: delayed repeating

    long getTotalTicks();

    int getRunningTaskCount();

    interface SchedulerFactory {

        ApiInstanceHolder<SchedulerFactory> FACTORY = ApiInstanceHolder.create();

        Scheduler createScheduler(ExecutorService asyncTaskExecutor);
    }
}
