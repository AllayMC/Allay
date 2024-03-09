package org.allaymc.api.scheduler;

/**
 * Describes a task creator (eg: plugin) <br/>
 * Each time a task is executed, the creator of the task is first checked to be valid, and if not, the task is canceled <br/>
 * <p>
 * Allay Project 2023/3/12
 *
 * @author daoge_cmd
 */
public interface TaskCreator {
    /**
     * @return Whether this task creator is valid or not
     */
    boolean isValid();
}
