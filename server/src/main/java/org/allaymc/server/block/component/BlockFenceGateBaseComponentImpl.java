package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import static org.allaymc.api.block.data.BlockTags.POWER_SOURCE;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.BlockWallBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.FenceGateSound;
import org.allaymc.server.block.BlockPlaceHelper;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * @author IWareQ
 */
public class BlockFenceGateBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockFenceGateBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var neighbor = context.neighbor();
        var oldNeighborState = context.oldNeighborState();

        block.updateBlockProperty(IN_WALL_BIT, shouldBeLowered(block));

        // Only check redstone power if:
        // 1. Neighbor is a power source (new redstone component placed), OR
        // 2. Old neighbor was a power source (power source removed)
        // This prevents the fence gate from closing when normal blocks are placed/broken next to it
        boolean isNewPowerSource = neighbor.getBlockType().hasBlockTag(POWER_SOURCE);
        boolean wasOldPowerSource = oldNeighborState != null && oldNeighborState.getBlockType().hasBlockTag(POWER_SOURCE);
        
        if (isNewPowerSource || wasOldPowerSource) {
            checkRedstonePower(block);
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        blockState = BlockPlaceHelper.processMinecraftCardinalDirectionProperty(blockState, placeBlockPos, placementInfo);
        var current = new Block(blockState, new Position3i(placeBlockPos, dimension));
        blockState = blockState.setPropertyValue(IN_WALL_BIT, shouldBeLowered(current));
        return dimension.setBlockState(placeBlockPos, blockState);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Check redstone power immediately after placement
        var newBlock = new Block(newBlockState, oldBlock.getPosition());
        checkRedstonePower(newBlock);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedPos = interactInfo.clickedBlockPos();
        var clickedBlockState = interactInfo.getClickedBlock();
        var open = !clickedBlockState.getPropertyValue(OPEN_BIT);
        if (open) {
            var playerFacing = interactInfo.player().getHorizontalFace();
            var direction = clickedBlockState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
            if (playerFacing == BlockFace.from(direction).opposite()) {
                dimension.updateBlockProperty(MINECRAFT_CARDINAL_DIRECTION, playerFacing.toMinecraftCardinalDirection(), clickedPos);
            }
        }

        clickedBlockState.updateBlockProperty(OPEN_BIT, open);
        clickedBlockState.addSound(new FenceGateSound(clickedBlockState.getBlockState(), open));
        return true;
    }

    private boolean shouldBeLowered(Block current) {
        var direction = current.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        var blockFace = BlockFace.from(direction);
        blockFace = blockFace.rotateY();
        return current.offsetPos(blockFace).getBehavior() instanceof BlockWallBehavior ||
               current.offsetPos(blockFace.opposite()).getBehavior() instanceof BlockWallBehavior;
    }

    /**
     * Checks if the fence gate should be opened/closed based on redstone power.
     *
     * @param block the fence gate block
     */
    protected void checkRedstonePower(Block block) {
        var power = block.getPower();

        var shouldBeOpen = power > 0;
        var isCurrentlyOpen = block.getPropertyValue(OPEN_BIT);

        if (shouldBeOpen != isCurrentlyOpen) {
            block.updateBlockProperty(OPEN_BIT, shouldBeOpen);
            block.addSound(new FenceGateSound(block.getBlockState(), shouldBeOpen));
        }
    }
}
