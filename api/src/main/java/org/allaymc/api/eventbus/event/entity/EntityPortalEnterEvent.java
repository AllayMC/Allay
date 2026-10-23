package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code EntityPortalEnterEvent} carries a portal category before the entity
 * starts a portal teleport. Nether and end portal handlers consult its
 * cancellation result before starting their corresponding transfer work,
 * allowing listeners to block that attempt without changing the reported
 * portal category.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityPortalEnterEvent extends EntityEvent implements CancellableEvent {

    /**
     * Portal category associated with the proposed transfer attempt.
     */
    protected PortalType portalType;

    /**
     * Creates a portal-entry proposal with the entity and portal category.
     */
    public EntityPortalEnterEvent(Entity entity, PortalType portalType) {
        super(entity);
        this.portalType = portalType;
    }

    /**
     * {@code PortalType} identifies the portal family initiating an entity
     * transfer.
     */
    public enum PortalType {
        /**
         * Nether portal entry and its linked dimension transfer.
         */
        NETHER,
        /**
         * End portal entry and its linked dimension transfer.
         */
        END
    }
}
