package cn.allay.scheduler.task;

import cn.allay.scheduler.taskcreator.TaskCreator;
import cn.allay.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
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
     * @param error The error that occurred
     */
    default void onError(Throwable error) {
        LOGGER.error("Exception while running task!", error);
    }

    default TaskCreator getTaskCreator() {
        return Server.getInstance();
    }
}
