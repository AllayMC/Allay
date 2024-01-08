package org.allaymc.server.scheduler;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.scheduler.task.Task;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a running task that contains some run presets for this task
 * <p>
 * Allay Project 2023/3/12
 *
 * @author daoge_cmd
 */

public final class RunningTaskInfo {
    @Getter
    private final Task task;
    @Getter
    private final int delay;
    @Getter
    private final int period;
    @Getter
    private final boolean async;
    @Getter
    @Setter
    private long nextRunTick;
    private final AtomicBoolean cancelled = new AtomicBoolean(false);
    private final AtomicBoolean running = new AtomicBoolean(false);

    public boolean isCancelled() {
        return cancelled.get();
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled.set(cancelled);
    }

    public boolean isRunning() {
        return running.get();
    }

    public void setRunning(boolean running) {
        this.running.set(running);
    }

    public RunningTaskInfo(Task task, int delay, int period, boolean async) {
        this.task = task;
        this.delay = delay;
        this.period = period;
        this.async = async;
    }

    public boolean isRepeating() {
        return period > 0;
    }
}
