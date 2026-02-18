package org.allaymc.server.block.component;

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
import org.joml.Vector3i;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.END_PORTAL_EYE_BIT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION;

/**
 * @author IWareQ | daoge_cmd
 */
public class BlockEndPortalFrameBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * End portal frame layout (top-down view, Y is constant):
     * <pre>
     *      .  F  F  F  .       row z-2  (facing SOUTH)
     *      F  .  .  .  F       row z-1  (W→EAST, E→WEST)
     *      F  .  .  .  F       row z    (center row)
     *      F  .  .  .  F       row z+1
     *      .  F  F  F  .       row z+2  (facing NORTH)
     * </pre>
     * Each offset is {dx, dz, required direction facing inward}.
     */
    private static final int[][] FRAME_OFFSETS = {
            // North side (z - 2): facing SOUTH
            {-1, -2, 0}, {0, -2, 0}, {1, -2, 0},
            // South side (z + 2): facing NORTH
            {-1, 2, 2}, {0, 2, 2}, {1, 2, 2},
            // West side (x - 2): facing EAST
            {-2, -1, 3}, {-2, 0, 3}, {-2, 1, 3},
            // East side (x + 2): facing WEST
            {2, -1, 1}, {2, 0, 1}, {2, 1, 1}
    };

    // Direction index to MinecraftCardinalDirection
    private static final MinecraftCardinalDirection[] DIRECTIONS = MinecraftCardinalDirection.values();

    public BlockEndPortalFrameBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

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

        // Update comparators that may be reading this end portal frame
        dimension.updateComparatorOutputLevel(interactInfo.clickedBlockPos());

        // Try to form the end portal
        tryFormEndPortal(dimension, interactInfo.clickedBlockPos());

        return true;
    }

    /**
     * Try to form a complete end portal centered around the placed frame block.
     * The placed frame block could be on any of the 4 sides, so we determine
     * which side it's on from its direction and compute the possible center.
     */
    private void tryFormEndPortal(Dimension dimension, Vector3ic placedPos) {
        var placedState = dimension.getBlockState(placedPos);
        if (placedState.getBlockType() != BlockTypes.END_PORTAL_FRAME) return;

        var direction = placedState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);
        // From the direction of the placed frame, determine the center of the portal
        // Frame facing SOUTH → it's on the north side → center is at z + 2
        // Frame facing NORTH → it's on the south side → center is at z - 2
        // Frame facing EAST  → it's on the west side → center is at x + 2
        // Frame facing WEST  → it's on the east side → center is at x - 2
        var centers = computePossibleCenters(placedPos, direction);

        for (var center : centers) {
            if (validatePortalFormation(dimension, center)) {
                placeEndPortal(dimension, center);
                return;
            }
        }
    }

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

    private void placeEndPortal(Dimension dimension, Vector3ic center) {
        int cx = center.x(), cy = center.y(), cz = center.z();
        var endPortalState = BlockTypes.END_PORTAL.getDefaultState();

        // Place 3×3 end portal blocks
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                dimension.setBlockState(cx + dx, cy, cz + dz, endPortalState);
            }
        }

        dimension.addSound(MathUtils.center(center), SimpleSound.END_PORTAL_SPAWN);
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        // Output 15 if eye is placed, 0 otherwise
        return block.getPropertyValue(END_PORTAL_EYE_BIT) ? 15 : 0;
    }
}
