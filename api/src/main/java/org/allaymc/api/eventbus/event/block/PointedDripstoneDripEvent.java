package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PointedDripstoneDripEvent} represents a pointed dripstone attempt to add
 * liquid to a cauldron. The inherited block identifies the receiving cauldron rather
 * than the dripstone, and the liquid flag distinguishes water from lava; cancellation
 * prevents that filling attempt.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PointedDripstoneDripEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code water} selects water when {@code true} and lava when {@code false};
     * {@code isLava} exposes the complementary selection.
     */
    protected boolean water;

    /**
     * Creates a drip event for the receiving cauldron and liquid selection.
     */
    public PointedDripstoneDripEvent(Block block, boolean water) {
        super(block);
        this.water = water;
    }

    /**
     * Returns whether this drip carries lava instead of water.
     */
    public boolean isLava() {
        return !water;
    }
}
