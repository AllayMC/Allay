package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code ProjectileLaunchEvent} represents a cancellable request to launch a
 * projectile for a shooter. Item launch handlers dispatch it before adding the
 * projectile to a dimension; handlers can change {@code throwForce}, whose use
 * depends on the emitting launcher.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class ProjectileLaunchEvent extends EntityEvent implements CancellableEvent {

    /**
     * {@code shooter} retains the entity supplied by the launcher. The event
     * stores this reference independently from the projectile component
     * shooter assignment.
     */
    protected Entity shooter;
    /**
     * {@code throwForce} carries the launcher-specific force value. Generic
     * projectile item handling reads the modified value when computing initial
     * motion after the event succeeds.
     */
    @Setter
    protected double throwForce;

    /**
     * Creates a launch event retaining the projectile and shooter references
     * with the supplied force. The event performs no launch itself, and the
     * emitting item handler interprets cancellation and force changes.
     */
    public ProjectileLaunchEvent(EntityProjectile entity, Entity shooter, double throwForce) {
        super(entity);
        this.shooter = shooter;
        this.throwForce = throwForce;
    }

    /**
     * Returns the projectile entity retained as this launch event source.
     */
    @Override
    public EntityProjectile getEntity() {
        return (EntityProjectile) super.getEntity();
    }
}
