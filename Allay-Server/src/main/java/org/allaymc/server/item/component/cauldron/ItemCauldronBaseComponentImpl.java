package org.allaymc.server.item.component.cauldron;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCauldronStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import static org.allaymc.api.block.type.BlockTypes.CAULDRON_TYPE;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class ItemCauldronBaseComponentImpl extends ItemBaseComponentImpl<ItemCauldronStack> {
    public ItemCauldronBaseComponentImpl(ItemStackInitInfo<ItemCauldronStack> initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(EntityPlayer player, Dimension dimension, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        return tryPlaceBlockState(player, dimension, targetBlockPos, placeBlockPos, clickPos, blockFace, CAULDRON_TYPE.getDefaultState());
    }
}
