package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class ProjectileLaunchEvent extends EntityEvent implements CancellableEvent {

    /**
     * The entity that shoot this projectile.
     */
    protected Entity shooter;

    public ProjectileLaunchEvent(EntityProjectile entity, Entity shooter) {
        super(entity);
        this.shooter = shooter;
    }

    @Override
    public EntityProjectile getEntity() {
        return (EntityProjectile) super.getEntity();
    }
}
