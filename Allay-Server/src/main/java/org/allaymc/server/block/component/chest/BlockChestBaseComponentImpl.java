package org.allaymc.server.block.component.chest;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.annotation.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3fc;
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
    public boolean place(EntityPlayer player, Dimension dimension, BlockState blockState, Vector3ic targetBlockPos, Vector3ic placeBlockPos, Vector3fc clickPos, BlockFace blockFace) {
        checkPlaceMethodParam(player, dimension, blockState, targetBlockPos, placeBlockPos, clickPos, blockFace);
        var face = MinecraftCardinalDirection.EAST;
        if (player != null) {
            face = toMinecraftCardinalDirection(player.getHorizontalFace().opposite());
        }
        blockState = blockState.setProperty(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, face);
        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        return true;
    }
}
