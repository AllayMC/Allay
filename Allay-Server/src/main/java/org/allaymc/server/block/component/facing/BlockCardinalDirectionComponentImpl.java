package org.allaymc.server.block.component.facing;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;

import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.PropertyUtil.toMinecraftCardinalDirection;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;

/**
 * Allay Project 2024/8/15
 *
 * @author Dhaiven
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "minecraft:cardinal_direction")
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
            blockState = blockState.setProperty(MINECRAFT_CARDINAL_DIRECTION, toMinecraftCardinalDirection(player.getHorizontalFace().opposite()));
        }
        
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
