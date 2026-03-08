package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.dispenser.DropperDispenseBehavior;

/**
 * @author daoge_cmd
 */
public class BlockEntityDropperBaseComponentImpl extends BlockEntityDispenserBaseComponentImpl {

    public BlockEntityDropperBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    protected DispenseBehavior getBehavior(ItemStack item) {
        return DropperDispenseBehavior.INSTANCE;
    }
}
