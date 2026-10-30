package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.dispenser.DropperDispenseBehavior;

/**
 * {@code BlockEntityDropperBaseComponentImpl} specializes dispenser processing
 * with the shared dropper behavior. Item selection, event cancellation, source
 * consumption, and remainder handling remain inherited; behavior selection
 * always starts with the dropper implementation instead of the item-specific
 * dispenser registry.
 *
 * @author daoge_cmd
 */
public class BlockEntityDropperBaseComponentImpl extends BlockEntityDispenserBaseComponentImpl {

    /**
     * Creates dropper base state using the supplied block-entity initialization
     * information.
     */
    public BlockEntityDropperBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns the shared dropper behavior regardless of the selected item type.
     */
    @Override
    protected DispenseBehavior getBehavior(ItemStack item) {
        return DropperDispenseBehavior.INSTANCE;
    }
}
