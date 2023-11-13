package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.base.BlockBaseComponentImpl;
import org.allaymc.api.block.component.blockentity.BlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.blockentity.interfaces.barrel.BlockEntityBarrel;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.entity.interfaces.player.EntityPlayer;
import org.allaymc.api.world.Dimension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import static java.lang.Math.abs;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.FACING_DIRECTION;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.OPEN_BIT;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBarrelBehavior extends
        BlockBehavior, BlockEntityHolderComponent<BlockEntityBarrel> {
    BlockType<BlockBarrelBehavior> BARREL_TYPE = BlockTypeBuilder
            .builder(BlockBarrelBehavior.class)
            .vanillaBlock(VanillaBlockId.BARREL)
            .setProperties(FACING_DIRECTION, OPEN_BIT)
            .setBlockBaseComponentSupplier(
                    blockType -> new BlockBaseComponentImpl(blockType) {
                        @Override
                        public boolean place(@Nullable EntityPlayer player, @NotNull Dimension dimension, @NotNull BlockState blockState, @NotNull Vector3ic targetBlockPos, @NotNull Vector3ic placeBlockPos, Vector3fc clickPos, @NotNull BlockFace blockFace) {
                            if (player != null) {
                                if (abs(player.getLocation().x() - placeBlockPos.x()) < 2 && abs(player.getLocation().z() - placeBlockPos.z()) < 2) {
                                    var y = player.getLocation().y() + player.getEyeHeight();
                                    if (y - placeBlockPos.y() > 2) {
                                        blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.UP.ordinal());
                                    } else if (placeBlockPos.y() - y > 0) {
                                        blockState = blockState.setProperty(FACING_DIRECTION, BlockFace.DOWN.ordinal());
                                    } else {
                                        blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
                                    }
                                } else {
                                    blockState = blockState.setProperty(FACING_DIRECTION, player.getHorizontalFace().opposite().ordinal());
                                }
                            }
                            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
                            return true;
                        }
                    }
            )
            .bindBlockEntity(BlockEntityBarrel.BARREL_TYPE)
            .build();
}
