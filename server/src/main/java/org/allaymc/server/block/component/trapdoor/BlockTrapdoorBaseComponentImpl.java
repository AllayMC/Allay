package org.allaymc.server.block.component.trapdoor;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.TrapdoorSound;
import org.allaymc.server.block.BlockPlaceHelper;
import org.allaymc.server.block.RedstoneHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;
import static org.allaymc.server.block.BlockPlaceHelper.EWSN_DIRECTION_4_MAPPER;

/**
 * @author harry-xi
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
                isOpen ? new TrapdoorSound(clickedBlockState.getBlockState(), true) : new TrapdoorSound(clickedBlockState.getBlockState(), false)
        );
        return true;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        // Check redstone power and update trapdoor state
        checkRedstonePower(block);
    }

    /**
     * Checks if the trapdoor should be opened/closed based on redstone power.
     *
     * @param block the trapdoor block
     */
    protected void checkRedstonePower(Block block) {
        int power = RedstoneHelper.getPowerAt(block.getPosition());

        boolean shouldBeOpen = power > 0;
        boolean isCurrentlyOpen = block.getPropertyValue(OPEN_BIT);

        if (shouldBeOpen != isCurrentlyOpen) {
            var dimension = block.getDimension();
            var pos = block.getPosition();

            dimension.updateBlockProperty(OPEN_BIT, shouldBeOpen, pos);

            // Play sound
            dimension.addSound(MathUtils.center(pos), shouldBeOpen ? new TrapdoorSound(block.getBlockState(), true) : new TrapdoorSound(block.getBlockState(), false));
        }
    }
}
