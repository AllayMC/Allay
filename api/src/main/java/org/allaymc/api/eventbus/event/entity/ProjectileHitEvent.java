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
 * Called when a projectile hits a block or an entity.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class ProjectileHitEvent extends EntityEvent implements CancellableEvent {

    protected Vector3dc hitPos;
    /**
     * The block being hit, can be {@code null}.
     */
    protected Block block;
    /**
     * The entity being hit, can be {@code null}.
     */
    protected Entity victim;

    public ProjectileHitEvent(EntityProjectile entity, Vector3dc hitPos, Entity victim, Block block) {
        super(entity);
        this.hitPos = hitPos;
        this.victim = victim;
        this.block = block;
    }

    @Override
    public EntityProjectile getEntity() {
        return (EntityProjectile) super.getEntity();
    }
}
