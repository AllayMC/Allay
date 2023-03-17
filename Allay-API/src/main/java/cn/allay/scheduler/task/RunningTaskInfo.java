package cn.allay.scheduler.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/12 <br>
 * Allay Project <br>
 *
 * Represents a running task that contains some run presets for this task
 */
@Getter
public final class RunningTaskInfo {
    private final Task task;
    private final int delay;
    private final int period;
    private final boolean async;

    @Setter
    private long lastRunTick;
    @Setter
    private long nextRunTick;
    @Setter
    private boolean stop;
    /**
     * This boolean indicates whether Task.onRun() is being calling <p/>
     * If async is true, the last run will be checked before each scheduler makes an asynchronous task call to prevent unexpected additional calls
     */
    @Setter
    private boolean running;

    @Builder
    public RunningTaskInfo(Task task, int delay, int period, boolean async) {
        this.task = task;
        this.delay = delay;
        this.period = period;
        this.async = async;
    }
}
