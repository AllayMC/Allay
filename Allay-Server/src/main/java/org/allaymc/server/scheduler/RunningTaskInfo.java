package org.allaymc.server.scheduler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.allaymc.api.scheduler.Task;
import org.allaymc.api.scheduler.TaskCreator;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a running task that contains some run presets for this task
 * <p>
 * Allay Project 2023/3/12
 *
 * @author daoge_cmd
 */
@Getter
@RequiredArgsConstructor
public final class RunningTaskInfo {
    private final TaskCreator creator;
    private final Task task;
    private final int delay;
    private final int period;
    private final boolean async;

    @Getter(AccessLevel.NONE)
    private final AtomicBoolean cancelled = new AtomicBoolean(false);
    @Getter(AccessLevel.NONE)
    private final AtomicBoolean running = new AtomicBoolean(false);

    @Setter
    private long nextRunTick;

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

    public boolean isRepeating() {
        return period > 0;
    }
}
