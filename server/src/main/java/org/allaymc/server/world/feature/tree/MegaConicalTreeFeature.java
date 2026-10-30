package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code MegaConicalTreeFeature} places configurable two-by-two conifer trunks
 * with shared mega-pine foliage and podzol decoration. Ground and clearance
 * checks precede writes, while the constructor selects materials and a
 * foliage-height distribution to distinguish the built-in mega pine and spruce
 * variants.
 *
 * @author daoge_cmd
 */
public class MegaConicalTreeFeature extends TreeWorldFeature {

    /**
     * Built-in mega pine tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier MEGA_PINE_IDENTIFIER = new Identifier("minecraft:mega_pine_tree");
    /**
     * Built-in mega spruce tree registry key. Runtime feature construction
     * uses this identifier to register the corresponding placement
     * implementation.
     */
    public static final Identifier MEGA_SPRUCE_IDENTIFIER = new Identifier("minecraft:mega_spruce_tree");

    // foliageHeightBase and foliageHeightRand select the cone height as a base
    // plus an exclusive random increment. The random bound must be positive
    // when placement samples it.
    /**
     * Base conical foliage height in blocks.
     */
    private final int foliageHeightBase;
    /**
     * Exclusive random foliage-height increment bound.
     */
    private final int foliageHeightRand;

    /**
     * Creates and registers a tree feature retaining the supplied identifier
     * and materials. Registration occurs before subclass configuration is
     * assigned, and numeric shape parameters are retained for later placement
     * without validation.
     */
    public MegaConicalTreeFeature(
            Identifier identifier,
            BlockType<?> logType,
            BlockType<?> leavesType,
            int foliageHeightBase,
            int foliageHeightRand
    ) {
        super(identifier, logType, leavesType);
        this.foliageHeightBase = foliageHeightBase;
        this.foliageHeightRand = foliageHeightRand;
    }

    /**
     * Attempts a two-by-two conifer after checking all four ground cells and
     * vine-sensitive clearance. Acceptance writes the trunk, delegates the
     * sampled-height cone to shared foliage logic, and converts nearby
     * eligible soil to podzol.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(13, 2, 14);

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                if (!canGrowOn(context.getBlockState(x + dx, y - 1, z + dz))) {
                    return false;
                }
            }
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(
                context,
                height,
                x,
                y,
                z,
                (treeHeight, currentHeight) -> currentHeight < 1 ? 1 : 2,
                false
        );
        if (maxFreeTreeHeight < height) {
            return false;
        }

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                placeDirtUnder(context, x + dx, y - 1, z + dz);
            }
        }

        var placedLogs = new ArrayList<Vector3i>();
        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            placeLogIfValid(context, x, y + dy, z, placedLogs);
            if (dy < maxFreeTreeHeight - 1) {
                placeLogIfValid(context, x + 1, y + dy, z, placedLogs);
                placeLogIfValid(context, x + 1, y + dy, z + 1, placedLogs);
                placeLogIfValid(context, x, y + dy, z + 1, placedLogs);
            }
        }

        var placedLeaves = new ArrayList<Vector3i>();
        placeMegaPineFoliage(
                context,
                new FoliageAttachment(x, y + maxFreeTreeHeight, z, 0, true),
                // The foliage increment excludes its upper bound, distinguishing this
                // sampler from trunk-height increments.
                foliageHeightBase + random.nextInt(foliageHeightRand),
                0,
                0,
                placedLeaves
        );
        placePodzol(context, placedLogs);
        return true;
    }
}
