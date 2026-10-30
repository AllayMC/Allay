package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;

/**
 * {@code StraightBlobTreeFeature} places a straight trunk and a shared blob
 * canopy using configurable materials and height ranges. The runtime reuses
 * this shape for oak, birch, jungle, and tall birch by supplying different
 * identifiers, block types, and inclusive height increments.
 *
 * @author daoge_cmd
 */
public class StraightBlobTreeFeature extends TreeWorldFeature {

    /**
     * Built-in oak tree registry key. Runtime feature construction uses this
     * identifier to register the corresponding placement implementation.
     */
    public static final Identifier OAK_IDENTIFIER = new Identifier("minecraft:oak_tree");
    /**
     * Built-in birch tree registry key. Runtime feature construction uses this
     * identifier to register the corresponding placement implementation.
     */
    public static final Identifier BIRCH_IDENTIFIER = new Identifier("minecraft:birch_tree");
    /**
     * Built-in jungle tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier JUNGLE_IDENTIFIER = new Identifier("minecraft:jungle_tree");
    /**
     * Built-in tall birch tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier TALL_BIRCH_IDENTIFIER = new Identifier("minecraft:tall_birch_tree");

    // baseHeight, heightRandA, and heightRandB select trunk height through the
    // shared additive sampler. Both increment bounds must be nonnegative and
    // remain valid after adding one.
    /**
     * Base trunk height in blocks.
     */
    private final int baseHeight;
    /**
     * First inclusive random height increment.
     */
    private final int heightRandA;
    /**
     * Second inclusive random height increment.
     */
    private final int heightRandB;

    /**
     * Creates and registers a tree feature retaining the supplied identifier
     * and materials. Registration occurs before subclass configuration is
     * assigned, and numeric shape parameters are retained for later placement
     * without validation.
     */
    public StraightBlobTreeFeature(
            Identifier identifier,
            BlockType<?> logType,
            BlockType<?> leavesType,
            int baseHeight,
            int heightRandA,
            int heightRandB
    ) {
        super(identifier, logType, leavesType);
        this.baseHeight = baseHeight;
        this.heightRandA = heightRandA;
        this.heightRandB = heightRandB;
    }

    /**
     * Attempts a configured straight tree after ground and clearance checks,
     * returning {@code false} before mutation on rejection. Acceptance
     * normalizes the soil, writes the full straight trunk, and delegates a
     * three-block-deep blob canopy to shared foliage logic.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        int height = calculateHeight(baseHeight, heightRandA, heightRandB);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 1 ? 0 : 1, true);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        placeDirtUnder(context, x, y - 1, z);

        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            placeLog(context, x, y + dy, z);
        }

        placeBlobFoliage(context, new FoliageAttachment(x, y + maxFreeTreeHeight, z, 0, false), 3, 2, 0, new ArrayList<>());
        return true;
    }
}
