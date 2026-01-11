package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Fancy oak tree feature implementation.
 * Generates large oak trees with branching structure.
 *
 * @author daoge_cmd
 */
public class FancyOakTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:fancy_oak_tree");

    public FancyOakTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.OAK_LOG,
                BlockTypes.OAK_LEAVES,
                BlockTypes.OAK_SAPLING,
                8, 14
        );
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight();

        // Check if tree can generate
        if (!canGenerate(context, x, y, z, height)) {
            return false;
        }

        // Place dirt under the tree
        placeDirtUnder(context, x, y - 1, z);

        // Place main trunk
        int trunkHeight = height - 4;
        for (int dy = 0; dy < trunkHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        // Generate branches
        generateBranches(context, x, y + trunkHeight, z, height - trunkHeight);

        // Try to place bee nest (5% chance)
        if (random.nextFloat() < 0.05f) {
            tryPlaceBeeNest(context, x, y + trunkHeight - 1, z, random.nextInt(4));
        }

        return true;
    }

    /**
     * Generate the branching structure of the fancy oak.
     */
    protected void generateBranches(WorldFeatureContext context, int x, int y, int z, int canopyHeight) {
        var random = ThreadLocalRandom.current();

        // Center trunk continues up
        for (int dy = 0; dy < canopyHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        // Generate 3-5 main branches
        int branchCount = 3 + random.nextInt(3);
        double angleStep = Math.PI * 2 / branchCount;
        double startAngle = random.nextDouble() * Math.PI * 2;

        for (int i = 0; i < branchCount; i++) {
            double angle = startAngle + angleStep * i;
            int branchY = y + random.nextInt(canopyHeight - 1);
            int length = 2 + random.nextInt(3);

            // Calculate branch direction
            int dirX = (int) Math.round(Math.cos(angle));
            int dirZ = (int) Math.round(Math.sin(angle));

            // Place branch
            int bx = x;
            int bz = z;
            for (int j = 0; j < length; j++) {
                bx += dirX;
                bz += dirZ;
                if (j == length - 1) {
                    branchY++;
                }
                placeLog(context, bx, branchY, bz);
            }

            // Place leaves around branch end
            placeLeavesBlob(context, bx, branchY + 1, bz, 2);
        }

        // Place main canopy at top
        placeLeavesBlob(context, x, y + canopyHeight, z, 3);
    }

    /**
     * Place a spherical blob of leaves.
     */
    protected void placeLeavesBlob(WorldFeatureContext context, int x, int y, int z, int radius) {
        var random = ThreadLocalRandom.current();

        for (int dy = -radius; dy <= radius; dy++) {
            int layerRadius = (int) Math.sqrt(radius * radius - dy * dy);
            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                    if (dist <= radius + 0.5) {
                        if (dist > radius - 0.5 && random.nextFloat() < 0.3f) {
                            continue;
                        }
                        placeLeaves(context, x + dx, y + dy, z + dz);
                    }
                }
            }
        }
    }

    /**
     * Try to place a bee nest on the side of the tree.
     */
    protected void tryPlaceBeeNest(WorldFeatureContext context, int x, int y, int z, int side) {
        int beeX = x;
        int beeZ = z;
        switch (side) {
            case 0 -> beeZ -= 1;
            case 1 -> beeZ += 1;
            case 2 -> beeX -= 1;
            case 3 -> beeX += 1;
        }

        var blockState = context.getBlockState(beeX, y, beeZ);
        if (blockState.getBlockType() == leavesType ||
            blockState.getBlockType() == BlockTypes.AIR) {
            context.setBlockState(beeX, y, beeZ, BlockTypes.BEE_NEST.getDefaultState());
        }
    }
}
