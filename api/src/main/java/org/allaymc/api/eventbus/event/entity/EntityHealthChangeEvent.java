package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code EntityHealthChangeEvent} carries the current health and a proposed
 * replacement for a living entity. The living component prepares its initial
 * clamped proposal before dispatch, then stores {@code newHealth} without
 * reapplying that clamp; cancellation leaves the existing health unchanged.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityHealthChangeEvent extends EntityEvent implements CancellableEvent {
    /**
     * Health value observed before the proposed health assignment.
     */
    protected float oldHealth;
    /**
     * {@code newHealth} holds the proposed replacement health. The living
     * component stores this listener-adjustable value after dispatch without
     * clamping it again to its normal range.
     */
    @Setter
    protected float newHealth;

    /**
     * Creates a health proposal with the existing and replacement health
     * values.
     */
    public EntityHealthChangeEvent(Entity entity, float oldHealth, float newHealth) {
        super(entity);
        this.oldHealth = oldHealth;
        this.newHealth = newHealth;
    }
}
