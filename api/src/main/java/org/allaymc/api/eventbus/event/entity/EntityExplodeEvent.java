package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.Explosion;

/**
 * Called when an entity is about to explode, used by tnt and creeper for example.
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
