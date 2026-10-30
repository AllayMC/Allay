package org.allaymc.api.scheduler;

import org.allaymc.api.AllayAPI;

import java.util.concurrent.ExecutorService;

/**
 * {@code Scheduler} schedules callbacks against a counter advanced explicitly
 * by its owner.
 * Synchronous callbacks run on the thread calling {@link #tick()}, while
 * asynchronous callbacks use the supplied executor. Delays and periods count
 * scheduler ticks rather than elapsed time; creator validity is checked when a
 * due task is selected for execution.
 *
 * @author daoge_cmd
 */
public interface Scheduler {
    /**
     * {@code FACTORY} holds the runtime factory installed during API initialization for creating
     * schedulers with a caller-owned asynchronous executor.
     */
    AllayAPI.APIInstanceHolder<Factory> FACTORY = AllayAPI.APIInstanceHolder.create();

    /**
     * Creates a scheduler using the installed runtime factory and supplied
     * executor. The caller retains responsibility for the executor's lifetime;
     * shutting down the scheduler does not shut down that executor.
     *
     * @param asyncTaskExecutor the async task executor
     * @return the scheduler
     */
    static Scheduler createScheduler(ExecutorService asyncTaskExecutor) {
        return FACTORY.get().create(asyncTaskExecutor);
    }

    /**
     * Advances the counter once and dispatches tasks whose due tick has arrived.
     * The owner must serialize tick calls; synchronous callbacks execute before
     * this call returns, while submitted asynchronous callbacks may finish later.
     */
    void tick();

    /**
     * Cancels tasks currently waiting in the scheduler and clears its queue.
     * This does not interrupt or await callbacks already submitted to the
     * asynchronous executor, close that executor, or prevent later scheduling.
     */
    void shutdown();

    /**
     * Schedules a synchronous callback with no additional tick delay.
     * A callback queued between ticks is eligible on the next tick; one queued
     * while a tick is draining due work may execute during that same tick.
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
     * Schedules an asynchronous callback with no additional tick delay.
     * A subsequent due-task scan submits the callback to the executor, which
     * determines when it actually starts and finishes independently of ticking.
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
     * Schedules a task for one synchronous invocation after the supplied delay.
     * The creator is checked when the task becomes due, and either return value
     * from the callback ends this one-shot registration.
     *
     * @param creator the task creator
     * @param task    the task
     * @param delay   the delay in ticks
     */
    default void scheduleDelayed(TaskCreator creator, Task task, int delay) {
        scheduleDelayed(creator, task, delay, false);
    }

    /**
     * Schedules a runnable for one invocation on the ticking thread after the
     * supplied delay. The runnable has no repetition decision; normal completion
     * ends its registration and invokes the task cancellation lifecycle.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param delay    the delay in ticks
     */
    default void scheduleDelayed(TaskCreator creator, Runnable runnable, int delay) {
        scheduleDelayed(creator, runnable, delay, false);
    }

    /**
     * Adapts a runnable to a one-shot task using the requested execution mode.
     * Its return adapter ends the registration after normal completion; creator
     * invalidation can instead cancel it before execution begins.
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
     * Schedules one invocation relative to the current scheduler tick.
     * Zero or negative delays make the task eligible at the next due-task scan,
     * including the current tick when scheduling occurs inside a callback.
     * Asynchronous execution is submitted after the creator validity check.
     *
     * @param creator the task creator
     * @param task    the task
     * @param delay   the delay in ticks
     * @param async   whether to run the task asynchronously
     */
    void scheduleDelayed(TaskCreator creator, Task task, int delay, boolean async);

    /**
     * Schedules a task to repeat on the ticking thread after each positive
     * period. Returning {@code false} stops repetition; a nonpositive period
     * selects one-shot execution without an additional tick delay.
     *
     * @param creator the task creator
     * @param task    the task
     * @param period  the period in ticks
     */
    default void scheduleRepeating(TaskCreator creator, Task task, int period) {
        scheduleRepeating(creator, task, period, false);
    }

    /**
     * Schedules a runnable to repeat synchronously after each positive period.
     * The adapter keeps requesting repetition, so creator invalidation,
     * cancellation, or a callback exception ends it; nonpositive periods run once.
     *
     * @param creator  the task creator
     * @param runnable the runnable to execute
     * @param period   the period in ticks
     */
    default void scheduleRepeating(TaskCreator creator, Runnable runnable, int period) {
        scheduleRepeating(creator, runnable, period, false);
    }

    /**
     * Adapts a runnable to a repeating task in the requested execution mode.
     * Normal completion requests another invocation for positive periods, while
     * a nonpositive period makes the registration execute only once.
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
     * Schedules a task whose first invocation is due after the supplied positive
     * period. Later invocations are scheduled after completion using the current
     * scheduler tick, so asynchronous execution time can extend their spacing.
     * Nonpositive periods select one-shot execution with zero delay.
     *
     * @param creator the task creator
     * @param task    the task
     * @param period  the period in ticks
     * @param async   whether to run the task asynchronously
     */
    void scheduleRepeating(TaskCreator creator, Task task, int period, boolean async);

    /**
     * Schedules synchronous repetition after a separate delayed registration
     * step. The first callback therefore waits for both the delay and a positive
     * period; returning {@code false} ends subsequent repetition.
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
     * Schedules a runnable for synchronous repetition after delayed setup.
     * The initial callback waits for the delay and then a positive period;
     * normal completion continues repetition while the creator remains valid.
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
     * Adapts a runnable to repetition started by a delayed setup task.
     * Setup runs synchronously even when {@code async} is {@code true}; that flag applies
     * to the runnable invocations registered after the delay expires.
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
     * Registers a repeating task when a synchronous delayed setup task runs.
     * For positive periods, the first invocation is due one period after setup,
     * rather than at the end of the delay itself. The creator is checked for
     * both setup and subsequent invocations; {@code async} affects only the latter.
     *
     * @param creator the task creator
     * @param task    the task
     * @param delay   the delay in ticks
     * @param period  the period in ticks
     * @param async   whether to run the task asynchronously
     */
    default void scheduleDelayedRepeating(TaskCreator creator, Task task, int delay, int period, boolean async) {
        // Keep setup on the ticking thread: it registers a separate repeating
        // task, whose first due tick includes its own period after this delay.
        scheduleDelayed(creator, () -> {
            scheduleRepeating(creator, task, period, async);
            return false;
        }, delay);
    }

    /**
     * Returns the number of ticks advanced by this scheduler.
     *
     * @return the current tick
     */
    long getTicks();

    /**
     * Returns the number of registrations not yet removed by cancellation
     * bookkeeping. This includes waiting and executing callbacks, rather than
     * only callbacks currently occupying a thread; cancelled queue entries may
     * still await removal.
     *
     * @return the running task count
     */
    int getRunningTaskCount();

    /**
     * {@code Factory} creates runtime scheduler implementations for callers
     * supplying an asynchronous executor.
     */
    interface Factory {
        /**
         * Creates a scheduler whose asynchronous callbacks use the supplied
         * executor. The caller owns that executor and must keep it available
         * until submitted callbacks no longer need it.
         */
        Scheduler create(ExecutorService asyncTaskExecutor);
    }
}
