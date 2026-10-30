package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code EntityCombustEvent} carries a proposed fire duration and its block or
 * entity source. Contact handlers dispatch this event before assigning fire
 * ticks; cancellation prevents that assignment, and an accepted event supplies
 * the listener-adjusted {@code onFireTicks} value to the living component.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityCombustEvent extends EntityEvent implements CancellableEvent {

    /**
     * Category identifying the reported block or entity combustion source.
     */
    protected final CombusterType combusterType;
    /**
     * {@code combuster} retains the reported source object without conversion.
     * Callers use {@code combusterType} to interpret it as the block or entity
     * responsible for the attempted ignition.
     */
    protected final Object combuster;

    /**
     * {@code onFireTicks} holds the proposed burning duration in ticks.
     * Contact handlers read the listener-adjusted value only after accepting
     * this event's cancellation result.
     */
    @Setter
    protected int onFireTicks;

    /**
     * Creates a combustion proposal retaining its entity and source
     * references. The constructor records the duration without applying fire
     * or validating that {@code combuster} matches the supplied
     * {@code combusterType}.
     */
    public EntityCombustEvent(Entity entity, CombusterType combusterType, Object combuster, int onFireTicks) {
        super(entity);
        this.combusterType = combusterType;
        this.combuster = combuster;
        this.onFireTicks = onFireTicks;
    }

    /**
     * {@code CombusterType} identifies whether a combustion source is a block
     * or entity.
     */
    public enum CombusterType {
        /**
         * Block source for the attempted entity ignition.
         */
        BLOCK,
        /**
         * Entity source for the attempted entity ignition.
         */
        ENTITY
    }
}
