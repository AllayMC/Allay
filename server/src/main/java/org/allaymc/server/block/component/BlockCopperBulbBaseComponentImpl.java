package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * Implementation of the copper bulb block.
 * Toggles its lit state on the rising edge of a redstone signal.
 *
 * @author daoge_cmd
 */
public class BlockCopperBulbBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockCopperBulbBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (dimension.isPoweredAt(placeBlockPos)) {
            blockState = blockState
                    .setPropertyValue(BlockPropertyTypes.LIT, true)
                    .setPropertyValue(BlockPropertyTypes.POWERED_BIT, true);
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var powered = block.isPowered();
        var wasPowered = block.getPropertyValue(BlockPropertyTypes.POWERED_BIT);

        if (powered && !wasPowered) {
            // Rising edge: toggle lit state, set powered_bit to true
            var lit = block.getPropertyValue(BlockPropertyTypes.LIT);
            block.replaceState(
                    block.getBlockState()
                            .setPropertyValue(BlockPropertyTypes.LIT, !lit)
                            .setPropertyValue(BlockPropertyTypes.POWERED_BIT, true)
            );
        } else if (!powered && wasPowered) {
            // Falling edge: only update powered_bit, keep lit unchanged
            block.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, false);
        }
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        return block.getPropertyValue(BlockPropertyTypes.LIT) ? MAX_REDSTONE_POWER : 0;
    }
}
