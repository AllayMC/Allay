package org.allaymc.api.scheduler;

/**
 * {@code TaskCreator} supplies the lifecycle validity check for tasks belonging
 * to one creator.
 * <p>
 * The task creator is responsible for determining whether a task is valid before it is executed.
 * Each time a due task is selected, the validity of its creator is checked.
 * If the creator returns {@code false}, indicating that the task is no longer valid, the task
 * will be canceled before dispatch. An asynchronous task can already be
 * submitted when the creator later becomes invalid; invalidation does not
 * interrupt that submitted callback.
 *
 * @author daoge_cmd
 */
public interface TaskCreator {
    /**
     * Returns whether this creator currently permits its scheduled tasks to run.
     *
     * <p>
     * The scheduler calls this method on the ticking thread before dispatching
     * a due task. Returning {@code false} cancels that registration; a later
     * validity change does not retract an asynchronous callback already submitted.
     * </p>
     *
     * @return {@code true} if the task is valid and can be executed, {@code false} otherwise.
     */
    boolean isValid();
}
