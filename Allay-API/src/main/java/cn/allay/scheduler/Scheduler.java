package cn.allay.scheduler;

import cn.allay.api.AllayAPI;
import cn.allay.scheduler.task.RunningTaskInfo;
import cn.allay.scheduler.task.Task;

import java.util.Set;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 *
 * scheduler
 */
public interface Scheduler {

    static Scheduler createScheduler() {
        return SchedulerFactory.FACTORY.createScheduler();
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

    //TODO: delayed repeating

    long getTotalTicks();

    //TODO: The setter for the user is not turned off, there is a potential risk of misuse
    Set<RunningTaskInfo> getRunningTasks();

    interface SchedulerFactory {

        SchedulerFactory FACTORY = AllayAPI.getInstance().getAPIInstance(SchedulerFactory.class);

        Scheduler createScheduler();
    }
}
