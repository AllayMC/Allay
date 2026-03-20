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
 * Acacia tree feature implementation.
 * Generates acacia trees with diagonal trunk and flat canopy.
 *
 * @author daoge_cmd
 */
public class AcaciaTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:acacia_tree");

    public AcaciaTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.ACACIA_LOG,
                BlockTypes.ACACIA_LEAVES
        );
    }

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
        int bendStart = maxFreeTreeHeight - random.nextInt(4) - 1;
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
