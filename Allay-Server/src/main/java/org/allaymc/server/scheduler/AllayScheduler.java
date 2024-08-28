package org.allaymc.server.scheduler;

import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.Task;
import org.allaymc.api.scheduler.TaskCreator;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Allay Project 2023/4/28
 *
 * @author daoge_cmd
 */
public class AllayScheduler implements Scheduler {

    protected static final Comparator<RunningTaskInfo> COMPARATOR = Comparator.comparing(RunningTaskInfo::getNextRunTick);
    protected static final int INITIAL_CAPACITY = 11;

    protected final ExecutorService asyncTaskExecutor;
    protected final PriorityBlockingQueue<RunningTaskInfo> queue = new PriorityBlockingQueue<>(INITIAL_CAPACITY, COMPARATOR);
    protected long tickCounter;
    protected AtomicInteger taskCount = new AtomicInteger(0);

    public AllayScheduler(ExecutorService asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Override
    public void tick() {
        tickCounter++;
        while (!queue.isEmpty() && queue.peek().getNextRunTick() <= tickCounter) {
            var taskInfo = queue.poll();
            // 1. Confirm validity
            if (taskInfo.isCancelled() || !taskInfo.getCreator().isValid()) {
                cancelTask(taskInfo);
                continue;
            }

            // 2. Run it
            if (taskInfo.isAsync()) asyncTaskExecutor.submit(() -> runTask(taskInfo));
            else runTask(taskInfo);
        }
    }

    @Override
    public void scheduleDelayed(TaskCreator creator, Task task, int delay, boolean async) {
        addTask(new RunningTaskInfo(creator, task, delay, 0, async));
    }

    @Override
    public void scheduleRepeating(TaskCreator creator, Task task, int period, boolean async) {
        addTask(new RunningTaskInfo(creator, task, 0, period, async));
    }

    @Override
    public long getTotalTicks() {
        return tickCounter;
    }

    @Override
    public int getRunningTaskCount() {
        return taskCount.get();
    }

    @Override
    public void stop() {
        queue.forEach(this::cancelTask);
        queue.clear();
    }

    protected void runTask(RunningTaskInfo info) {
        var task = info.getTask();
        try {
            info.setRunning(true);
            if (!task.onRun()) cancelTask(info);
        } catch (Exception exception) {
            task.onError(exception);
            cancelTask(info);
        } finally {
            info.setRunning(false);
            // Run only once
            if (!info.isRepeating()) cancelTask(info);
            else addTask(info, false);
        }
    }

    protected void cancelTask(RunningTaskInfo info) {
        if (info.isCancelled()) return;
        info.setCancelled(true);
        info.getTask().onCancel();
        taskCount.decrementAndGet();
    }

    protected void addTask(RunningTaskInfo taskInfo) {
        addTask(taskInfo, true);
    }

    protected void addTask(RunningTaskInfo taskInfo, boolean firstTime) {
        taskInfo.setNextRunTick(tickCounter + (taskInfo.isRepeating() ? taskInfo.getPeriod() : taskInfo.getDelay()));
        queue.offer(taskInfo);
        if (firstTime) taskCount.incrementAndGet();
    }
}
