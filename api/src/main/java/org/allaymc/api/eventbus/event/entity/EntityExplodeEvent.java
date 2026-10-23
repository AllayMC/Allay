package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.explosion.Explosion;

/**
 * {@code EntityExplodeEvent} exposes a cancellable entity explosion before its
 * execution. TNT and ender crystal handlers pass their configured {@link
 * Explosion} through this event, while firework damage uses {@link
 * FireworkExplodeEvent} because rockets implement their blast separately.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class EntityExplodeEvent extends EntityEvent implements CancellableEvent {
    /**
     * {@code explosion} retains the mutable explosion supplied by the emitter.
     * Current TNT and crystal emitters execute their original reference, so
     * handlers adjust that object rather than replace it through the setter.
     */
    protected Explosion explosion;

    /**
     * Creates an event retaining the supplied entity and explosion references
     * without copying either object. The emitter decides whether cancellation
     * prevents entity removal and execution of the configured explosion.
     */
    public EntityExplodeEvent(Entity entity, Explosion explosion) {
        super(entity);
        this.explosion = explosion;
    }
}
