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
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.data.BlockTags.POWER_SOURCE;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DIRECTION_4;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.OPEN_BIT;
import static org.allaymc.server.block.BlockPlaceHelper.EWSN_DIRECTION_4_MAPPER;

/**
 * {@code BlockTrapdoorBaseComponentImpl} handles trapdoor facing, upper-half
 * placement, manual toggling, and redstone opening. Player placement uses the shared
 * stair/trapdoor encoding and click-height rules, while later power-source changes
 * reevaluate opening without allowing unrelated neighbor changes to close a manually
 * opened trapdoor.
 *
 * @author harry-xi | daoge_cmd
 */
public class BlockTrapdoorBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates trapdoor base behavior for the supplied block type.
     */
    public BlockTrapdoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a trapdoor opposite player facing and selects its upper or lower half. A
     * {@code null} {@code placementInfo} preserves the supplied state, and the result
     * reports dimension write acceptance.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        blockState = blockState.setPropertyValue(DIRECTION_4, EWSN_DIRECTION_4_MAPPER.get(placementInfo.player().getHorizontalFace().opposite()));
        blockState = BlockPlaceHelper.processUpsideDownBitProperty(blockState, placeBlockPos, placementInfo);
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Forwards placement and synchronizes the incoming trapdoor's opening with
     * current power. The new block snapshot uses the old snapshot's position, and a
     * state change emits the corresponding trapdoor sound.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        var newBlock = new Block(newBlockState, oldBlock.getPosition());
        checkRedstonePower(newBlock);
    }

    /**
     * Offers interaction to companion components before toggling the trapdoor and
     * playing its sound. Missing interaction context returns {@code false}; otherwise
     * the manual path reports handling after attempting the property update.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo == null) {
            return false;
        }

        var clickedBlockState = interactInfo.getClickedBlock();
        var isOpen = !clickedBlockState.getPropertyValue(OPEN_BIT);
        clickedBlockState.updateBlockProperty(OPEN_BIT, isOpen);
        dimension.addSound(
                MathUtils.center(clickedBlockState.getPosition()),
                new TrapdoorSound(clickedBlockState.getBlockState(), isOpen)
        );
        return true;
    }

    /**
     * Forwards the update and reevaluates opening when the new or old neighbor is a
     * tagged power source. {@code oldNeighborState} may be {@code null}, leaving only
     * the new neighbor's tag available.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        // Only new or removed tagged power sources reevaluate opening, preserving a
        // manually opened trapdoor when unrelated neighbors are placed or broken.
        boolean isNewPowerSource = neighbor.getBlockType().hasBlockTag(POWER_SOURCE);
        boolean wasOldPowerSource = oldNeighborState != null && oldNeighborState.getBlockType().hasBlockTag(POWER_SOURCE);
        
        if (isNewPowerSource || wasOldPowerSource) {
            checkRedstonePower(block);
        }
    }

    /**
     * Synchronizes opening with whether the trapdoor receives positive redstone
     * power. An unchanged opening avoids a property write and sound, while a changed
     * value updates the state before playing its sound.
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
