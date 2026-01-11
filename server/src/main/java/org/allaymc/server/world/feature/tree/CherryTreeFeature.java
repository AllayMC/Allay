package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Cherry tree feature implementation.
 * Generates cherry blossom trees with spreading branches.
 * Can generate as small or big variants.
 *
 * @author daoge_cmd
 */
public class CherryTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:cherry_tree");
    private static final int LEAVES_RADIUS = 4;

    public CherryTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.CHERRY_LOG,
                BlockTypes.CHERRY_LEAVES,
                BlockTypes.CHERRY_SAPLING,
                4, 5
        );
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();

        // 50% chance for big tree
        if (random.nextBoolean()) {
            if (generateBigTree(context, x, y, z, random)) {
                return true;
            }
        }
        return generateSmallTree(context, x, y, z, random);
    }

    /**
     * Generate a big cherry tree with main trunk and two side branches.
     */
    protected boolean generateBigTree(WorldFeatureContext context, int x, int y, int z, ThreadLocalRandom random) {
        int mainTrunkHeight = (random.nextBoolean() ? 1 : 0) + 10;

        // Check space
        if (!canGenerate(context, x, y, z, mainTrunkHeight + 1)) {
            return false;
        }

        // Choose X or Z axis for growth
        boolean growOnXAxis = random.nextBoolean();
        int xMultiplier = growOnXAxis ? 1 : 0;
        int zMultiplier = growOnXAxis ? 0 : 1;

        int leftSideTrunkLength = random.nextInt(2) + 2;
        int leftSideTrunkHeight = random.nextInt(2) + 3;
        int leftSideTrunkStartY = random.nextInt(1) + 4;

        // Check left side space
        if (!canPlaceLog(context, x - leftSideTrunkLength * xMultiplier, y + leftSideTrunkStartY, z - leftSideTrunkLength * zMultiplier)) {
            // Try flipping axis
            growOnXAxis = !growOnXAxis;
            xMultiplier = growOnXAxis ? 1 : 0;
            zMultiplier = growOnXAxis ? 0 : 1;
            if (!canPlaceLog(context, x - leftSideTrunkLength * xMultiplier, y + leftSideTrunkStartY, z - leftSideTrunkLength * zMultiplier)) {
                return false;
            }
        }

        int rightSideTrunkLength = random.nextInt(2) + 2;
        int rightSideTrunkHeight = random.nextInt(2) + 3;
        int rightSideTrunkStartY = random.nextInt(1) + 4;

        // Place dirt
        placeDirtUnder(context, x, y - 1, z);

        // Generate main trunk
        for (int yy = 0; yy < mainTrunkHeight; yy++) {
            placeLog(context, x, y + yy, z);
        }

        // Generate left-side trunk
        for (int xx = 1; xx <= leftSideTrunkLength; xx++) {
            placeLogHorizontal(context, x - xx * xMultiplier, y + leftSideTrunkStartY, z - xx * zMultiplier, growOnXAxis);
        }
        for (int yy = 1; yy < leftSideTrunkHeight; yy++) {
            placeLog(context, x - leftSideTrunkLength * xMultiplier, y + leftSideTrunkStartY + yy, z - leftSideTrunkLength * zMultiplier);
        }

        // Generate right-side trunk
        for (int xx = 1; xx <= rightSideTrunkLength; xx++) {
            placeLogHorizontal(context, x + xx * xMultiplier, y + rightSideTrunkStartY, z + xx * zMultiplier, growOnXAxis);
        }
        for (int yy = 1; yy < rightSideTrunkHeight; yy++) {
            placeLog(context, x + rightSideTrunkLength * xMultiplier, y + rightSideTrunkStartY + yy, z + rightSideTrunkLength * zMultiplier);
        }

        // Generate leaves at trunk tops
        generateLeaves(context, x, y + mainTrunkHeight + 1, z, random);
        generateLeaves(context, x - leftSideTrunkLength * xMultiplier, y + leftSideTrunkStartY + leftSideTrunkHeight + 1,
                z - leftSideTrunkLength * zMultiplier, random);
        generateLeaves(context, x + rightSideTrunkLength * xMultiplier, y + rightSideTrunkStartY + rightSideTrunkHeight + 1,
                z + rightSideTrunkLength * zMultiplier, random);

        return true;
    }

    /**
     * Generate a small cherry tree with main trunk and one side branch.
     */
    protected boolean generateSmallTree(WorldFeatureContext context, int x, int y, int z, ThreadLocalRandom random) {
        int mainTrunkHeight = (random.nextBoolean() ? 1 : 0) + 4;
        int sideTrunkHeight = random.nextInt(2) + 3;

        // Check main trunk space
        if (!canGenerate(context, x, y, z, mainTrunkHeight + 1)) {
            return false;
        }

        // Find direction for side branch
        int growDirection = random.nextInt(4);
        int xMultiplier = 0;
        int zMultiplier = 0;
        boolean canPlace = false;

        for (int i = 0; i < 4; i++) {
            growDirection = (growDirection + 1) % 4;
            xMultiplier = switch (growDirection) {
                case 0 -> -1;
                case 1 -> 1;
                default -> 0;
            };
            zMultiplier = switch (growDirection) {
                case 2 -> -1;
                case 3 -> 1;
                default -> 0;
            };
            if (canPlaceLog(context, x + xMultiplier * sideTrunkHeight, y, z + zMultiplier * sideTrunkHeight)) {
                canPlace = true;
                break;
            }
        }

        if (!canPlace) {
            return false;
        }

        // Place dirt
        placeDirtUnder(context, x, y - 1, z);

        boolean growOnXAxis = xMultiplier != 0;

        // Generate main trunk
        for (int yy = 0; yy < mainTrunkHeight; yy++) {
            placeLog(context, x, y + yy, z);
        }

        // Generate side trunk with diagonal pattern
        for (int yy = 1; yy <= sideTrunkHeight; yy++) {
            int tmpX = x + yy * xMultiplier;
            int tmpY = y + mainTrunkHeight + yy - 2;
            int tmpZ = z + yy * zMultiplier;

            placeLogHorizontal(context, tmpX, tmpY, tmpZ, growOnXAxis);
            // Don't place vertical part for last blocks if tall
            if (yy == sideTrunkHeight - 1 && sideTrunkHeight > 3) {
                continue;
            }
            placeLog(context, tmpX, tmpY + 1, tmpZ);
        }

        // Generate leaves at branch top
        generateLeaves(context, x + sideTrunkHeight * xMultiplier, y + mainTrunkHeight + sideTrunkHeight,
                z + sideTrunkHeight * zMultiplier, random);

        return true;
    }

    /**
     * Generate spherical leaves cluster.
     */
    protected void generateLeaves(WorldFeatureContext context, int x, int y, int z, ThreadLocalRandom random) {
        for (int dy = -2; dy <= 2; dy++) {
            int currentRadius = LEAVES_RADIUS - Math.max(1, Math.abs(dy));
            for (int dx = -LEAVES_RADIUS; dx <= LEAVES_RADIUS; dx++) {
                for (int dz = -LEAVES_RADIUS; dz <= LEAVES_RADIUS; dz++) {
                    if (dx * dx + dz * dz > currentRadius * currentRadius) {
                        continue;
                    }
                    placeLeaves(context, x + dx, y + dy, z + dz);

                    // Extra leaves below at -2 layer (30% chance)
                    if (dy == -2 && random.nextInt(3) == 0) {
                        placeLeaves(context, x + dx, y + dy - 1, z + dz);
                    }
                }
            }
        }
    }

    /**
     * Place a horizontal log (for branches).
     */
    protected void placeLogHorizontal(WorldFeatureContext context, int x, int y, int z, boolean xAxis) {
        if (canPlaceLog(context, x, y, z)) {
            var axis = xAxis ? PillarAxis.X : PillarAxis.Z;
            var logState = logType.getDefaultState().setPropertyValue(BlockPropertyTypes.PILLAR_AXIS, axis);
            context.setBlockState(x, y, z, logState);
        }
    }
}
