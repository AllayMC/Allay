package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a dispenser or dropper dispenses an item.
 * <p>
 * This event can be used to modify or cancel the dispense behavior.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BlockDispenseEvent extends BlockEvent implements CancellableEvent {

    /**
     * The item being dispensed.
     */
    protected final ItemStack item;

    /**
     * The direction the dispenser is facing.
     */
    protected final BlockFace facing;

    /**
     * The dispense behavior that will be used.
     * Can be modified to override the default behavior.
     */
    @Setter
    protected DispenseBehavior behavior;

    public BlockDispenseEvent(Block block, ItemStack item, BlockFace facing, DispenseBehavior behavior) {
        super(block);
        this.item = item;
        this.facing = facing;
        this.behavior = behavior;
    }
}
