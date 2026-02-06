package org.allaymc.server.block.component.trapdoor;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import static org.allaymc.api.block.data.BlockTags.POWER_SOURCE;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.TrapdoorSound;
import org.allaymc.server.block.BlockPlaceHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;
import static org.allaymc.server.block.BlockPlaceHelper.EWSN_DIRECTION_4_MAPPER;

/**
 * @author harry-xi | daoge_cmd
 */
public class BlockTrapdoorBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockTrapdoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        blockState = blockState.setPropertyValue(DIRECTION_4, EWSN_DIRECTION_4_MAPPER.get(placementInfo.player().getHorizontalFace().opposite()));
        blockState = BlockPlaceHelper.processUpsideDownBitProperty(blockState, placeBlockPos, placementInfo);
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
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

        if (interactInfo == null || interactInfo.player().isSneaking()) {
            return false;
        }

        var clickedBlockState = interactInfo.getClickedBlock();
        var isOpen = !clickedBlockState.getPropertyValue(OPEN_BIT);
        clickedBlockState.updateBlockProperty(OPEN_BIT, isOpen);
        // Shouldn't use addLevelSoundEvent here, which has no effect on client for no reason
        dimension.addSound(
                MathUtils.center(clickedBlockState.getPosition()),
                new TrapdoorSound(clickedBlockState.getBlockState(), isOpen)
        );
        return true;
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var neighbor = context.neighbor();
        var oldNeighborState = context.oldNeighborState();

        // Only check redstone power if:
        // 1. Neighbor is a power source (new redstone component placed), OR
        // 2. Old neighbor was a power source (power source removed)
        // This prevents the trapdoor from closing when normal blocks are placed/broken next to it
        boolean isNewPowerSource = neighbor.getBlockType().hasBlockTag(POWER_SOURCE);
        boolean wasOldPowerSource = oldNeighborState != null && oldNeighborState.getBlockType().hasBlockTag(POWER_SOURCE);
        
        if (isNewPowerSource || wasOldPowerSource) {
            checkRedstonePower(block);
        }
    }

    /**
     * Checks if the trapdoor should be opened/closed based on redstone power.
     *
     * @param block the trapdoor block
     */
    protected void checkRedstonePower(Block block) {
        var power = block.getPower();

        var shouldBeOpen = power > 0;
        var isCurrentlyOpen = block.getPropertyValue(OPEN_BIT);

        if (shouldBeOpen != isCurrentlyOpen) {
            block.updateBlockProperty(OPEN_BIT, shouldBeOpen);
            block.addSound(new TrapdoorSound(block.getBlockState(), shouldBeOpen));
        }
    }
}
