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
 * @author IWareQ
 */
public class BlockScaffoldingBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockScaffoldingBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        // TODO: add support horizontal scaffolding
        if (face == BlockFace.DOWN && !canBeSupportedAt(block.offsetPos(BlockFace.DOWN).getBlockState())) {
            block.breakBlock();
        }
    }

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

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        blockState = blockState.setPropertyValue(STABILITY_CHECK, true);
        return dimension.setBlockState(placeBlockPos, blockState);
    }

    private Vector3ic findNextScaffoldingPos(Dimension dimension, Vector3ic placeBlockPos) {
        var current = BlockFace.UP.offsetPos(placeBlockPos);
        while (dimension.getBlockState(current).getBlockType() == getBlockType()) {
            current = BlockFace.UP.offsetPos(current);
        }

        return current;
    }

    private boolean canBeSupportedAt(BlockState blockState) {
        return blockState.getBlockStateData().collisionShape().isFull(BlockFace.UP) || blockState.getBlockType() == getBlockType();
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }
}
