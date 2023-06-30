package cn.allay.api.scheduler;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.scheduler.task.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * scheduler
 * <p>
 * Allay Project 2023/3/12
 *
 * @author daoge_cmd
 */
public interface Scheduler {

    static Scheduler createScheduler() {
        return SchedulerFactory.FACTORY.get().createScheduler(Executors.newVirtualThreadPerTaskExecutor());
    }

    static Scheduler createScheduler(ExecutorService asyncTaskExecutor) {
        return SchedulerFactory.FACTORY.get().createScheduler(asyncTaskExecutor);
    }

    void ticking();

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

        Scheduler createScheduler(ExecutorService asyncTaskExecutor);
    }
}
