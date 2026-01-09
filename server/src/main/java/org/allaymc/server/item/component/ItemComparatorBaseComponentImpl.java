package org.allaymc.server.item.component;

import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Component for comparator item that handles placement of the unpowered comparator block.
 *
 * @author daoge_cmd
 */
public class ItemComparatorBaseComponentImpl extends ItemBaseComponentImpl {

    public ItemComparatorBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean placeBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return tryPlaceBlockState(dimension, BlockTypes.UNPOWERED_COMPARATOR.getDefaultState(), placeBlockPos, placementInfo);
    }
}
