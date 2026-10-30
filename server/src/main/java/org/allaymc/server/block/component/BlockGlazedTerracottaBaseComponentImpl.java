package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * {@code BlockGlazedTerracottaBaseComponentImpl} sets glazed terracotta facing during
 * player placement. Player context selects the opposite horizontal facing, while
 * placement without a player preserves the supplied state and delegates state
 * acceptance and block callbacks to the dimension write.
 *
 * @author IWareQ
 */
public class BlockGlazedTerracottaBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the glazed terracotta block behavior for the supplied block type.
     */
    public BlockGlazedTerracottaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places glazed terracotta opposite the player's horizontal facing when context
     * is available. A {@code null} {@code placementInfo} retains the input
     * orientation, and the result reports the dimension write outcome.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.player().getHorizontalFace().opposite();
        // The facing property stores BlockFace ordinals even though this placement
        // selects only horizontal directions; using horizontal indices would encode
        // different directions for the same face.
        blockState = blockState.setPropertyValue(FACING_DIRECTION, face.ordinal());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }
}
