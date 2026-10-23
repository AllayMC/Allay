package org.allaymc.api.scheduler;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@code Task} defines a scheduled callback together with cancellation and
 * failure hooks.
 * A repeating callback decides whether another invocation is requested, while
 * a delayed callback completes after one invocation. Hooks execute on the
 * thread performing the corresponding lifecycle operation and should not throw.
 *
 * @author daoge_cmd
 */
public interface Task {

    /**
     * {@code LOGGER} reports callback failures when a task retains the default error handler
     * instead of supplying its own recovery or logging behavior.
     */
    Logger LOGGER = LoggerFactory.getLogger(Task.class);

    /**
     * Runs the scheduled callback and decides whether repetition should
     * continue. Returning {@code false} requests cancellation immediately after
     * the callback; a one-shot registration is cancelled after either result.
     * Asynchronous tasks execute this method on their configured executor.
     *
     * @return {@code false} if the task should be canceled.
     */
    @ApiStatus.OverrideOnly
    boolean onRun();

    /**
     * Handles cancellation or normal completion of a one-shot registration.
     * The default implementation does nothing; overrides should return normally
     * because scheduler bookkeeping follows this hook. Cancellation does not
     * imply that an asynchronous callback was interrupted.
     */
    @ApiStatus.OverrideOnly
    default void onCancel() {
    }

    /**
     * Reports an exception caught while the scheduler invokes this task.
     * The default implementation logs the failure; overrides should return
     * normally so cancellation can proceed. The server scheduler catches
     * {@link Exception}, rather than every possible {@link Throwable}.
     *
     * @param error The error that occurred
     */
    @ApiStatus.OverrideOnly
    default void onError(Throwable error) {
        LOGGER.error("Exception while running task!", error);
    }
}
