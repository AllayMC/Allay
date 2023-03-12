package cn.allay.scheduler;

import cn.allay.api.AllayAPI;
import cn.allay.scheduler.task.Task;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 *
 * scheduler
 */
public interface Scheduler {

    SchedulerFactory FACTORY = AllayAPI.getInstance().getAPIInstance(SchedulerFactory.class);

    static Scheduler createScheduler() {
        return FACTORY.createScheduler();
    }

    void ticking();

    default void scheduleDelayed(Task task, int delay) {
        scheduleDelayed(task, delay, false);
    }

    void scheduleDelayed(Task task, int delay, boolean async);

    default void scheduleRepeating(Task task, int period) {
        scheduleDelayed(task, period, false);
    }

    void scheduleRepeating(Task task, int period, boolean async);

    long getTotalTicks();

    //TODO: delayed repeating

    interface SchedulerFactory {
        Scheduler createScheduler();
    }
}
