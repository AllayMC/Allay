package cn.allay.server.scheduler;

import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.scheduler.task.Task;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Allay Project 2023/4/28
 *
 * @author daoge_cmd
 */
public class AllayScheduler implements Scheduler {

    protected static final Comparator<RunningTaskInfo> COMPARATOR = Comparator.comparing(RunningTaskInfo::getNextRunTick).reversed();
    protected final ExecutorService asyncTaskExecutor = getExecutorService();
    protected final PriorityBlockingQueue<RunningTaskInfo> queue = new PriorityBlockingQueue<>(11, COMPARATOR);
    protected long tickCounter;

    @Override
    public void ticking() {
        tickCounter++;
        while (!this.queue.isEmpty() && this.queue.peek().getNextRunTick() <= tickCounter) {
            var taskInfo = this.queue.poll();
            Task task = taskInfo.getTask();
            //1. Confirm validity
            if (taskInfo.isCancelled() || !task.getTaskCreator().isValid()) {
                taskInfo.setCancelled(true);
                task.onCancel();
                continue;
            }

            //2. Run it
            if (taskInfo.isAsync()) asyncTaskExecutor.submit(() -> runTask(taskInfo));
            else runTask(taskInfo);
        }
    }

    @Override
    public void scheduleDelayed(Task task, int delay, boolean async) {
        var taskInfo = new RunningTaskInfo(task, delay, 0, async);
        this.addInQueue(taskInfo);
    }

    @Override
    public void scheduleRepeating(Task task, int period, boolean async) {
        var taskInfo = new RunningTaskInfo(task, 0, period, async);
        this.addInQueue(taskInfo);
    }

    @Override
    public long getTotalTicks() {
        return tickCounter;
    }

    @Override
    public int getRunningTaskCount() {
        return queue.size();
    }

    @Override
    public void stop() {
        this.queue.forEach(entry -> {
            entry.setCancelled(true);
            entry.getTask().onCancel();
        });
        this.queue.clear();
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
                addInQueue(info);
            }
        }
    }

    protected void cancelTask(RunningTaskInfo info, Task task) {
        info.setCancelled(true);
        task.onCancel();
    }

    protected void addInQueue(RunningTaskInfo taskInfo) {
        if (taskInfo.isRepeating()) taskInfo.setNextRunTick(tickCounter + taskInfo.getPeriod());
        else taskInfo.setNextRunTick(tickCounter + taskInfo.getDelay());
        queue.add(taskInfo);
    }

    protected ExecutorService getExecutorService() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
