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
 * {@code PortalCreateEvent} represents a proposed collection of portal blocks in
 * one dimension. Fire activation and linked Nether portal creation dispatch this
 * event before placing the portal interior; cancellation prevents those interior
 * writes. The supplied position list remains shared with the producer.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
@CallerThread(ThreadType.DIMENSION)
@CallerThread(ThreadType.VIRTUAL)
public class PortalCreateEvent extends Event implements CancellableEvent {

    /**
     * Dimension receiving the portal interior.
     */
    protected Dimension dimension;
    /**
     * {@code portalBlocks} describes the proposed interior positions. The
     * constructor and generated getter retain the same list and vectors. Both
     * built-in producers place blocks from their retained geometry, so list or
     * vector edits change only the event payload.
     */
    protected List<Vector3ic> portalBlocks;
    /**
     * Trigger for the portal creation.
     */
    protected CreateReason reason;

    /**
     * Creates a portal proposal retaining the supplied dimension, position list,
     * and reason. Construction copies neither the list nor its vectors, so
     * listeners and the producer observe the same position objects.
     */
    public PortalCreateEvent(Dimension dimension, List<Vector3ic> portalBlocks, CreateReason reason) {
        this.dimension = dimension;
        this.portalBlocks = portalBlocks;
        this.reason = reason;
    }

    /**
     * {@code CreateReason} identifies the operation requesting a portal interior.
     * Portal producers attach these values so event listeners can distinguish fire
     * activation from a destination portal created during Nether travel.
     */
    public enum CreateReason {
        /**
         * Fire activation inside a portal frame.
         */
        FIRE,
        /**
         * Destination portal for linked Nether travel.
         */
        NETHER_PAIR
    }
}
