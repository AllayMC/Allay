package cn.allay.scheduler;

import cn.allay.scheduler.task.RunningTaskInfo;
import cn.allay.scheduler.task.Task;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 */
public class AllayScheduler implements Scheduler {

    protected final ExecutorService asyncTaskExecutor = getExecutorService();
    protected Map<RunningTaskInfo, Task> runningTasks = new ConcurrentHashMap<>();
    protected long tickCounter = 0;

    @Override
    public void ticking() {
        tickCounter++;
        for (var iterator = runningTasks.entrySet().iterator(); iterator.hasNext(); ) {
            var entry = iterator.next();
            var info = entry.getKey();
            if (info.isStop()) {
                iterator.remove();
                continue;
            }
            var task = entry.getValue();
            if (info.getNextRunTick() <= tickCounter) {
                if (info.getPeriod() > 0)
                    info.setNextRunTick(tickCounter + info.getPeriod());
                if (info.isAsync())
                    asyncTaskExecutor.submit(() -> runTask(task, info));
                else runTask(task, info);
            }
        }
    }

    @Override
    public void scheduleDelayed(Task task, int delay, boolean async) {
        if (delay <= 0)
            throw new IllegalArgumentException("Delay must be greater than 0! (delay=" + delay + ")");
        var info = new RunningTaskInfo(task, delay, 0, async);
        info.setNextRunTick(tickCounter + delay);
        runningTasks.put(info, task);
    }

    @Override
    public void scheduleRepeating(Task task, int period, boolean async) {
        if (period <= 0)
            throw new IllegalArgumentException("Period must be greater than 0! (period=" + period + ")");
        var info = new RunningTaskInfo(task, 0, period, async);
        runningTasks.put(info, task);
    }

    @Override
    public long getTotalTicks() {
        return tickCounter;
    }

    @Override
    public Set<RunningTaskInfo> getRunningTasks() {
        return runningTasks.keySet();
    }

    protected void runTask(Task task, RunningTaskInfo info) {
        try {
            if (!task.onRun()) {
                task.onCancel();
                info.setStop(true);
            }
        } catch (Throwable error) {
            task.onError(error);
        }
        if (info.getPeriod() <= 0)
            info.setStop(true);
    }

    protected ExecutorService getExecutorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
