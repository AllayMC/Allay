package org.allaymc.api.eventbus.event.entity;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when an entity is about to be teleported by a portal.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class EntityPortalEnterEvent extends EntityEvent implements CancellableEvent {

    protected PortalType portalType;

    public EntityPortalEnterEvent(Entity entity, PortalType portalType) {
        super(entity);
        this.portalType = portalType;
    }

    public enum PortalType {
        NETHER,
        END
    }
}
