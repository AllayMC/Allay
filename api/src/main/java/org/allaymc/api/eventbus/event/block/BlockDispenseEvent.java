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
 * {@code BlockDispenseEvent} represents a dispenser or dropper attempt to dispense an
 * inventory item. Cancellation stops the attempt before dispensing, while the
 * selected behavior can be replaced by listeners; the caller copies one item from the
 * selected stack for that behavior.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class BlockDispenseEvent extends BlockEvent implements CancellableEvent {

    /**
     * {@code item} refers to the selected inventory stack. The dispenser copies one
     * item from this stack after event dispatch.
     */
    protected final ItemStack item;

    /**
     * Dispenser outward facing direction.
     */
    protected final BlockFace facing;

    /**
     * {@code behavior} holds the dispense strategy selected after event dispatch.
     * Listeners can replace it before the dispenser invokes {@code dispense}.
     */
    @Setter
    protected DispenseBehavior behavior;

    /**
     * Creates a dispense event retaining the item stack and initial behavior. The
     * dispenser later reads {@code behavior} after dispatch and invokes it with a
     * one-item copy of the selected stack.
     */
    public BlockDispenseEvent(Block block, ItemStack item, BlockFace facing, DispenseBehavior behavior) {
        super(block);
        this.item = item;
        this.facing = facing;
        this.behavior = behavior;
    }
}
