package org.allaymc.server.item.component;

import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.item.init.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemCauldronStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.item.component.common.ItemBaseComponentImpl;
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
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, CAULDRON_TYPE.getDefaultState(), placeBlockPos, placementInfo);
    }
}
