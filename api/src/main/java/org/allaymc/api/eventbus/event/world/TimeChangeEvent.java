package org.allaymc.api.eventbus.event.world;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;

/**
 * Called when the world time changes.
 *
 * @author Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class TimeChangeEvent extends WorldEvent implements CancellableEvent {
    protected int oldTime;
    protected int newTime;

    public TimeChangeEvent(World world, int oldTime, int newTime) {
        super(world);
        this.oldTime = oldTime;
        setNewTime(newTime);
    }

    public void setNewTime(int time) {
        Preconditions.checkArgument(time >= WorldData.TIME_DAY && time <= WorldData.TIME_FULL, "Time must be between " + WorldData.TIME_DAY + " and " + WorldData.TIME_FULL);
        this.newTime = time;
    }
}