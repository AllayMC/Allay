package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.Explosion;

/**
 * Called when an entity is about to explode. This event is used by some entities like tnt and creeper.
 * <p>
 * Notes that firework does not use this event, but use {@link FireworkExplodeEvent} instead. That's
 * because firework does not use the {@link Explosion} class.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class EntityExplodeEvent extends EntityEvent implements CancellableEvent {
    /**
     * The explosion.
     */
    protected Explosion explosion;

    public EntityExplodeEvent(Entity entity, Explosion explosion) {
        super(entity);
        this.explosion = explosion;
    }
}
