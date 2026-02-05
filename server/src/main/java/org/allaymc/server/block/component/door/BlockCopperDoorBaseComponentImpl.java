package org.allaymc.server.block.component.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockCopperDoorBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.component.annotation.Dependency;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPPER_BLOCK_BIT;
import org.allaymc.api.block.type.BlockState;

/**
 * @author IWareQ
 */
public class BlockCopperDoorBaseComponentImpl extends BlockDoorBaseComponentImpl {
    @Dependency
    protected BlockOxidationComponent oxidationComponent;

    public BlockCopperDoorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onRandomUpdate(Block block) {
        // Only random update the lower part of the door. When the oxidation level of the lower block change,
        // we will sync the level to the upper block
        if (!block.getPropertyValue(UPPER_BLOCK_BIT)) {
            super.onRandomUpdate(block);
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (!isSameDoor(neighbor)) {
            return;
        }

        var blockBehavior = (BlockCopperDoorBehavior) block.getBlockType().getBlockBehavior();
        var neighborBlockBehavior = (BlockCopperDoorBehavior) neighbor.getBlockType().getBlockBehavior();

        var isUpperBlock = block.getPropertyValue(UPPER_BLOCK_BIT);
        OxidationLevel newOxidationLevel = null;
        boolean shouldbeWaxed = false;

        // Only the lower block can do the random update
        if (isUpperBlock && face == BlockFace.DOWN) {
            var oxidationLevel = blockBehavior.getOxidationLevel();
            var neighborOxidationLevel = neighborBlockBehavior.getOxidationLevel();
            if (oxidationLevel.ordinal() < neighborOxidationLevel.ordinal()) {
                // Sync the oxidation level to the upper block
                newOxidationLevel = neighborOxidationLevel;
            }

            if (!blockBehavior.isWaxed() && neighborBlockBehavior.isWaxed()) {
                shouldbeWaxed = true;
            }
        } else if (!isUpperBlock && face == BlockFace.UP) {
            if (!blockBehavior.isWaxed() && neighborBlockBehavior.isWaxed()) {
                shouldbeWaxed = true;
            }
        }

        BlockType<? extends BlockOxidationComponent> newBlockType = null;
        if (newOxidationLevel != null) {
            newBlockType = blockBehavior.getBlockWithOxidationLevel(newOxidationLevel);
        }

        if (shouldbeWaxed) {
            if (newBlockType != null) {
                newBlockType = newBlockType.getBlockBehavior().getBlockWithWaxed(true);
            } else {
                newBlockType = blockBehavior.getBlockWithWaxed(true);
            }
        }

        if (newBlockType != null) {
            var newBlockState = newBlockType.copyPropertyValuesFrom(block.getBlockState());
            block.getDimension().setBlockState(block.getPosition(), newBlockState);
        }
    }

    @Override
    protected boolean isSameDoor(Block neighbor) {
        return neighbor.getBlockType().getBlockBehavior() instanceof BlockCopperDoorBehavior;
    }
}
