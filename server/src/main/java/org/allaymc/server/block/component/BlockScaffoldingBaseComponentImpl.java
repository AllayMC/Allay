package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.STABILITY_CHECK;

/**
 * {@code BlockScaffoldingBaseComponentImpl} handles vertical scaffolding extension,
 * lower support checks, and fall-distance resetting. Top-face interaction with a
 * matching item extends an existing column at its first nonmatching position when
 * that position is air; the current implementation does not provide horizontal
 * support propagation.
 *
 * @author IWareQ
 */
public class BlockScaffoldingBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the scaffolding block behavior for the supplied block type.
     */
    public BlockScaffoldingBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Breaks scaffolding when a lower-neighbor update leaves it without valid
     * support. This override does not forward the inherited component event, and
     * support currently considers only the block immediately below.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        // TODO: Propagate support distances through horizontal scaffolding before accepting side-supported columns.
        if (face == BlockFace.DOWN && !canBeSupportedAt(block.offsetPos(BlockFace.DOWN).getBlockState())) {
            block.breakBlock();
        }
    }

    /**
     * Extends a scaffolding column for a top-face interaction with its matching item.
     * The first nonmatching position must be air; after attempting its state write,
     * the player's held item is consumed without checking the write result.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (interactInfo.blockFace() != BlockFace.UP || itemStack.getItemType() != getBlockType().getItemType()) {
            return false;
        }

        var nextScaffoldingPos = findNextScaffoldingPos(dimension, interactInfo.clickedBlockPos());
        if (dimension.getBlockState(nextScaffoldingPos).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        dimension.setBlockState(nextScaffoldingPos, getBlockType().getDefaultState());
        interactInfo.player().tryConsumeItemInHand();
        return true;
    }

    /**
     * Places player-supplied scaffolding with {@code STABILITY_CHECK} enabled. A
     * {@code null} {@code placementInfo} uses inherited property processing, while
     * the player path writes the resulting block state directly and returns the
     * dimension result.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        blockState = blockState.setPropertyValue(STABILITY_CHECK, true);
        return dimension.setBlockState(placeBlockPos, blockState);
    }

    /**
     * Returns the first nonmatching block position above the scaffolding column.
     */
    private Vector3ic findNextScaffoldingPos(Dimension dimension, Vector3ic placeBlockPos) {
        var current = BlockFace.UP.offsetPos(placeBlockPos);
        while (dimension.getBlockState(current).getBlockType() == getBlockType()) {
            current = BlockFace.UP.offsetPos(current);
        }

        return current;
    }

    /**
     * Returns whether a full upper surface or matching scaffolding supports this
     * block.
     */
    private boolean canBeSupportedAt(BlockState blockState) {
        return blockState.getBlockStateData().collisionShape().isFull(BlockFace.UP) || blockState.getBlockType() == getBlockType();
    }

    /**
     * Returns whether scaffolding contact resets an entity's accumulated fall
     * distance.
     */
    @Override
    public boolean canResetFallDamage() {
        return true;
    }
}
