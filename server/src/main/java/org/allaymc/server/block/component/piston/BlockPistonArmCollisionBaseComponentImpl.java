package org.allaymc.server.block.component.piston;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * Base component implementation for piston arm collision blocks.
 *
 * @author daoge_cmd
 */
public class BlockPistonArmCollisionBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockPistonArmCollisionBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        // Piston arm doesn't drop anything - only the piston block itself drops
        return Set.of();
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        // Only cascade when directly broken by player/entity, not when broken by code
        // This prevents infinite recursion between piston and piston arm
        if (entity == null) {
            return;
        }

        // When the piston arm is broken, also break the piston base
        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();

        // Get the facing direction of the arm
        int facing = blockState.getPropertyValue(FACING_DIRECTION);
        BlockFace armFace = BlockFace.fromIndex(facing);
        // For horizontal, the stored value is opposite to the actual facing
        if (armFace.isHorizontal()) {
            armFace = armFace.opposite();
        }

        // The piston base is in the opposite direction of the arm's facing
        Vector3ic pistonPos = armFace.opposite().offsetPos(pos);

        var pistonState = dimension.getBlockState(pistonPos);
        if (pistonState.getBlockType() == BlockTypes.PISTON ||
            pistonState.getBlockType() == BlockTypes.STICKY_PISTON) {
            // Verify the piston's facing matches
            int pistonFacing = pistonState.getPropertyValue(FACING_DIRECTION);
            BlockFace pistonFace = BlockFace.fromIndex(pistonFacing);
            if (pistonFace.isHorizontal()) {
                pistonFace = pistonFace.opposite();
            }
            if (pistonFace == armFace) {
                // Use breakBlock with null entity to trigger particles/sound but prevent recursion
                dimension.breakBlock(pistonPos, null, null);
            }
        }
    }
}
