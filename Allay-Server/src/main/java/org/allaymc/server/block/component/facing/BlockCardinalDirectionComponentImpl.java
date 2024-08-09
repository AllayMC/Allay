package org.allaymc.server.block.component.facing;

import static org.allaymc.api.data.VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "cardinal_direction")
public class BlockCardinalDirectionComponentImpl extends BlockBaseComponentImpl {
    public BlockCardinalDirectionComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }
        var player = placementInfo.player();
        if (player != null) {
            blockState = blockState.setProperty(MINECRAFT_CARDINAL_DIRECTION, switch (player.getHorizontalFace()) {
                case EAST -> MinecraftCardinalDirection.WEST;
                case NORTH -> MinecraftCardinalDirection.SOUTH;
                case SOUTH -> MinecraftCardinalDirection.NORTH;
                default -> MinecraftCardinalDirection.EAST;
            });
        }
        
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
