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
 * {@code BlockCommandBlockBaseComponentImpl} handles command block placement, editor
 * access, redstone activation, and comparator input. Player placement and editing
 * require creative mode and command block permission; a block entity records power
 * transitions and supplies the last command success count for comparators.
 *
 * @author daoge_cmd
 */
public class BlockCommandBlockBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Injected command block entity holder.
     */
    @Dependency
    private BlockBlockEntityHolderComponent<BlockEntityCommandBlock> blockEntityHolderComponent;

    /**
     * Creates the command block block behavior for the supplied block type.
     */
    public BlockCommandBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a command block with player-derived facing and its initial power state.
     * Player context requires creative mode and command block permission;
     * {@code null} context uses ordinary placement without those player checks.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            var player = placementInfo.player();
            if (player.getGameMode() != GameMode.CREATIVE || !player.hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean()) {
                return false;
            }

            var face = calculatePlaceFacing(player, placeBlockPos);
            // FACING_DIRECTION stores the six-face ordinal encoding, so vertical
            // command block placement must use the same BlockFace order as horizontal
            // placement.
            blockState = blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, face.ordinal());
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

    /**
     * Returns vertical facing for a nearby player above or below the placement
     * position. Other positions use the opposite horizontal direction, with eye
     * height deciding the nearby vertical case.
     */
    protected BlockFace calculatePlaceFacing(EntityPlayer player, Vector3ic blockPos) {
        var playerPos = player.getLocation();
        double dx = Math.abs(playerPos.x() - blockPos.x());
        double dz = Math.abs(playerPos.z() - blockPos.z());

        // The two-block horizontal window restricts vertical orientation to nearby
        // placement.
        if (dx < 2 && dz < 2) {
            double eyeY = playerPos.y() + player.getEyeHeight();
            // A two-block eye-height gap selects upward facing.
            if (eyeY - blockPos.y() > 2) {
                return BlockFace.UP;
            } else if (blockPos.y() - eyeY > 0) {
                return BlockFace.DOWN;
            }
        }

        return player.getHorizontalFace().opposite();
    }

    /**
     * Opens the command block editor for a permitted creative player.
     * {@code interactInfo} must be present, and other players receive {@code false}
     * without opening an editor for this block.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        var player = interactInfo.player();
        if (player.getGameMode() != GameMode.CREATIVE || !player.hasPermission(Permissions.BLOCK_USE_COMMAND_BLOCK).asBoolean()) {
            return false;
        }

        player.getController().viewCommandBlockEditor(interactInfo.clickedBlockPos());
        return true;
    }

    /**
     * Forwards the neighbor update and refreshes the command entity's powered flag. A
     * rising edge triggers execution unless repeating mode defers its first tick,
     * while losing power clears the flag.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var commandBlock = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        var isPowered = block.isPowered();

        if (isPowered) {
            if (!commandBlock.isPowered()) {
                commandBlock.setPowered(true);
                if (commandBlock.getMode() != BlockEntityCommandBlockBaseComponent.CommandBlockMode.REPEATING || commandBlock.isExecuteOnFirstTick()) {
                    commandBlock.trigger();
                }
            }
        } else {
            commandBlock.setPowered(false);
        }
    }

    /**
     * Returns whether command blocks always support specialized comparator input.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns the command entity's success count capped at
     * {@code MAX_REDSTONE_POWER}. The lookup uses the holder's entity creation and
     * recovery path before reading the last execution result.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        var commandBlock = blockEntityHolderComponent.getBlockEntity(block.getPosition());
        // Comparator output cannot exceed the redstone signal range.
        return Math.min(commandBlock.getSuccessCount(), MAX_REDSTONE_POWER);
    }
}
