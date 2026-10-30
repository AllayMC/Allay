package org.allaymc.server.scheduler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.allaymc.api.scheduler.Task;
import org.allaymc.api.scheduler.TaskCreator;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * {@code RunningTaskInfo} stores one scheduler registration's callback, timing
 * settings, and lifecycle flags. The generated constructor captures the creator,
 * task, delay, period,
 * and execution mode; the scheduler later assigns the queue deadline.
 * {@code cancelled} and {@code running} expose individual status changes
 * independently. Their setters do not coordinate scheduler cancellation
 * callbacks or registration-count updates.
 *
 * @author daoge_cmd
 */
@Getter
@RequiredArgsConstructor
public final class RunningTaskInfo {
    /** Registration validity check provider. */
    private final TaskCreator creator;
    /** Registration lifecycle callback target. */
    private final Task task;
    /**
     * {@code delay} counts scheduler ticks before execution when {@code period}
     * is not positive and the registration therefore does not repeat.
     */
    private final int delay;
    /**
     * {@code period} counts ticks after each completed invocation. Positive
     * values enable repetition, while other values select one-shot execution.
     */
    private final int period;
    /** Asynchronous executor submission flag. */
    private final boolean async;

    /**
     * {@code cancelled} records cancellation independently of {@code running}.
     * Setting it neither interrupts a callback nor performs scheduler count
     * bookkeeping.
     */
    @Getter(AccessLevel.NONE)
    private final AtomicBoolean cancelled = new AtomicBoolean(false);
    /**
     * {@code running} records callback execution independently of cancellation.
     * The scheduler clears it in finalization even when a callback or lifecycle
     * hook throws.
     */
    @Getter(AccessLevel.NONE)
    private final AtomicBoolean running = new AtomicBoolean(false);

    /**
     * {@code nextRunTick} holds the absolute scheduler tick used as the queue
     * ordering key. Callers update it before insertion rather than while this
     * entry is queued.
     */
    @Setter
    private long nextRunTick;

    /**
     * Returns whether this registration's cancellation flag is currently set.
     */
    public boolean isCancelled() {
        return cancelled.get();
    }

    /**
     * Replaces the cancellation flag without invoking any task lifecycle hook.
     * Callers performing cancellation must separately coordinate hook delivery
     * and registration counting; this atomic write alone supplies neither operation.
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled.set(cancelled);
    }

    /**
     * Returns whether the scheduler currently marks this callback as running.
     */
    public boolean isRunning() {
        return running.get();
    }

    /**
     * Replaces the status flag describing execution of this callback.
     */
    public void setRunning(boolean running) {
        this.running.set(running);
    }

    /**
     * Returns whether the configured period is positive and enables repetition.
     * Zero and negative periods classify this registration as one-shot work,
     * regardless of which scheduling overload originally created it.
     */
    public boolean isRepeating() {
        return period > 0;
    }
}
