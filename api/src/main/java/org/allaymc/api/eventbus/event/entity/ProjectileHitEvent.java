package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class ProjectileHitEvent extends EntityEvent implements CancellableEvent {

    /**
     * The block being hit, can be {@code null}.
     */
    protected BlockStateWithPos block;
    /**
     * The entity being hit, can be {@code null}.
     */
    protected Entity victim;

    public ProjectileHitEvent(EntityProjectile entity, Entity victim, BlockStateWithPos block) {
        super(entity);
        this.victim = victim;
        this.block = block;
    }

    @Override
    public EntityProjectile getEntity() {
        return (EntityProjectile) super.getEntity();
    }
}
