package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a firework rocket is about to explode.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class FireworkExplodeEvent extends EntityEvent implements CancellableEvent {
    /**
     * The force of the explosion. Bigger force would cause bigger damage to the affected entities.
     */
    protected double force;

    public FireworkExplodeEvent(EntityFireworksRocket firework, double force) {
        super(firework);
        this.force = force;
    }
}
