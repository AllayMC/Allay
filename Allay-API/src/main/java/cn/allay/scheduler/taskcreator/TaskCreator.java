package cn.allay.scheduler.taskcreator;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 *
 * Describes a task creator (eg: plugin) <br/>
 * Each time a task is executed, the creator of the task is first checked to be valid, and if not, the task is canceled <br/>
 */
public interface TaskCreator {
    /**
     * @return Whether this task creator is valid or not
     */
    boolean isValid();
}
