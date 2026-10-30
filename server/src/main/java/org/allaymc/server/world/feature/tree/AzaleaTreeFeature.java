package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code AzaleaTreeFeature} places a bent oak-log trunk with mixed ordinary
 * and flowering azalea leaves. Placement requires dirt-tagged ground and a
 * vine-sensitive clearance scan, then replaces the substrate with rooted dirt
 * before writing logs.
 *
 * <p>
 * The upper trunk records multiple foliage attachments, including a horizontal
 * continuation after the vertical portion. Each attachment receives fifty
 * independently sampled leaf attempts with a one-quarter flowering selection,
 * so duplicate positions and overlapping clusters are possible. Replacement
 * checks decide individual leaves after admission rather than reserving a
 * complete canopy. A completed attempt returns success even when some
 * positions were blocked or inaccessible through the context.
 *
 * @author daoge_cmd
 */
public class AzaleaTreeFeature extends TreeWorldFeature {

    /**
     * Built-in azalea tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:azalea_tree");

    /**
     * Creates and immediately registers the built-in azalea tree feature. The
     * constructor fixes its log and leaf materials while shared clearance and
     * replacement behavior remains in the tree base.
     */
    public AzaleaTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.OAK_LOG,
                BlockTypes.AZALEA_LEAVES
        );
    }

    /**
     * Attempts an azalea tree after dirt-substrate and vine-sensitive
     * clearance checks. Failure returns {@code false} before writing;
     * acceptance changes the ground to rooted dirt and places a bending trunk
     * before scattered foliage.
     *
     * <p>
     * Upper trunk positions and the final horizontal continuation contribute
     * separate foliage attachments. Fifty sampled leaf attempts per attachment
     * can overlap, with flowering leaves selected independently one quarter of
     * the time. Each leaf uses the shared replacement helper, so blocked
     * positions are skipped and accepted coordinates are recorded without
     * requiring a complete canopy for the final {@code true} result.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = calculateHeight(4, 2, 0);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> currentHeight < 1 ? 0 : 1, false);
        if (maxFreeTreeHeight < height) {
            return false;
        }

        context.setBlockState(x, y - 1, z, BlockTypes.DIRT_WITH_ROOTS.getDefaultState());

        var attachments = new ArrayList<FoliageAttachment>();
        var horizontalFaces = BlockFace.getHorizontalBlockFaces();
        BlockFace bendDirection = horizontalFaces[random.nextInt(horizontalFaces.length)];
        int topIndex = maxFreeTreeHeight - 1;
        int currentX = x;
        int currentZ = z;

        for (int i = 0; i <= topIndex; i++) {
            if (i + 1 >= topIndex + random.nextInt(2)) {
                currentX += bendDirection.getOffset().x();
                currentZ += bendDirection.getOffset().z();
            }

            placeLogIfValid(context, currentX, y + i, currentZ);
            // Only upper trunk levels contribute the scattered azalea leaf clusters.
            if (i >= 3) {
                attachments.add(new FoliageAttachment(currentX, y + i, currentZ, 0, false));
            }
        }

        int bendLength = 1 + random.nextInt(2);
        int bendY = y + maxFreeTreeHeight;
        for (int i = 0; i <= bendLength; i++) {
            placeLogIfValid(context, currentX, bendY, currentZ);
            attachments.add(new FoliageAttachment(currentX, bendY, currentZ, 0, false));
            currentX += bendDirection.getOffset().x();
            currentZ += bendDirection.getOffset().z();
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var attachment : attachments) {
            // Fifty attempts per attachment build overlapping clusters rather than
            // guaranteeing fifty stored leaves.
            for (int i = 0; i < 50; i++) {
                int targetX = attachment.x() + random.nextInt(3) - random.nextInt(3);
                int targetY = attachment.y() + random.nextInt(2) - random.nextInt(2);
                int targetZ = attachment.z() + random.nextInt(3) - random.nextInt(3);
                // A quarter of sampled leaves use the flowering variant without changing
                // the trunk material.
                BlockState leafState = random.nextInt(4) == 0 ?
                        BlockTypes.AZALEA_LEAVES_FLOWERED.getDefaultState() :
                        BlockTypes.AZALEA_LEAVES.getDefaultState();
                tryPlaceLeaves(context, targetX, targetY, targetZ, leafState, placedLeaves);
            }
        }
        return true;
    }
}
