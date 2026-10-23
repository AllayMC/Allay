package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code DoubleTrunkTreeFeature} places dark-oak or pale-oak trees with a
 * two-by-two bending trunk and dark-oak foliage. The constructor selects the
 * identifier and materials, while placement validates all four ground
 * positions and a shared clearance profile.
 *
 * <p>
 * The main canopy uses a large attachment at the bent trunk top, and optional
 * surrounding downward branches receive smaller attachments at their own
 * positions. Foliage geometry therefore follows trunk width rather than merely
 * sharing a species name. The admission scan and later replacement checks
 * serve different purposes: admission can reject the whole attempt, while
 * blocked individual logs or leaves after that point are skipped without
 * rollback.
 *
 * @author daoge_cmd
 */
public class DoubleTrunkTreeFeature extends TreeWorldFeature {

    /**
     * Built-in dark oak tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier DARK_OAK_IDENTIFIER = new Identifier("minecraft:dark_oak_tree");
    /**
     * Built-in pale oak tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier PALE_OAK_IDENTIFIER = new Identifier("minecraft:pale_oak_tree");

    /**
     * Creates and registers a two-by-two tree feature using the supplied
     * identifier, log type, and leaf type. The tree base retains those
     * materials, while this implementation fixes the bending and canopy rules.
     */
    public DoubleTrunkTreeFeature(Identifier identifier, BlockType<?> logType, BlockType<?> leavesType) {
        super(identifier, logType, leavesType);
    }

    /**
     * Attempts a two-by-two tree after checking all four substrate cells and
     * the clearance profile. Rejection returns {@code false} before soil
     * conversion; acceptance writes a sampled bending trunk and optional
     * surrounding branches.
     *
     * <p>
     * The main top attachment follows the bent trunk position and uses
     * two-by-two foliage distances. Smaller branch attachments use their own
     * coordinates with single-column foliage rules, keeping both canopy scales
     * in one tree. Logs and leaves still pass individual replacement checks
     * after admission, so a {@code true} result does not prove that the full
     * idealized shape was stored in every context chunk.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(6, 2, 1);

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
                (treeHeight, currentHeight) -> {
                    if (currentHeight < 1) {
                        return 0;
                    }
                    return currentHeight >= treeHeight - 1 ? 2 : 1;
                },
                true
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
        var attachments = new ArrayList<FoliageAttachment>();
        var horizontalFaces = BlockFace.getHorizontalBlockFaces();
        BlockFace bendDirection = horizontalFaces[random.nextInt(horizontalFaces.length)];
        // The bend is limited to upper trunk levels, keeping the four base
        // positions aligned with their checked substrates.
        int bendStart = maxFreeTreeHeight - random.nextInt(4);
        int bendLength = 2 - random.nextInt(3);
        int currentX = x;
        int currentZ = z;
        int topY = y + maxFreeTreeHeight - 1;

        for (int dy = 0; dy < maxFreeTreeHeight; dy++) {
            if (dy >= bendStart && bendLength > 0) {
                currentX += bendDirection.getOffset().x();
                currentZ += bendDirection.getOffset().z();
                bendLength--;
            }

            int logY = y + dy;
            if (isAirOrLeaves(context, currentX, logY, currentZ)) {
                placeLogIfValid(context, currentX, logY, currentZ, placedLogs);
                placeLogIfValid(context, currentX + 1, logY, currentZ, placedLogs);
                placeLogIfValid(context, currentX, logY, currentZ + 1, placedLogs);
                placeLogIfValid(context, currentX + 1, logY, currentZ + 1, placedLogs);
            }
        }

        attachments.add(new FoliageAttachment(currentX, topY, currentZ, 0, true));
        for (int dx = -1; dx <= 2; dx++) {
            for (int dz = -1; dz <= 2; dz++) {
                // Only positions outside the central two-by-two trunk can seed the
                // surrounding downward branches.
                if ((dx < 0 || dx > 1 || dz < 0 || dz > 1) && random.nextInt(3) <= 0) {
                    int branchLength = random.nextInt(3) + 2;
                    for (int i = 0; i < branchLength; i++) {
                        placeLogIfValid(context, x + dx, topY - i - 1, z + dz, placedLogs);
                    }
                    attachments.add(new FoliageAttachment(x + dx, topY, z + dz, 0, false));
                }
            }
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var attachment : attachments) {
            placeDarkOakFoliage(context, attachment, 0, 0, placedLeaves);
        }
        return true;
    }
}
