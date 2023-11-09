package org.allaymc.api.scheduler;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.scheduler.task.Task;

import javax.annotation.Nullable;
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

    default void scheduleDelayed(Task task, int delay) {
        scheduleDelayed(task, delay, false);
    }

    void scheduleDelayed(Task task, int delay, boolean async);

    default void scheduleRepeating(Task task, int period) {
        scheduleRepeating(task, period, false);
    }

    void scheduleRepeating(Task task, int period, boolean async);

    //TODO: delayed repeating

    long getTotalTicks();

    int getRunningTaskCount();

    interface SchedulerFactory {

        ApiInstanceHolder<SchedulerFactory> FACTORY = ApiInstanceHolder.of();

        Scheduler createScheduler(@Nullable ExecutorService asyncTaskExecutor);
    }
}
