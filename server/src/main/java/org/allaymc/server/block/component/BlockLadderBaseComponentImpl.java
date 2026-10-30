package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.Objects;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * {@code BlockLadderBaseComponentImpl} handles ladder attachment, alternative support
 * selection, and fall-distance resetting. Player placement rejects vertical clicked
 * faces and looks for another horizontal support when needed, while neighbor changes
 * break ladders whose stored attachment face no longer has a full surface.
 *
 * @author IWareQ
 */
public class BlockLadderBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the ladder block behavior for the supplied block type.
     */
    public BlockLadderBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards horizontal neighbor changes and verifies the stored supporting face
     * remains full. Vertical changes skip support checks; malformed stored facing
     * values can fail when no matching face is available.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face.isVertical()) {
            return;
        }

        var facingDirection = block.getPropertyValue(FACING_DIRECTION);
        var blockFace = Objects.requireNonNull(BlockFace.fromIndex(facingDirection));
        if (!canBeSupportedAt(block.offsetPos(blockFace.opposite()).getBlockState(), blockFace)) {
            block.breakBlock();
        }
    }

    /**
     * Places a ladder against a valid horizontal support, searching other sides if
     * necessary. A {@code null} {@code placementInfo} writes the supplied state
     * directly, while vertical clicks and missing support return {@code false}.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var blockFace = placementInfo.blockFace();
        if (blockFace.isVertical()) {
            return false;
        }

        if (!canBeSupportedAt(placementInfo.getClickedBlock().getBlockState(), blockFace)) {
            // The clicked face cannot support this ladder. Search neighboring
            // horizontal surfaces so placement can still attach to another suitable
            // wall.
            blockFace = findValidFace(dimension, placeBlockPos);
        }

        if (blockFace == null) {
            return false;
        }

        // The stored facing uses BlockFace ordinals rather than horizontal indices,
        // matching the fromIndex decoding used when later neighbor changes recheck
        // ladder support.
        return dimension.setBlockState(placeBlockPos, blockState.setPropertyValue(FACING_DIRECTION, blockFace.ordinal()));
    }

    /**
     * Returns whether ladder contact resets an entity's accumulated fall distance.
     */
    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    /**
     * Returns a ladder facing supported by a full horizontal neighbor surface. The
     * search tries south, north, west, then east support positions and returns
     * {@code null} when none qualify.
     */
    private BlockFace findValidFace(Dimension dimension, Vector3ic placeBlockPos) {
        for (var face : new BlockFace[]{BlockFace.SOUTH, BlockFace.NORTH, BlockFace.WEST, BlockFace.EAST}) {
            if (canBeSupportedAt(dimension.getBlockState(face.offsetPos(placeBlockPos)), face.opposite())) {
                return face.opposite();
            }
        }

        return null;
    }

    /**
     * Returns whether the support shape completely covers the requested attachment
     * face.
     */
    private boolean canBeSupportedAt(BlockState blockState, BlockFace face) {
        var shape = blockState.getBlockStateData().collisionShape();
        return shape.isFull(face);
    }
}
