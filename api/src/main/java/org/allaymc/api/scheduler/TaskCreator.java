package org.allaymc.api.scheduler;

/**
 * Describes a task creator.
 * <p>
 * Each time a task is executed, the creator of the task is first checked to be valid, and if not, the task is canceled.
 *
 * @author daoge_cmd
 */
public interface TaskCreator {
    /**
     * @return {@code true} if the task is valid, {@code false} otherwise.
     */
    boolean isValid();
}
