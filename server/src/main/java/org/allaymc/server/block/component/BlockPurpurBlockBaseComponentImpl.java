package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * {@code BlockPurpurBlockBaseComponentImpl} places purpur block states without
 * inherited property processing. The dimension receives the supplied state and
 * optional player context directly, so this component preserves the selected variant
 * and orientation rather than applying registered placement property handlers.
 *
 * @author IWareQ
 */
public class BlockPurpurBlockBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the purpur block block behavior for the supplied block type.
     */
    public BlockPurpurBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Writes the supplied block state directly with its optional placement context. A
     * {@code null} {@code placementInfo} is forwarded to the dimension, and the
     * result reports whether the write succeeds.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }
}
