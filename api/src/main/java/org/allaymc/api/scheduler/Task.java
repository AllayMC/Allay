package org.allaymc.api.scheduler;

import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author daoge_cmd
 */
public interface Task {

    Logger LOGGER = LoggerFactory.getLogger(Task.class);

    /**
     * Called when the task is run.
     * <p>
     * The return value only have effect when the task is a repeating task,
     * and if the task is a delayed task, the return value will be ignored.
     *
     * @return {@code false} if the task should be canceled.
     */
    @ApiStatus.OverrideOnly
    boolean onRun();

    /**
     * Called when the task is canceled.
     */
    @ApiStatus.OverrideOnly
    default void onCancel() {}

    /**
     * Called when an error occurs while running the task.
     *
     * @param error The error that occurred.
     */
    @ApiStatus.OverrideOnly
    default void onError(Throwable error) {
        LOGGER.error("Exception while running task!", error);
    }
}
