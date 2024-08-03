package org.allaymc.api.eventbus.event.world;

import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;

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
        this.newTime = newTime;
    }
}