package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.PropertyUtil.toMinecraftCardinalDirection;

/**
 * Allay Project 2024/3/2
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "minecraft:cardinal_direction")
public class BlockChestBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockChestBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
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
        var face = MinecraftCardinalDirection.EAST;
        if (player != null) {
            face = toMinecraftCardinalDirection(player.getHorizontalFace().opposite());
        }
        blockState = blockState.setProperty(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, face);
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }
}
