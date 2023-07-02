package cn.allay.server.scheduler;

import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.scheduler.task.Task;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Allay Project 2023/4/28
 *
 * @author daoge_cmd
 */
public class AllayScheduler implements Scheduler {

    protected static final Comparator<RunningTaskInfo> COMPARATOR =
            Comparator
                    .comparing(RunningTaskInfo::getNextRunTick)
                    .reversed();
    protected static final int INITIAL_CAPACITY = 11;

    protected final ExecutorService asyncTaskExecutor;
    protected final PriorityBlockingQueue<RunningTaskInfo> queue = new PriorityBlockingQueue<>(INITIAL_CAPACITY, COMPARATOR);
    protected long tickCounter;
    protected AtomicInteger taskCount = new AtomicInteger(0);

    public AllayScheduler(@Nullable ExecutorService asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor != null ? asyncTaskExecutor : Executors.newVirtualThreadPerTaskExecutor();
    }

    @Override
    public void tick() {
        tickCounter++;
        while (!queue.isEmpty() && queue.peek().getNextRunTick() <= tickCounter) {
            var taskInfo = queue.poll();
            var task = taskInfo.getTask();
            //1. Confirm validity
            if (taskInfo.isCancelled() || !task.getTaskCreator().isValid()) {
                cancelTask(taskInfo, task);
                continue;
            }

            //2. Run it
            if (taskInfo.isAsync())
                asyncTaskExecutor.submit(() -> runTask(taskInfo));
            else
                runTask(taskInfo);
        }
    }

    @Override
    public void scheduleDelayed(Task task, int delay, boolean async) {
        var taskInfo = new RunningTaskInfo(task, delay, 0, async);
        addTask(taskInfo);
    }

    @Override
    public void scheduleRepeating(Task task, int period, boolean async) {
        var taskInfo = new RunningTaskInfo(task, 0, period, async);
        addTask(taskInfo);
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
        queue.forEach(entry -> {
            var task = entry.getTask();
            cancelTask(entry, task);
        });
        queue.clear();
    }

    protected void runTask(RunningTaskInfo info) {
        var task = info.getTask();
        try {
            info.setRunning(true);
            if (!task.onRun()) cancelTask(info, task);
        } catch (Throwable error) {
            task.onError(error);
            cancelTask(info, task);
        } finally {
            info.setRunning(false);
            //Run only once
            if (!info.isRepeating()) {
                cancelTask(info, task);
            } else {
                addTask(info, false);
            }
        }
    }

    protected void cancelTask(RunningTaskInfo info, Task task) {
        if (info.isCancelled()) return;
        info.setCancelled(true);
        task.onCancel();
        taskCount.decrementAndGet();
    }

    protected void addTask(RunningTaskInfo taskInfo) {
        addTask(taskInfo, true);
    }

    protected void addTask(RunningTaskInfo taskInfo, boolean firstTime) {
        if (taskInfo.isRepeating())
            taskInfo.setNextRunTick(tickCounter + taskInfo.getPeriod());
        else
            taskInfo.setNextRunTick(tickCounter + taskInfo.getDelay());
        queue.add(taskInfo);
        if (firstTime)
            taskCount.incrementAndGet();
    }
}
