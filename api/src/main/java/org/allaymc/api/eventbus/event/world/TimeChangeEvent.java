package org.allaymc.api.eventbus.event.world;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;

/**
 * {@code TimeChangeEvent} carries the previous time of day and a validated
 * proposed time for a world. Listeners may change {@code newTime} through
 * {@link #setNewTime(int)} or cancel the event before the world data setter
 * stores and broadcasts the result.
 *
 * @author Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class TimeChangeEvent extends WorldEvent implements CancellableEvent {
    /**
     * Previous time of day in ticks.
     */
    protected int oldTime;
    /**
     * Proposed time of day in ticks.
     */
    protected int newTime;

    /**
     * Creates a time proposal after validating the requested replacement time.
     * The new time must fall within {@code WorldData.TIME_DAY} through {@code
     * WorldData.TIME_FULL}, inclusive; an invalid replacement throws {@code
     * IllegalArgumentException}.
     */
    public TimeChangeEvent(World world, int oldTime, int newTime) {
        super(world);
        this.oldTime = oldTime;
        setNewTime(newTime);
    }

    /**
     * Replaces the proposed time of day after validating its range. Values
     * from {@code WorldData.TIME_DAY} through {@code WorldData.TIME_FULL},
     * inclusive, are accepted; other values throw {@code
     * IllegalArgumentException} before changing {@code newTime}.
     */
    public void setNewTime(int time) {
        Preconditions.checkArgument(time >= WorldData.TIME_DAY && time <= WorldData.TIME_FULL, "Time must be between " + WorldData.TIME_DAY + " and " + WorldData.TIME_FULL);
        this.newTime = time;
    }
}
