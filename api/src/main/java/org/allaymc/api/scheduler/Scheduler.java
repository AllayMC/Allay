package org.allaymc.api.scheduler;

import org.allaymc.api.AllayAPI;

import java.util.concurrent.ExecutorService;

/**
 * Scheduler is used to schedule tasks in a specific cycle.
 *
 * @author daoge_cmd
 */
public interface Scheduler {
    AllayAPI.APIInstanceHolder<Factory> FACTORY = AllayAPI.APIInstanceHolder.create();

    /**
     * Create a scheduler.
     *
     * @param asyncTaskExecutor the async task executor
     * @return the scheduler
     */
    static Scheduler createScheduler(ExecutorService asyncTaskExecutor) {
        return FACTORY.get().create(asyncTaskExecutor);
    }

    /**
     * Tick the scheduler.
     */
    void tick();

    /**
     * Shutdown the scheduler.
     */
    void shutdown();

    /**
     * Run a task later.
     * <p>
     * This method will run the task in the next tick.
     *
     * @param creator  the task creator
     * @param runnable the runnable
     */
    default void runLater(TaskCreator creator, Runnable runnable) {
        scheduleDelayed(creator, () -> {
            runnable.run();
            return true;
        }, 0);
    }

    /**
     * Run a task later asynchronously.
     * <p>
     * This method will run the task in the next tick.
     *
     * @param creator  the task creator
     * @param runnable the runnable
     */
    default void runLaterAsync(TaskCreator creator, Runnable runnable) {
        scheduleDelayed(creator, () -> {
            runnable.run();
            return true;
        }, 0, true);
    }

    /**
     * Schedule a delayed task.
     *
     * @param creator the task creator
     * @param task    the task
     * @param delay   the delay in ticks
     */
    default void scheduleDelayed(TaskCreator creator, Task task, int delay) {
        scheduleDelayed(creator, task, delay, false);
    }

    /**
     * Schedule a delayed task.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param delay    the delay in ticks
     */
    default void scheduleDelayed(TaskCreator creator, Runnable runnable, int delay) {
        scheduleDelayed(creator, runnable, delay, false);
    }

    /**
     * Schedule a delayed task.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param delay    the delay in ticks
     * @param async    whether to run the task asynchronously
     */
    default void scheduleDelayed(TaskCreator creator, Runnable runnable, int delay, boolean async) {
        scheduleDelayed(creator, () -> {
            runnable.run();
            return false;
        }, delay, async);
    }

    /**
     * Schedule a delayed task.
     *
     * @param creator the task creator
     * @param task    the task
     * @param delay   the delay in ticks
     * @param async   whether to run the task asynchronously
     */
    void scheduleDelayed(TaskCreator creator, Task task, int delay, boolean async);

    /**
     * Schedule a repeating task.
     *
     * @param creator the task creator
     * @param task    the task
     * @param period  the period in ticks
     */
    default void scheduleRepeating(TaskCreator creator, Task task, int period) {
        scheduleRepeating(creator, task, period, false);
    }

    /**
     * Schedule a repeating task.
     * <p>
     * The task will run indefinitely until the task creator becomes invalid.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param period   the period in ticks
     */
    default void scheduleRepeating(TaskCreator creator, Runnable runnable, int period) {
        scheduleRepeating(creator, runnable, period, false);
    }

    /**
     * Schedule a repeating task.
     * <p>
     * The task will run indefinitely until the task creator becomes invalid.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param period   the period in ticks
     * @param async    whether to run the task asynchronously
     */
    default void scheduleRepeating(TaskCreator creator, Runnable runnable, int period, boolean async) {
        scheduleRepeating(creator, () -> {
            runnable.run();
            return true;
        }, period, async);
    }

    /**
     * Schedule a repeating task.
     *
     * @param creator the task creator
     * @param task    the task
     * @param period  the period in ticks
     * @param async   whether to run the task asynchronously
     */
    void scheduleRepeating(TaskCreator creator, Task task, int period, boolean async);

    /**
     * Schedule a delayed repeating task.
     *
     * @param creator the task creator
     * @param task    the task
     * @param delay   the delay in ticks
     * @param period  the period in ticks
     */
    default void scheduleDelayedRepeating(TaskCreator creator, Task task, int delay, int period) {
        scheduleDelayedRepeating(creator, task, delay, period, false);
    }

    /**
     * Schedule a delayed repeating task.
     * <p>
     * The task will run indefinitely until the task creator becomes invalid.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param delay    the delay in ticks
     * @param period   the period in ticks
     */
    default void scheduleDelayedRepeating(TaskCreator creator, Runnable runnable, int delay, int period) {
        scheduleDelayedRepeating(creator, runnable, delay, period, false);
    }

    /**
     * Schedule a delayed repeating task.
     * <p>
     * The task will run indefinitely until the task creator becomes invalid.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param delay    the delay in ticks
     * @param period   the period in ticks
     * @param async    whether to run the task asynchronously
     */
    default void scheduleDelayedRepeating(TaskCreator creator, Runnable runnable, int delay, int period, boolean async) {
        scheduleDelayedRepeating(creator, () -> {
            runnable.run();
            return true;
        }, delay, period, async);
    }

    /**
     * Schedule a delayed repeating task.
     *
     * @param creator the task creator
     * @param task    the task
     * @param delay   the delay in ticks
     * @param period  the period in ticks
     * @param async   whether to run the task asynchronously
     */
    default void scheduleDelayedRepeating(TaskCreator creator, Task task, int delay, int period, boolean async) {
        scheduleDelayed(creator, () -> {
            scheduleRepeating(creator, task, period, async);
            return false;
        }, delay);
    }

    /**
     * Get the current tick.
     *
     * @return the current tick
     */
    long getTicks();

    /**
     * Get the running task count.
     *
     * @return the running task count
     */
    int getRunningTaskCount();

    interface Factory {
        Scheduler create(ExecutorService asyncTaskExecutor);
    }
}
