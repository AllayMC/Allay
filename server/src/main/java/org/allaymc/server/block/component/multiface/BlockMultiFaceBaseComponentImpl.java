package org.allaymc.server.block.component.multiface;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code BlockMultiFaceBaseComponentImpl} manages blocks whose attachments occupy
 * several faces encoded in one direction bit mask. Placement preserves existing
 * attachment bits for matching block types and adds one supported face; combining
 * uses the same path. Neighbor updates remove only unsupported bits and break the
 * block when none remain.
 *
 * <p>
 * The clicked face is converted into the direction toward its supporting neighbor,
 * then tested against that neighbor's opposing full surface. Missing, invalid, or
 * already-present choices use the first unused supported face in face-array order. A
 * dedicated bit mapping preserves the property's south-west-north-east horizontal
 * order rather than relying on the face enum ordinals, keeping attachment identity
 * stable across placement, support checks, and item counts.
 */
public class BlockMultiFaceBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates multi-face base behavior for the supplied block type.
     */
    public BlockMultiFaceBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Adds an attachment only when the position already contains this block type.
     * Matching states delegate to the normal placement path, including its
     * supported-face search and existing-bit preservation rules.
     */
    @Override
    public boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        if (dimension.getBlockState(combineBlockPos).getBlockType() != blockType) {
            return false;
        }

        return place(dimension, blockState, combineBlockPos, placementInfo);
    }

    /**
     * Adds one supported attachment while preserving existing bits for this block
     * type. The clicked face points away from its support, so its opposite supplies
     * the requested support direction; missing player context starts with fallback
     * selection.
     *
     * <p>
     * An unsupported or already-set choice searches for an unused supported face. No
     * qualifying face returns {@code false}. Existing matching blocks receive updated
     * bits in their current state, while a new block uses inherited placement with
     * the input state and calculated bits.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var currentState = dimension.getBlockState(placeBlockPos);
        var currentBits = currentState.getBlockType() == blockType
                ? currentState.getPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                : 0;

        var newFace = placementInfo == null ? null : placementInfo.blockFace().opposite();
        if (newFace == null || !canAttachAt(dimension, placeBlockPos, newFace)) {
            // Missing placement context or an unsupported clicked face cannot select
            // a new attachment directly. Search unused supported directions while
            // preserving existing bits.
            newFace = findAttachableFace(dimension, placeBlockPos, currentBits);
        }

        if (newFace == null) {
            return false;
        }

        var newBits = currentBits | faceToBit(newFace);
        if (newBits == currentBits) {
            // The requested supported face is already attached. Choose a different
            // unused support so a successful placement always adds an attachment bit.
            var fallbackFace = findAttachableFace(dimension, placeBlockPos, currentBits);
            if (fallbackFace == null) {
                return false;
            }

            newBits |= faceToBit(fallbackFace);
        }

        if (currentState.getBlockType() == blockType) {
            return dimension.setBlockState(
                    placeBlockPos.x(),
                    placeBlockPos.y(),
                    placeBlockPos.z(),
                    currentState.setPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS, newBits),
                    placementInfo
            );
        }

        return super.place(
                dimension,
                blockState.setPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS, newBits),
                placeBlockPos,
                placementInfo
        );
    }

    /**
     * Forwards the neighbor event and recomputes which existing attachment bits
     * remain supported. Every currently set bit is tested against the corresponding
     * neighbor's opposing full face; absent bits are never added here.
     *
     * <p>
     * No surviving bits trigger the ordinary block break path. A strict subset of
     * surviving bits replaces the mask while retaining the other current state
     * properties. An unchanged mask avoids a dimension write, so unrelated neighbor
     * changes do not rebuild the attachment state unnecessarily.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var currentBits = block.getPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS);
        var supportedBits = 0;
        for (var supportFace : BlockFace.VALUES) {
            var bit = faceToBit(supportFace);
            if ((currentBits & bit) != 0 && canAttachAt(block.getDimension(), block.getPosition(), supportFace)) {
                supportedBits |= bit;
            }
        }

        if (supportedBits == 0) {
            block.breakBlock();
            return;
        }

        if (supportedBits != currentBits) {
            block.getDimension().setBlockState(
                    block.getPosition(),
                    block.getBlockState().setPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS, supportedBits)
            );
        }
    }

    /**
     * Returns whether the neighboring block completely covers the face toward this
     * position. {@code supportFace} points from the attaching block toward its
     * support, so the support's opposite face is tested.
     */
    protected boolean canAttachAt(Dimension dimension, Vector3ic blockPos, BlockFace supportFace) {
        var supportState = dimension.getBlockState(supportFace.offsetPos(blockPos));
        return supportState.getBlockStateData().collisionShape().isFull(supportFace.opposite());
    }

    /**
     * Returns the first unused supported face in {@code BlockFace.VALUES} order. Bits
     * already present in {@code currentBits} are excluded, and no supported unused
     * neighboring surface face produces {@code null}.
     */
    protected BlockFace findAttachableFace(Dimension dimension, Vector3ic blockPos, int currentBits) {
        for (var supportFace : BlockFace.VALUES) {
            var bit = faceToBit(supportFace);
            if ((currentBits & bit) == 0 && canAttachAt(dimension, blockPos, supportFace)) {
                return supportFace;
            }
        }

        return null;
    }

    /**
     * Returns the property bit identifying the supplied support face. The horizontal
     * bit order is south, west, north, then east, which differs from the face enum's
     * declaration order.
     */
    protected int faceToBit(BlockFace face) {
        // Direction bits use the block property's own ordering. Mapping explicit
        // faces prevents enum declaration order from silently changing saved
        // attachment identities.
        return switch (face) {
            case DOWN -> 0b000001;
            case UP -> 0b000010;
            case SOUTH -> 0b000100;
            case WEST -> 0b001000;
            case NORTH -> 0b010000;
            case EAST -> 0b100000;
        };
    }
}
