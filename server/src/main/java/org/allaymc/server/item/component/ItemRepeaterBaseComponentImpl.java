package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Component for repeater item that handles placement of the unpowered repeater block.
 *
 * @author daoge_cmd
 */
public class ItemRepeaterBaseComponentImpl extends ItemBaseComponentImpl {

    public ItemRepeaterBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.UNPOWERED_REPEATER.getDefaultState(), placeBlockPos, placementInfo);
    }
}
