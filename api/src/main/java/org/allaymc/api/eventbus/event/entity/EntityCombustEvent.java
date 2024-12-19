package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@Getter
public class EntityCombustEvent extends EntityEvent implements CancellableEvent {

    protected final CombusterType combusterType;
    protected final Object combuster;

    @Setter
    protected int onFireTicks;

    public EntityCombustEvent(Entity entity, CombusterType combusterType, Object combuster, int onFireTicks) {
        super(entity);
        this.combusterType = combusterType;
        this.combuster = combuster;
        this.onFireTicks = onFireTicks;
    }

    public enum CombusterType {
        BLOCK,
        ENTITY
    }
}
