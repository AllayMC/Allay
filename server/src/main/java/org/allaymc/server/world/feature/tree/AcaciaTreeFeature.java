package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code AcaciaTreeFeature} places acacia trees with a bending trunk, an
 * optional secondary branch, and flat foliage. Placement first checks
 * dirt-tagged ground and a clearance profile, then uses successful log
 * placements to locate canopy attachments.
 *
 * <p>
 * The main trunk can move horizontally near its top, and the secondary branch
 * is omitted when its sampled direction matches the main bend. Their
 * attachments use different radius adjustments but share the same two-row
 * acacia foliage rules. Existing nonreplaceable positions can interrupt
 * individual logs or leaves after admission without rejecting the completed
 * attempt. The context controls chunk availability and can discard writes
 * beyond its source.
 *
 * @author daoge_cmd
 */
public class AcaciaTreeFeature extends TreeWorldFeature {

    /**
     * Built-in acacia tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:acacia_tree");

    /**
     * Creates and immediately registers the built-in acacia tree feature. The
     * constructor fixes its log and leaf materials while shared clearance and
     * replacement behavior remains in the tree base.
     */
    public AcaciaTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.ACACIA_LOG,
                BlockTypes.ACACIA_LEAVES
        );
    }

    /**
     * Attempts a bending acacia tree after checking dirt-tagged ground and the
     * requested height's clearance profile. Failed admission returns {@code
     * false} before soil or trunk writes, while accepted placement proceeds
     * incrementally through the context.
     *
     * <p>
     * The main bend direction and successful top log determine its large
     * canopy attachment. A differently directed secondary branch can add
     * another attachment only when at least one of its logs is accepted. Flat
     * foliage is then placed for both attachments using their separate radius
     * offsets. Individual rejected writes do not change the final {@code true}
     * result, and later exceptions do not undo earlier mutations.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(5, 2, 2);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 1 ? 0 : 2, true);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        placeDirtUnder(context, x, y - 1, z);

        var attachments = new ArrayList<FoliageAttachment>();
        int currentX = x;
        int currentZ = z;
        var horizontalFaces = BlockFace.getHorizontalBlockFaces();
        BlockFace primaryDirection = horizontalFaces[random.nextInt(4)];
        // The sampled bend starts near the trunk top, keeping lower trunk levels
        // on the original column.
        int bendStart = maxFreeTreeHeight - random.nextInt(4) - 1;
        // At most three horizontal steps limit the main trunk's lateral drift.
        int bendLength = 3 - random.nextInt(3);
        int lastPlacedY = y;

        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            int logY = y + dy;
            if (dy >= bendStart && bendLength > 0) {
                currentX += primaryDirection.getOffset().x();
                currentZ += primaryDirection.getOffset().z();
                bendLength--;
            }

            if (placeLogIfValid(context, currentX, logY, currentZ)) {
                lastPlacedY = logY + 1;
            }
        }

        attachments.add(new FoliageAttachment(currentX, lastPlacedY, currentZ, 1, false));

        BlockFace secondaryDirection = horizontalFaces[random.nextInt(4)];
        if (secondaryDirection != primaryDirection) {
            int secondaryStart = bendStart - random.nextInt(2) - 1;
            int secondaryLength = 1 + random.nextInt(3);
            currentX = x;
            currentZ = z;
            int secondaryTopY = -1;

            for (int dy = secondaryStart; dy < maxFreeTreeHeight && secondaryLength > 0; secondaryLength--, dy++) {
                if (dy >= 1) {
                    int logY = y + dy;
                    currentX += secondaryDirection.getOffset().x();
                    currentZ += secondaryDirection.getOffset().z();
                    if (placeLogIfValid(context, currentX, logY, currentZ)) {
                        secondaryTopY = logY + 1;
                    }
                }
            }
            // A secondary canopy needs an accepted branch log; an entirely rejected
            // branch must not create a detached foliage attachment.
            if (secondaryTopY >= 0) {
                attachments.add(new FoliageAttachment(currentX, secondaryTopY, currentZ, 0, false));
            }
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var attachment : attachments) {
            placeAcaciaFoliage(context, attachment, 2, 0, placedLeaves);
        }
        return true;
    }

    /**
     * Places the broad lower acacia row and overlapping smaller upper rows for
     * one attachment. Radius offsets affect their extents, while the shared
     * row helper handles trunk width and records accepted leaves in the
     * supplied list.
     */
    private void placeAcaciaFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        int baseX = attachment.x();
        int baseY = attachment.y() + offset;
        int baseZ = attachment.z();
        boolean large = attachment.doubleTrunk();

        placeLeavesRow(
                context,
                baseX,
                baseY,
                baseZ,
                foliageRadius + attachment.radiusOffset(),
                -1,
                large,
                (ignored, coord, rowY, rowRange, isLarge) ->
                        coord.localX() == rowRange && coord.localZ() == rowRange && rowRange > 0,
                placedLeaves
        );
        placeLeavesRow(
                context,
                baseX,
                baseY,
                baseZ,
                foliageRadius - 1,
                0,
                large,
                (ignored, coord, rowY, rowRange, isLarge) ->
                        (coord.localX() > 1 || coord.localZ() > 1) && coord.localX() != 0 && coord.localZ() != 0,
                placedLeaves
        );
        placeLeavesRow(
                context,
                baseX,
                baseY,
                baseZ,
                foliageRadius + attachment.radiusOffset() - 1,
                0,
                large,
                (ignored, coord, rowY, rowRange, isLarge) ->
                        (coord.localX() > 1 || coord.localZ() > 1) && coord.localX() != 0 && coord.localZ() != 0,
                placedLeaves
        );
    }
}
