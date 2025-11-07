package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ProjectileLaunchEvent extends EntityEvent implements CancellableEvent {

    /**
     * The entity that shoot this projectile.
     */
    protected Entity shooter;
    @Setter
    protected double throwForce;

    public ProjectileLaunchEvent(EntityProjectile entity, Entity shooter, double throwForce) {
        super(entity);
        this.shooter = shooter;
        this.throwForce = throwForce;
    }

    @Override
    public EntityProjectile getEntity() {
        return (EntityProjectile) super.getEntity();
    }
}
