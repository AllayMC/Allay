package org.allaymc.api.scheduler;

/**
 * Describes a creator for scheduled tasks.
 * <p>
 * The task creator is responsible for determining whether a task is valid before it is executed.
 * Each time a task is scheduled, the validity of the task is checked.
 * If the creator returns {@code false}, indicating that the task is no longer valid, the task
 * will be canceled before execution.
 *
 * @author daoge_cmd
 */
public interface TaskCreator {
    /**
     * Checks if the task created by this {@link TaskCreator} is valid.
     *
     * <p>
     * This method is called before executing the task to ensure it should still run.
     * If it returns {@code false}, the task will be canceled and will not execute.
     * </p>
     *
     * @return {@code true} if the task is valid and can be executed, {@code false} otherwise.
     */
    boolean isValid();
}
