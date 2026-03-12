package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Double-trunk (2x2) tree with dark-oak-style foliage and bending branches.
 * Used for dark oak and pale oak trees.
 *
 * @author daoge_cmd
 */
public class DoubleTrunkTreeFeature extends TreeWorldFeature {

    public static final Identifier DARK_OAK_IDENTIFIER = new Identifier("minecraft:dark_oak_tree");
    public static final Identifier PALE_OAK_IDENTIFIER = new Identifier("minecraft:pale_oak_tree");

    public DoubleTrunkTreeFeature(Identifier identifier, BlockType<?> logType, BlockType<?> leavesType) {
        super(identifier, logType, leavesType);
    }

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
