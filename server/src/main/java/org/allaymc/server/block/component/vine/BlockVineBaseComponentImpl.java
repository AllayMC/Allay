package org.allaymc.server.block.component.vine;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * {@code BlockVineBaseComponentImpl} implements horizontal attachment bits, hanging survival,
 * and shears eligibility for ordinary vines. Player placement chooses the support-facing bit
 * and can merge another attachment into an existing vine without replacing its other faces.
 * Programmatic placement delegates directly.
 *
 * <p>
 * Neighbor updates keep an attachment when its own support face is full or a vine immediately
 * above carries the same bit. Unsupported bits are removed, while a full underside overhead
 * can retain a vine even with no horizontal bits. These observations jointly determine
 * survival and preserve each attachment independently rather than breaking a multi-face vine
 * after the loss of only one support.
 */
public class BlockVineBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the vine behavior component for the supplied block type.
     */
    public BlockVineBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a horizontally attached vine or adds a missing face to an existing vine. Player
     * placement requires a full collision face on the supporting neighbor and rejects a bit
     * already present. Programmatic placement with {@code null} information follows inherited
     * handling.
     *
     * <p>
     * The clicked face is reversed to identify the support-facing bit. Existing vines keep
     * every other attachment by combining that bit with their stored mask, while new vines use
     * only the selected bit before ordinary property processing. The method returns the chosen
     * dimension or inherited placement result.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        var clickedFace = placementInfo.blockFace();
        if (clickedFace.isVertical()) {
            return false;
        }

        var supportFace = clickedFace.opposite();
        if (!canAttachAt(dimension, placeBlockPos, supportFace)) {
            return false;
        }

        var currentState = dimension.getBlockState(placeBlockPos);
        var newBits = faceToBit(supportFace);
        if (currentState.getBlockType() == BlockTypes.VINE) {
            var currentBits = currentState.getPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS);
            if ((currentBits & newBits) == newBits) {
                return false;
            }

            return dimension.setBlockState(
                    placeBlockPos.x(),
                    placeBlockPos.y(),
                    placeBlockPos.z(),
                    currentState.setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, currentBits | newBits),
                    placementInfo
            );
        }

        return super.place(
                dimension,
                blockState.setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, newBits),
                placeBlockPos,
                placementInfo
        );
    }

    /**
     * Revalidates each stored attachment after inherited neighbor handling. A bit survives
     * when its adjacent support face remains full or the vine above carries that same bit;
     * bits absent from the current state are never added by this update.
     *
     * <p>
     * When no horizontal bit survives, a full underside on the upper block can still support
     * the vine. Otherwise the vine breaks. Surviving states are rewritten only if the
     * attachment mask changed, preserving valid faces after another side loses its support.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var currentBits = block.getPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS);
        var aboveState = block.offsetPos(BlockFace.UP).getBlockState();
        var aboveBits = aboveState.getBlockType() == BlockTypes.VINE
                ? aboveState.getPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS)
                : 0;

        var supportedBits = 0;
        for (var supportFace : BlockFace.getHorizontalBlockFaces()) {
            var bit = faceToBit(supportFace);
            if ((currentBits & bit) == 0) {
                continue;
            }

            if (canAttachAt(block.getDimension(), block.getPosition(), supportFace) || (aboveBits & bit) != 0) {
                supportedBits |= bit;
            }
        }

        if (supportedBits == 0 && !canHangFrom(aboveState)) {
            block.breakBlock();
            return;
        }

        if (supportedBits != currentBits) {
            block.getDimension().setBlockState(
                    block.getPosition(),
                    block.getBlockState().setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, supportedBits)
            );
        }
    }

    /**
     * Allows ordinary vine drops only when inherited eligibility passes and the tool is
     * shears. A {@code null} tool rejects the drop even when the breaking entity would
     * otherwise qualify.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        return super.isDroppable(block, usedItem, entity) &&
               usedItem != null &&
               usedItem.getItemType() == ItemTypes.SHEARS;
    }

    /**
     * Returns whether vine contact can clear an entity's accumulated fall damage.
     */
    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    /**
     * Returns whether the neighbor at {@code supportFace} presents a full collision face
     * toward the vine. The predicate uses the neighbor's opposite face rather than the outward
     * face identified by the support direction.
     */
    protected boolean canAttachAt(Dimension dimension, Vector3ic vinePos, BlockFace supportFace) {
        var supportState = dimension.getBlockState(supportFace.offsetPos(vinePos));
        return supportState.getBlockStateData().collisionShape().isFull(supportFace.opposite());
    }

    /**
     * Returns whether the supplied upper state has a full lower collision face.
     */
    protected boolean canHangFrom(BlockState blockState) {
        return blockState.getBlockStateData().collisionShape().isFull(BlockFace.DOWN);
    }

    /**
     * Returns the vine attachment bit for a horizontal face. South, west, north, and east use
     * successive bits; a vertical face throws {@code IllegalArgumentException} because no
     * vine-side bit represents it.
     */
    protected int faceToBit(BlockFace face) {
        // Preserve the Bedrock south-west-north-east bit ordering when combining multiple
        // attachments; using face ordinals would encode a different mask in the block state.
        return switch (face) {
            case SOUTH -> 0b0001;
            case WEST -> 0b0010;
            case NORTH -> 0b0100;
            case EAST -> 0b1000;
            default -> throw new IllegalArgumentException("Vine does not support face " + face);
        };
    }
}
