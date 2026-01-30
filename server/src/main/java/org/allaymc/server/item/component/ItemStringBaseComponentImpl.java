package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Component for string item that handles placement of the tripwire block.
 *
 * @author daoge_cmd
 */
public class ItemStringBaseComponentImpl extends ItemBaseComponentImpl {

    public ItemStringBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.TRIP_WIRE.getDefaultState(), placeBlockPos, placementInfo);
    }
}
