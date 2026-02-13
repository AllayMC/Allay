package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.component.BlockEntityCommandBlockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCommandBlock;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3ic;

/**
 * Implementation of command block block behavior.
 * Handles placement facing direction, player interaction (opening UI),
 * redstone signal detection, and comparator output.
 *
 * @author daoge_cmd
 */
public class BlockCommandBlockBaseComponentImpl extends BlockBaseComponentImpl {

    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityCommandBlock> blockEntityHolderComponent;

    public BlockCommandBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            var player = placementInfo.player();
            // Only creative players with command block permission can place command blocks
            if (player.getGameMode() != GameMode.CREATIVE || !player.hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean()) {
                return false;
            }

            var face = calculatePlaceFacing(player, placeBlockPos);
            blockState = blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, face.ordinal());

            // Check if powered on placement
            if (dimension.isPoweredAt(placeBlockPos)) {
                var result = super.place(dimension, blockState, placeBlockPos, placementInfo);
                if (result) {
                    var commandBlock = blockEntityHolderComponent.getBlockEntity(
                            placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), dimension);
                    commandBlock.setPowered(true);
                }
                return result;
            }
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    protected BlockFace calculatePlaceFacing(EntityPlayer player, Vector3ic blockPos) {
        var playerPos = player.getLocation();
        double dx = Math.abs(playerPos.x() - blockPos.x());
        double dz = Math.abs(playerPos.z() - blockPos.z());

        if (dx < 2 && dz < 2) {
            double eyeY = playerPos.y() + player.getEyeHeight();
            if (eyeY - blockPos.y() > 2) {
                return BlockFace.UP;
            } else if (blockPos.y() - eyeY > 0) {
                return BlockFace.DOWN;
            }
        }

        return player.getHorizontalFace().opposite();
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var player = interactInfo.player();
        // Only creative players with command block permission can interact with command blocks
        if (player.getGameMode() != GameMode.CREATIVE || !player.hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean()) {
            return false;
        }

        player.getController().viewCommandBlockEditor(interactInfo.clickedBlockPos());
        return true;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var commandBlock = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        var isPowered = block.isPowered();

        if (isPowered) {
            if (!commandBlock.isPowered()) {
                commandBlock.setPowered(true);
                if (commandBlock.getMode() != BlockEntityCommandBlockBaseComponent.CommandBlockMode.REPEATING || commandBlock.isExecutingOnFirstTick()) {
                    commandBlock.trigger();
                }
            }
        } else {
            commandBlock.setPowered(false);
        }
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var commandBlock = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        return Math.min(commandBlock.getSuccessCount(), MAX_REDSTONE_POWER);
    }
}
