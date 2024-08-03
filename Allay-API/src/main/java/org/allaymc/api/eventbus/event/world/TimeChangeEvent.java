package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;

import com.google.common.base.Preconditions;

import lombok.Getter;

/**
 * Allay Project 2024/08/03
 *
 * @author Dhaiven
 */
@Getter
public class TimeChangeEvent extends WorldEvent implements CancellableEvent {
    protected long oldTime;
    protected long newTime;
 
    public TimeChangeEvent(World world, long oldTime, long newTime) {
        super(world);
        this.oldTime = oldTime;
        setNewTime(newTime);
    }

    public void setNewTime(long time) {
        Preconditions.checkArgument(time < WorldData.TIME_DAY || time > WorldData.TIME_FULL, "Time must be between " + WorldData.TIME_DAY + " and " + WorldData.TIME_FULL);
        this.newTime = time;
    }
}