package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Mangrove tree feature implementation.
 * Generates mangrove trees with exposed roots.
 *
 * @author daoge_cmd
 */
public class MangroveTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:mangrove_tree");

    public MangroveTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.MANGROVE_LOG,
                BlockTypes.MANGROVE_LEAVES,
                BlockTypes.MANGROVE_PROPAGULE,
                8, 11
        );
    }

    @Override
    protected boolean canGrowOn(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return super.canGrowOn(blockState) ||
               blockType == BlockTypes.WATER ||
               blockType == BlockTypes.MUD ||
               blockType == BlockTypes.MUDDY_MANGROVE_ROOTS;
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight();

        // Check trunk space
        for (int dy = 3; dy < height; dy++) {
            if (!canPlaceLog(context, x, y + dy, z)) {
                return false;
            }
        }

        int topY = y + height;

        // Place roots and trunk following NKMOT structure
        for (int dy = 0; dy <= height; dy++) {
            if (dy > 2) {
                // Log at center
                placeLog(context, x, y + dy, z);
            } else {
                // Roots at cardinal directions for first 3 blocks
                placeRoot(context, x + 1, y + dy, z);
                placeRoot(context, x - 1, y + dy, z);
                placeRoot(context, x, y + dy, z + 1);
                placeRoot(context, x, y + dy, z - 1);
            }
        }

        // Outer roots extending 2 blocks at y level
        placeRoot(context, x + 2, y, z);
        placeRoot(context, x - 2, y, z);
        placeRoot(context, x, y, z + 2);
        placeRoot(context, x, y, z - 2);

        // Place leaves with propagules (using NKMOT's pattern)
        for (int dx = -2; dx <= 1; dx++) {
            for (int dz = -2; dz <= 1; dz++) {
                int offsetX = random.nextInt(2);
                int offsetY = random.nextInt(2);
                int offsetZ = random.nextInt(2);

                // Layer +1 with random offsets
                placeLeafWithPropagule(context, x + dx + offsetX, topY + 1 + offsetY, z + dz + offsetZ, random);
                placeLeafWithPropagule(context, x - dx + offsetX, topY + 1 + offsetY, z + dz + offsetZ, random);
                placeLeafWithPropagule(context, x + dx + offsetX, topY + 1 + offsetY, z - dz + offsetZ, random);
                placeLeafWithPropagule(context, x - dx + offsetX, topY + 1 + offsetY, z - dz + offsetZ, random);

                // Layer 0
                placeLeafWithPropagule(context, x + dx, topY, z + dz, random);
                placeLeafWithPropagule(context, x - dx, topY, z + dz, random);
                placeLeafWithPropagule(context, x + dx, topY, z - dz, random);
                placeLeafWithPropagule(context, x - dx, topY, z - dz, random);

                // Layer +1 (no offset)
                placeLeafWithPropagule(context, x + dx, topY + 1, z + dz, random);
                placeLeafWithPropagule(context, x - dx, topY + 1, z + dz, random);
                placeLeafWithPropagule(context, x + dx, topY + 1, z - dz, random);
                placeLeafWithPropagule(context, x - dx, topY + 1, z - dz, random);

                // Layer +2 with negative random offsets
                offsetX = random.nextInt(2) - 1;
                offsetY = random.nextInt(2) - 1;
                offsetZ = random.nextInt(2) - 1;
                placeLeafWithPropagule(context, x + dx + offsetX, topY + 2 + offsetY, z + dz + offsetZ, random);
                placeLeafWithPropagule(context, x - dx + offsetX, topY + 2 + offsetY, z + dz + offsetZ, random);
                placeLeafWithPropagule(context, x + dx + offsetX, topY + 2 + offsetY, z - dz + offsetZ, random);
                placeLeafWithPropagule(context, x - dx + offsetX, topY + 2 + offsetY, z - dz + offsetZ, random);
            }
        }

        return true;
    }

    /**
     * Place a mangrove root block.
     */
    protected void placeRoot(WorldFeatureContext context, int x, int y, int z) {
        var existing = context.getBlockState(x, y, z);
        if (canReplace(existing) || existing.getBlockType() == BlockTypes.WATER ||
            existing.getBlockType() == BlockTypes.MANGROVE_LEAVES ||
            existing.getBlockType() == BlockTypes.MANGROVE_PROPAGULE) {
            context.setBlockState(x, y, z, BlockTypes.MANGROVE_ROOTS.getDefaultState());
        }
    }

    /**
     * Place a leaf block with potential hanging propagule.
     */
    protected void placeLeafWithPropagule(WorldFeatureContext context, int x, int y, int z, ThreadLocalRandom random) {
        var existing = context.getBlockState(x, y, z);
        if (existing.getBlockType() == BlockTypes.AIR) {
            context.setBlockState(x, y, z, leavesType.getDefaultState());
            // 1/8 chance to place propagule below
            if (random.nextInt(8) == 0) {
                var below = context.getBlockState(x, y - 1, z);
                if (below.getBlockType() == BlockTypes.AIR) {
                    // Place hanging propagule with random stage (0-4)
                    var hangingPropagule = BlockTypes.MANGROVE_PROPAGULE.getDefaultState()
                            .setPropertyValue(BlockPropertyTypes.HANGING, true)
                            .setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, random.nextInt(5));
                    context.setBlockState(x, y - 1, z, hangingPropagule);
                }
            }
        }
    }

}
