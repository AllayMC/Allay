package org.allaymc.server.block.component.facing;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.PILLAR_AXIS;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "pillar_axis")
public class BlockPillarAxisComponentImpl extends BlockBaseComponentImpl {
    public BlockPillarAxisComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo != null) {
            blockState = blockState.setProperty(PILLAR_AXIS, switch (placementInfo.blockFace()) {
                case EAST, WEST -> PillarAxis.X;
                case DOWN, UP -> PillarAxis.Y;
                case NORTH, SOUTH -> PillarAxis.Z;
            });
        }
        
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
