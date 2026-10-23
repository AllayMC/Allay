package org.allaymc.server.block.component.portal;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.END_PORTAL_EYE_BIT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;

/**
 * {@code BlockEndPortalFrameBaseComponentImpl} inserts eyes into End portal frames
 * and discovers complete inward-facing frame rings. A frame interaction consumes
 * one eye and updates comparator output before testing formation. Each possible
 * center follows from the clicked frame's facing and its possible position along a
 * three-frame side.
 *
 * <p>
 * {@code FRAME_OFFSETS} describes the twelve ring positions and their required
 * direction indices. {@code DIRECTIONS} interprets those indices using
 * {@code MinecraftCardinalDirection} declaration order, so both representations
 * must remain aligned. The first candidate whose frames all contain eyes and face
 * inward receives a three-by-three portal interior; existing interior blocks are
 * overwritten without a separate creation event.
 *
 * @author IWareQ | daoge_cmd
 */
public class BlockEndPortalFrameBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code FRAME_OFFSETS} lists the twelve ring positions as
     * {@code {dx, dz, directionIndex}} relative to the center.
     * {@code directionIndex} addresses {@code DIRECTIONS} in south, west, north,
     * east declaration order, and corner blocks are excluded.
     */
    private static final int[][] FRAME_OFFSETS = {
            {-1, -2, 0}, {0, -2, 0}, {1, -2, 0},
            {-1, 2, 2}, {0, 2, 2}, {1, 2, 2},
            {-2, -1, 3}, {-2, 0, 3}, {-2, 1, 3},
            {2, -1, 1}, {2, 0, 1}, {2, 1, 1}
    };

    /**
     * {@code DIRECTIONS} decodes direction indices stored in
     * {@code FRAME_OFFSETS}. Its enum declaration order must remain aligned with
     * those indices for inward-facing validation.
     */
    private static final MinecraftCardinalDirection[] DIRECTIONS = MinecraftCardinalDirection.values();

    /**
     * Creates this block behavior component for the supplied registered block
     * type.
     */
    public BlockEndPortalFrameBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared handlers before filling an empty frame with an ender eye.
     * {@code interactInfo} must identify the player and clicked frame; eye
     * insertion precedes sound, consumption, comparator refresh, and the portal
     * formation attempt.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedBlockState = interactInfo.getClickedBlock();
        if (clickedBlockState.getPropertyValue(END_PORTAL_EYE_BIT) || itemStack.getItemType() != ItemTypes.ENDER_EYE) {
            return false;
        }

        clickedBlockState.updateBlockProperty(END_PORTAL_EYE_BIT, true);
        clickedBlockState.addSound(SimpleSound.END_PORTAL_FRAME_FILLED);
        interactInfo.player().tryConsumeItemInHand();

        dimension.updateComparatorOutputLevel(interactInfo.clickedBlockPos());

        tryFormEndPortal(dimension, interactInfo.clickedBlockPos());

        return true;
    }

    /**
     * Forms a portal at the first complete center implied by the updated frame.
     * The supplied position must still contain an End portal frame before
     * candidates are generated. Its orientation identifies the inward side, while
     * its unknown position along that side yields three candidate centers.
     * Validation combines all twelve frames' identities, eye bits, and
     * orientations; the first valid candidate is filled and ends the search.
     */
    private void tryFormEndPortal(Dimension dimension, Vector3ic placedPos) {
        var placedState = dimension.getBlockState(placedPos);
        if (placedState.getBlockType() != BlockTypes.END_PORTAL_FRAME) return;

        var direction = placedState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        // The same facing can describe any of three positions along one ring side,
        // so test all implied centers instead of assuming the clicked frame is
        // centered.
        var centers = computePossibleCenters(placedPos, direction);

        for (var center : centers) {
            if (validatePortalFormation(dimension, center)) {
                placeEndPortal(dimension, center);
                return;
            }
        }
    }

    /**
     * Returns three newly allocated candidate centers for the frame position and
     * facing. Each candidate lies two blocks inward and covers one possible offset
     * along the three-frame side.
     */
    private Vector3ic[] computePossibleCenters(Vector3ic pos, MinecraftCardinalDirection direction) {
        int x = pos.x(), y = pos.y(), z = pos.z();
        return switch (direction) {
            case SOUTH -> new Vector3ic[]{
                    new Vector3i(x - 1, y, z + 2),
                    new Vector3i(x, y, z + 2),
                    new Vector3i(x + 1, y, z + 2)
            };
            case NORTH -> new Vector3ic[]{
                    new Vector3i(x - 1, y, z - 2),
                    new Vector3i(x, y, z - 2),
                    new Vector3i(x + 1, y, z - 2)
            };
            case EAST -> new Vector3ic[]{
                    new Vector3i(x + 2, y, z - 1),
                    new Vector3i(x + 2, y, z),
                    new Vector3i(x + 2, y, z + 1)
            };
            case WEST -> new Vector3ic[]{
                    new Vector3i(x - 2, y, z - 1),
                    new Vector3i(x - 2, y, z),
                    new Vector3i(x - 2, y, z + 1)
            };
        };
    }

    /**
     * Returns whether all twelve required frames contain eyes and face inward.
     * {@code FRAME_OFFSETS} supplies the positions and direction indices; the
     * three-by-three interior and four corners impose no constraints here.
     */
    private boolean validatePortalFormation(Dimension dimension, Vector3ic center) {
        int cx = center.x(), cy = center.y(), cz = center.z();

        for (var offset : FRAME_OFFSETS) {
            int fx = cx + offset[0];
            int fz = cz + offset[1];
            var expectedDir = DIRECTIONS[offset[2]];

            var state = dimension.getBlockState(fx, cy, fz);
            if (state.getBlockType() != BlockTypes.END_PORTAL_FRAME) return false;
            if (!state.getPropertyValue(END_PORTAL_EYE_BIT)) return false;
            if (state.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION) != expectedDir) return false;
        }

        return true;
    }

    /**
     * Replaces the candidate's three-by-three interior with End portal blocks and
     * plays the formation sound. The caller validates the frame first; write
     * failures are ignored and successful earlier writes are retained.
     */
    private void placeEndPortal(Dimension dimension, Vector3ic center) {
        int cx = center.x(), cy = center.y(), cz = center.z();
        var endPortalState = BlockTypes.END_PORTAL.getDefaultState();

        // The inclusive offsets cover all nine cells of the portal interior.
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                dimension.setBlockState(cx + dx, cy, cz + dz, endPortalState);
            }
        }

        dimension.addSound(MathUtils.center(center), SimpleSound.END_PORTAL_SPAWN);
    }

    /**
     * Returns whether this frame supplies its own comparator signal.
     */
    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * Returns full comparator strength when the frame contains an eye.
     */
    @Override
    public int getComparatorInputOverride(Block block) {
        return block.getPropertyValue(END_PORTAL_EYE_BIT) ? 15 : 0;
    }
}
