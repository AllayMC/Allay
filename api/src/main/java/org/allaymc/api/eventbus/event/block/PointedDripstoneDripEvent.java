package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a pointed dripstone attempts to drip liquid into a cauldron.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PointedDripstoneDripEvent extends BlockEvent implements CancellableEvent {
    /**
     * Whether the liquid being dripped is water (true) or lava (false).
     */
    protected boolean water;

    public PointedDripstoneDripEvent(Block block, boolean water) {
        super(block);
        this.water = water;
    }

    /**
     * Check if the liquid being dripped is lava.
     *
     * @return true if lava, false if water
     */
    public boolean isLava() {
        return !water;
    }
}
