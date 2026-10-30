package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.joml.Vector3dc;

/**
 * {@code ProjectileHitEvent} represents a selected projectile collision after
 * a movement proposal is accepted. Cancellation suppresses deferred target
 * callbacks and projectile-specific impact handling. The selected hit point
 * can differ from the current projectile location when movement listeners
 * redirect the accepted proposal; the normal emitter supplies either a block
 * or a victim.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class ProjectileHitEvent extends EntityEvent implements CancellableEvent {

    /**
     * {@code hitPos} retains the selected world-space collision point without
     * copying it. Movement listeners can redirect the accepted location, so
     * this point need not match the current projectile coordinates.
     */
    protected Vector3dc hitPos;
    /**
     * {@code block} identifies the hit block, or {@code null} for an entity
     * impact in the normal projectile emitter.
     */
    protected Block block;
    /**
     * {@code victim} identifies the hit entity, or {@code null} for a block
     * impact in the normal projectile emitter.
     */
    protected Entity victim;

    /**
     * Creates an impact event retaining the supplied projectile, position, and
     * target references. The constructor does not enforce target exclusivity;
     * normal projectile physics supplies one of {@code victim} and {@code
     * block}.
     */
    public ProjectileHitEvent(EntityProjectile entity, Vector3dc hitPos, Entity victim, Block block) {
        super(entity);
        this.hitPos = hitPos;
        this.victim = victim;
        this.block = block;
    }

    /**
     * Returns the projectile entity retained as this impact event source.
     */
    @Override
    public EntityProjectile getEntity() {
        return (EntityProjectile) super.getEntity();
    }
}
