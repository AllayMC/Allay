package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.List;

/**
 * Called before portal blocks are placed to form a portal.
 * Cancelling this event prevents the portal from being created.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
@CallerThread(ThreadType.VIRTUAL)
public class PortalCreateEvent extends Event implements CancellableEvent {

    protected Dimension dimension;
    protected List<Vector3ic> portalBlocks;
    protected CreateReason reason;

    public PortalCreateEvent(Dimension dimension, List<Vector3ic> portalBlocks, CreateReason reason) {
        this.dimension = dimension;
        this.portalBlocks = portalBlocks;
        this.reason = reason;
    }

    public enum CreateReason {
        FIRE,
        NETHER_PAIR
    }
}
