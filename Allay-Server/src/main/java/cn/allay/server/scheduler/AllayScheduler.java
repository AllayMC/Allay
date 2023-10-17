package cn.allay.server.scheduler;

import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.scheduler.task.Task;
import cn.allay.api.server.Server;

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
    protected static final Comparator<RunningTaskInfo> COMPARATOR = Comparator.comparing(RunningTaskInfo::getNextRunTick).reversed();
    protected static final int INITIAL_CAPACITY = 11;

    protected final ExecutorService asyncTaskExecutor;
    protected final PriorityBlockingQueue<RunningTaskInfo> queue = new PriorityBlockingQueue<>(INITIAL_CAPACITY, COMPARATOR);
    protected long tickCounter;
    protected AtomicInteger taskCount = new AtomicInteger(0);

    public AllayScheduler() {
        this.asyncTaskExecutor = Server.getInstance().getVirtualThreadPool();
    }

    public AllayScheduler(ExecutorService asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Override
    public void tick() {
        tickCounter++;
        while (!queue.isEmpty() && queue.peek().getNextRunTick() <= tickCounter) {
            var taskInfo = queue.poll();
            var task = taskInfo.getTask();
            //1. Confirm validity
            if (taskInfo.isCancelled() || !task.getTaskCreator().isValid()) {
                cancelTask(taskInfo);
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
        queue.forEach(this::cancelTask);
        queue.clear();
    }

    protected void runTask(RunningTaskInfo info) {
        var task = info.getTask();
        try {
            info.setRunning(true);
            if (!task.onRun()) cancelTask(info);
        } catch (Throwable error) {
            task.onError(error);
            cancelTask(info);
        } finally {
            info.setRunning(false);
            //Run only once
            if (!info.isRepeating()) {
                cancelTask(info);
            } else {
                addTask(info, false);
            }
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
        if (taskInfo.isRepeating())
            taskInfo.setNextRunTick(tickCounter + taskInfo.getPeriod());
        else
            taskInfo.setNextRunTick(tickCounter + taskInfo.getDelay());
        queue.add(taskInfo);
        if (firstTime)
            taskCount.incrementAndGet();
    }
}
