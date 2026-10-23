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
 * {@code BlockCopperBulbBaseComponentImpl} manages copper bulb lighting as a redstone
 * edge latch and supplies its comparator output. {@code POWERED_BIT} records the last
 * observed power condition, while {@code LIT} records the retained light state. A
 * neighbor update toggles the light only when current power is present and the recorded
 * power bit is clear.
 *
 * <p>
 * A falling edge clears only {@code POWERED_BIT}, so losing power leaves the light
 * unchanged and permits the next rising edge to toggle it. Placement into a powered
 * position initializes both bits together. Comparator output follows {@code LIT},
 * rather than the current input, allowing a lit bulb to keep its maximum output after
 * input power disappears.
 *
 * @author daoge_cmd
 */
public class BlockCopperBulbBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the copper bulb component for the supplied block type.
     */
    public BlockCopperBulbBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a bulb after initializing its powered state when the destination receives
     * power. Powered placement sets both {@code LIT} and {@code POWERED_BIT};
     * otherwise the supplied state continues through ordinary property processing.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (dimension.isPoweredAt(placeBlockPos)) {
            blockState = blockState
                    .setPropertyValue(BlockPropertyTypes.LIT, true)
                    .setPropertyValue(BlockPropertyTypes.POWERED_BIT, true);
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Updates the bulb's light latch after inherited neighbor handling. Current power
     * and the snapshot's {@code POWERED_BIT} distinguish a new rising edge from
     * sustained input, so repeated powered notifications do not toggle the light
     * repeatedly.
     *
     * <p>
     * A rising edge replaces the state with the opposite {@code LIT} value and a set
     * power bit. A falling edge clears only the power bit, preserving the light state
     * for comparator readers and the next edge.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var powered = block.isPowered();
        var wasPowered = block.getPropertyValue(BlockPropertyTypes.POWERED_BIT);

        if (powered && !wasPowered) {
            // Record the new input together with the toggled light so another powered
            // notification observes a latched edge instead of toggling again.
            var lit = block.getPropertyValue(BlockPropertyTypes.LIT);
            block.replaceState(
                    block.getBlockState()
                            .setPropertyValue(BlockPropertyTypes.LIT, !lit)
                            .setPropertyValue(BlockPropertyTypes.POWERED_BIT, true)
            );
        } else if (!powered && wasPowered) {
            // Clear the edge latch without changing LIT so the next rising input can
            // toggle the bulb while its current light survives power loss.
            block.updateBlockProperty(BlockPropertyTypes.POWERED_BIT, false);
        }
    }

    /**
     * Returns whether bulbs expose their light latch as comparator input.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns maximum comparator input for a lit bulb and {@code 0} otherwise. The
     * result uses {@code LIT} from the supplied block snapshot and does not sample the
     * current redstone input.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        return block.getPropertyValue(BlockPropertyTypes.LIT) ? MAX_REDSTONE_POWER : 0;
    }
}
