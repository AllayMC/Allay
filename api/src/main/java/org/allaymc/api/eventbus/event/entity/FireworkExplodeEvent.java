package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code FireworkExplodeEvent} exposes a cancellable damage phase for a
 * firework rocket blast. The rocket emits its action and sounds before
 * dispatch, so cancellation suppresses damage while the lifetime handler still
 * removes the rocket.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class FireworkExplodeEvent extends EntityEvent implements CancellableEvent {
    /**
     * {@code force} scales distance-attenuated damage to nearby living
     * entities. Handlers can replace this value before the rocket computes
     * each affected target damage.
     */
    protected double force;

    /**
     * Creates a damage-phase event for the supplied rocket and blast force.
     * The constructor stores the force without range validation, and handlers
     * may change it before damage calculation.
     */
    public FireworkExplodeEvent(EntityFireworksRocket firework, double force) {
        super(firework);
        this.force = force;
    }
}
