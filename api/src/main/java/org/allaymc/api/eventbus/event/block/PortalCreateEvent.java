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

    /**
     * The dimension where the portal is being created.
     */
    protected Dimension dimension;
    /**
     * The positions of the portal blocks to place.
     */
    protected List<Vector3ic> portalBlocks;
    /**
     * The reason for the portal creation.
     */
    protected CreateReason reason;

    public PortalCreateEvent(Dimension dimension, List<Vector3ic> portalBlocks, CreateReason reason) {
        this.dimension = dimension;
        this.portalBlocks = portalBlocks;
        this.reason = reason;
    }

    /**
     * The reason for the portal creation.
     */
    public enum CreateReason {
        FIRE,
        NETHER_PAIR
    }
}
