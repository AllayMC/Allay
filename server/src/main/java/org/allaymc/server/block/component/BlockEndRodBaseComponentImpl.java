package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * {@code BlockEndRodBaseComponentImpl} selects end rod placement orientation from the
 * clicked face and adjoining rod. Horizontal face selection reverses the clicked
 * direction, and a matching adjacent rod reverses it again; placement without player
 * context retains the supplied state.
 *
 * @author Dhaiven
 */
public class BlockEndRodBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the end rod block behavior for the supplied block type.
     */
    public BlockEndRodBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places an end rod oriented from the clicked face and neighboring rod state. A
     * {@code null} {@code placementInfo} writes the supplied state; matching rod
     * orientation reverses the selected face before placement.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.blockFace();
        if (face.isHorizontal()) {
            face = face.opposite();
        }

        // Both the adjacent rod comparison and new rod state use BlockFace ordinals.
        // Mixing horizontal indices into either operation would change which matching
        // rod reverses placement.
        var clickedBlockState = placementInfo.getClickedBlock();
        if (clickedBlockState.getBlockType() == getBlockType() && clickedBlockState.getPropertyValue(FACING_DIRECTION) == face.ordinal()) {
            face = face.opposite();
        }

        blockState = blockState.setPropertyValue(FACING_DIRECTION, face.ordinal());
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }
}
