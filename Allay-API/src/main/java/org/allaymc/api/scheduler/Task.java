package org.allaymc.api.scheduler;

import org.allaymc.api.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allay Project 2023/3/12
 *
 * @author daoge_cmd
 */
public interface Task {

    Logger LOGGER = LoggerFactory.getLogger(Task.class);

    /**
     * @return If false is returned, the Task will be canceled
     */
    boolean onRun();

    /**
     * Called when the task is canceled
     */
    default void onCancel() {

    }

    /**
     * Called when an error occurs while running the task
     *
     * @param error The error that occurred
     */
    default void onError(Throwable error) {
        LOGGER.error("Exception while running task!", error);
    }

    default TaskCreator getTaskCreator() {
        return Server.getInstance();
    }
}
