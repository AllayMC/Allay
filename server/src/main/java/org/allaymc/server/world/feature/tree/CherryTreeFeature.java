package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code CherryTreeFeature} places cherry trees with a sampled trunk height,
 * one or two branches, and layered hanging foliage. Placement checks ground
 * and clearance before selecting branch starts and deciding whether the main
 * trunk also gets a top attachment.
 *
 * <p>
 * Branch count controls trunk height so branch attachments and trunk
 * termination remain related. Each branch moves toward a sampled target using
 * vertical probability derived from its remaining Manhattan distance, and
 * horizontal logs receive the corresponding pillar axis. Foliage combines
 * clipped rows, random holes, and downward extensions limited by distance from
 * the row origin. Individual replacement checks can leave gaps without rolling
 * back already placed logs or leaves.
 *
 * @author daoge_cmd
 */
public class CherryTreeFeature extends TreeWorldFeature {

    /**
     * Built-in cherry tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:cherry_tree");

    /**
     * Creates and immediately registers the built-in cherry tree feature. The
     * constructor fixes its log and leaf materials while shared clearance and
     * replacement behavior remains in the tree base.
     */
    public CherryTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.CHERRY_LOG,
                BlockTypes.CHERRY_LEAVES
        );
    }

    /**
     * Attempts a cherry tree after ground and clearance checks, returning
     * {@code false} before mutation when either fails. A sampled branch count
     * then controls trunk termination, secondary-branch presence, and an
     * optional top canopy attachment.
     *
     * <p>
     * Branch starts are kept distinct before the trunk height is chosen,
     * preventing those choices from being made independently of their
     * attachment levels. The trunk is written before branches, and each
     * completed branch supplies the foliage origin used by later canopy
     * passes. Replacement failures can leave gaps in branches or leaves
     * without rejecting the completed attempt, and exceptions do not restore
     * earlier terrain.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(7, 1, 0);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 1 ? 0 : 2, true);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        placeDirtUnder(context, x, y - 1, z);

        int firstBranchStart = Math.max(0, maxFreeTreeHeight - 1 + sampleUniform(random, -4, -3));
        int secondBranchStart = Math.max(0, maxFreeTreeHeight - 1 - 4);
        // Keep the two start heights distinct before branch count chooses trunk
        // termination, so both branch attachments do not collapse onto one level.
        if (secondBranchStart >= firstBranchStart) {
            secondBranchStart++;
        }

        int branchCount = random.nextInt(3) + 1;
        boolean placeTopAttachment = branchCount == 3;
        boolean placeSecondBranch = branchCount >= 2;
        int trunkHeight;
        if (placeTopAttachment) {
            trunkHeight = maxFreeTreeHeight;
        } else if (placeSecondBranch) {
            trunkHeight = Math.max(firstBranchStart, secondBranchStart) + 1;
        } else {
            trunkHeight = firstBranchStart + 1;
        }

        for (int dy = 0; dy < trunkHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        var attachments = new ArrayList<FoliageAttachment>();
        if (placeTopAttachment) {
            attachments.add(new FoliageAttachment(x, y + trunkHeight, z, 0, false));
        }

        var horizontalFaces = BlockFace.getHorizontalBlockFaces();
        BlockFace branchDirection = horizontalFaces[random.nextInt(horizontalFaces.length)];
        attachments.add(generateBranch(context, x, y, z, maxFreeTreeHeight, branchDirection, firstBranchStart, firstBranchStart < trunkHeight - 1));
        if (placeSecondBranch) {
            attachments.add(generateBranch(context, x, y, z, maxFreeTreeHeight, branchDirection.opposite(), secondBranchStart, secondBranchStart < trunkHeight - 1));
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var attachment : attachments) {
            placeCherryFoliage(context, attachment, 5, 4, 0, 0.25f, 0.5f, 0.16666667f, 0.33333334f, placedLeaves);
        }
        return true;
    }

    /**
     * Places clipped cherry canopy rows and lower rows with possible hanging
     * extensions. The supplied hole and extension probabilities are used
     * directly, and accepted leaf positions accumulate in the caller's list.
     *
     * <p>
     * Upper rows use reduced radii, middle rows use the full radius, and the
     * two lowest rows delegate hanging-leaf placement. A shared skipper
     * combines layer height, edge distance, and random corner selection, so
     * holes and shape clipping interact at each candidate. The attachment's
     * trunk width is preserved through the shared row helper rather than
     * recomputed by each layer.
     */
    private void placeCherryFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            float wideBottomLayerHoleChance,
            float cornerHoleChance,
            float hangingLeavesChance,
            float hangingLeavesExtensionChance,
            List<Vector3i> placedLeaves
    ) {
        int baseX = attachment.x();
        int baseY = attachment.y() + offset;
        int baseZ = attachment.z();
        int range = foliageRadius + attachment.radiusOffset() - 1;

        RowSkipper cherrySkipper = (random, coord, localY, rowRange, large) -> {
            if (localY == -1 &&
                (coord.localX() == rowRange || coord.localZ() == rowRange) &&
                random.nextFloat() < wideBottomLayerHoleChance) {
                return true;
            }
            boolean isCorner = coord.localX() == rowRange && coord.localZ() == rowRange;
            // Wider rows always omit the extreme corner and sample additional near-
            // corner holes.
            if (rowRange > 2) {
                return isCorner ||
                       (coord.localX() + coord.localZ() > rowRange * 2 - 2 && random.nextFloat() < cornerHoleChance);
            }
            return isCorner && random.nextFloat() < cornerHoleChance;
        };

        placeLeavesRow(context, baseX, baseY, baseZ, range - 2, foliageHeight - 3, attachment.doubleTrunk(), cherrySkipper, placedLeaves);
        placeLeavesRow(context, baseX, baseY, baseZ, range - 1, foliageHeight - 4, attachment.doubleTrunk(), cherrySkipper, placedLeaves);
        for (int localY = foliageHeight - 5; localY >= 0; localY--) {
            placeLeavesRow(context, baseX, baseY, baseZ, range, localY, attachment.doubleTrunk(), cherrySkipper, placedLeaves);
        }
        placeLeavesRowWithHangingLeavesBelow(
                context,
                baseX,
                baseY,
                baseZ,
                range,
                -1,
                attachment.doubleTrunk(),
                cherrySkipper,
                hangingLeavesChance,
                hangingLeavesExtensionChance,
                placedLeaves
        );
        placeLeavesRowWithHangingLeavesBelow(
                context,
                baseX,
                baseY,
                baseZ,
                range - 1,
                -2,
                attachment.doubleTrunk(),
                cherrySkipper,
                hangingLeavesChance,
                hangingLeavesExtensionChance,
                placedLeaves
        );
    }

    /**
     * Places a cherry foliage row, then tests its perimeter for downward leaf
     * extensions. Only current leaves can start an extension, and a second
     * lower block is attempted only after the first one succeeds.
     *
     * <p>
     * Perimeter traversal accounts for the extra positive column around a
     * large trunk, keeping edges aligned with the shared row geometry. Both
     * extension attempts use the same origin below the row base for their
     * distance bound, but they use separate probabilities. Accepted positions
     * are appended through the shared leaf helper; an obstructed or rejected
     * first extension prevents its second attempt.
     */
    private void placeLeavesRowWithHangingLeavesBelow(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            int range,
            int localY,
            boolean large,
            RowSkipper skipper,
            float hangingLeavesChance,
            float hangingLeavesExtensionChance,
            List<Vector3i> placedLeaves
    ) {
        placeLeavesRow(context, x, y, z, range, localY, large, skipper, placedLeaves);

        int extra = large ? 1 : 0;
        int leafY = y + localY;
        int baseBelowY = y - 1;
        var random = ThreadLocalRandom.current();
        for (var direction : BlockFace.getHorizontalBlockFaces()) {
            var cw = direction.rotateY();
            int edge = (cw.getOffset().x() + cw.getOffset().z() > 0) ? range + extra : range;
            int cursorX = x + cw.getOffset().x() * edge + direction.getOffset().x() * -range;
            int cursorZ = z + cw.getOffset().z() * edge + direction.getOffset().z() * -range;
            for (int i = -range; i < range + extra; i++) {
                if (isLeaves(context, cursorX, leafY, cursorZ) &&
                    tryPlaceLeafExtension(context, random, cursorX, leafY - 1, cursorZ, x, baseBelowY, z, hangingLeavesChance, placedLeaves)) {
                    tryPlaceLeafExtension(context, random, cursorX, leafY - 2, cursorZ, x, baseBelowY, z, hangingLeavesExtensionChance, placedLeaves);
                }
                cursorX += direction.getOffset().x();
                cursorZ += direction.getOffset().z();
            }
        }
    }

    /**
     * Attempts a leaf extension only within Manhattan distance six of the
     * supplied origin. The random threshold is then checked before ordinary
     * replacement; rejection returns {@code false} without appending to the
     * accepted-leaf list.
     */
    private boolean tryPlaceLeafExtension(
            WorldFeatureContext context,
            ThreadLocalRandom random,
            int x,
            int y,
            int z,
            int originX,
            int originY,
            int originZ,
            float chance,
            List<Vector3i> placedLeaves
    ) {
        // The Manhattan bound keeps hanging extensions near their originating
        // canopy.
        if (Math.abs(x - originX) + Math.abs(y - originY) + Math.abs(z - originZ) >= 7) {
            return false;
        }
        if (random.nextFloat() > chance) {
            return false;
        }
        return tryPlaceLeaves(context, x, y, z, placedLeaves);
    }

    /**
     * Writes a branch toward a sampled horizontal and vertical target and
     * returns its canopy attachment. Horizontal log placement is conditional
     * on replaceability, while vertical branch steps use the direct log
     * writer.
     *
     * <p>
     * An extended branch begins with an extra horizontal segment. Later steps
     * choose vertical movement with probability equal to the remaining
     * vertical distance divided by total Manhattan distance, making each move
     * reduce the distance to the target. Horizontal segments use the axis
     * matching their direction, and completion returns the position one block
     * above the target for subsequent foliage placement.
     */
    private FoliageAttachment generateBranch(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            int treeHeight,
            BlockFace direction,
            int branchStart,
            boolean doubleBranch
    ) {
        var random = ThreadLocalRandom.current();
        int currentX = x;
        int currentY = y + branchStart;
        int currentZ = z;
        // Branch height and horizontal length are sampled together before the
        // remaining-distance movement rule chooses individual steps.
        int branchEndY = treeHeight - 1 + sampleUniform(random, -1, 0);
        boolean extended = doubleBranch || branchEndY < branchStart;
        int horizontalLength = sampleUniform(random, 2, 4) + (extended ? 1 : 0);
        int targetX = x + direction.getOffset().x() * horizontalLength;
        int targetY = y + branchEndY;
        int targetZ = z + direction.getOffset().z() * horizontalLength;
        int firstSteps = extended ? 2 : 1;
        PillarAxis horizontalAxis = direction.getOffset().x() != 0 ? PillarAxis.X : PillarAxis.Z;

        for (int i = 0; i < firstSteps; i++) {
            currentX += direction.getOffset().x();
            currentZ += direction.getOffset().z();
            placeLogWithAxisIfValid(context, currentX, currentY, currentZ, horizontalAxis);
        }

        int verticalStep = targetY > currentY ? 1 : -1;
        while (true) {
            int distance = Math.abs(targetX - currentX) + Math.abs(targetY - currentY) + Math.abs(targetZ - currentZ);
            if (distance == 0) {
                return new FoliageAttachment(targetX, targetY + 1, targetZ, 0, false);
            }

            // Weight vertical movement by the remaining vertical share so each sampled
            // move reduces the same target distance without overshooting an already
            // completed axis.
            float verticalChance = (float) Math.abs(targetY - currentY) / distance;
            boolean moveVertical = random.nextFloat() < verticalChance;
            if (moveVertical) {
                currentY += verticalStep;
                placeLog(context, currentX, currentY, currentZ);
            } else {
                currentX += direction.getOffset().x();
                currentZ += direction.getOffset().z();
                placeLogWithAxisIfValid(context, currentX, currentY, currentZ, horizontalAxis);
            }
        }
    }

    /**
     * Returns an integer sampled uniformly from the inclusive supplied bounds.
     * The caller must provide an ordered range whose inclusive width fits a
     * positive integer bound; this helper delegates invalid bounds to the
     * random generator.
     */
    private int sampleUniform(ThreadLocalRandom random, int min, int max) {
        // Adding one includes the configured upper endpoint in the sampled range.
        return random.nextInt(max - min + 1) + min;
    }
}
