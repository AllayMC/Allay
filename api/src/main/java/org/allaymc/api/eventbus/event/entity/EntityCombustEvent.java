package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when an entity is set on fire.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityCombustEvent extends EntityEvent implements CancellableEvent {

    /**
     * The type of the combuster.
     */
    protected final CombusterType combusterType;
    /**
     * The block or entity that caused the combustion.
     */
    protected final Object combuster;

    /**
     * The number of ticks the entity should remain on fire.
     */
    @Setter
    protected int onFireTicks;

    public EntityCombustEvent(Entity entity, CombusterType combusterType, Object combuster, int onFireTicks) {
        super(entity);
        this.combusterType = combusterType;
        this.combuster = combuster;
        this.onFireTicks = onFireTicks;
    }

    /**
     * The type of source that caused the combustion.
     */
    public enum CombusterType {
        BLOCK,
        ENTITY
    }
}
