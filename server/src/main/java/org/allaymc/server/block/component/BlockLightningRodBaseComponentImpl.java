package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * {@code BlockLightningRodBaseComponentImpl} places lightning rods with their facing
 * derived from the clicked block face. Programmatic placement without interaction
 * information keeps the supplied state, while player placement writes the face ordinal
 * directly before delegating the world update to the dimension.
 *
 * @author IWareQ
 */
public class BlockLightningRodBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the lightning rod component for the supplied block type.
     */
    public BlockLightningRodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a lightning rod using the clicked face as {@code FACING_DIRECTION}. A
     * {@code null} {@code placementInfo} preserves the supplied facing and uses the
     * dimension's placement overload without interaction information.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        blockState = blockState.setPropertyValue(FACING_DIRECTION, placementInfo.blockFace().ordinal());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }
}
