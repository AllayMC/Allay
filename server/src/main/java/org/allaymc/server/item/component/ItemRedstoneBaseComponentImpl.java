package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Component for redstone wire (dust) item that handles placement of the redstone wire block.
 *
 * @author daoge_cmd
 */
public class ItemRedstoneBaseComponentImpl extends ItemBaseComponentImpl {

    public ItemRedstoneBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.REDSTONE_WIRE.getDefaultState(), placeBlockPos, placementInfo);
    }
}
