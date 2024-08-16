package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.PlaceBlockHelper;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "open_bit")
public class BlockBarrelBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockBarrelBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                PlaceBlockHelper.processFacingDirectionProperty(blockState, placeBlockPos, placementInfo),
                placementInfo
        );
        return true;
    }
}
