package cn.allay.scheduler;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.scheduler.task.Task;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 * <p>
 * scheduler
 */
public interface Scheduler {

    static Scheduler createScheduler() {
        return SchedulerFactory.FACTORY.get().createScheduler();
    }

    void ticking();

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

        ApiInstanceHolder<SchedulerFactory> FACTORY = ApiInstanceHolder.create();

        Scheduler createScheduler();
    }
}
