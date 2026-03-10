package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Cherry tree feature implementation.
 *
 * @author daoge_cmd
 */
public class CherryTreeFeature extends TreeWorldFeature {

    public static final Identifier IDENTIFIER = new Identifier("minecraft:cherry_tree");

    public CherryTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.CHERRY_LOG,
                BlockTypes.CHERRY_LEAVES
        );
    }

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

        HorizontalDirection branchDirection = HorizontalDirection.values()[random.nextInt(HorizontalDirection.values().length)];
        attachments.add(generateBranch(context, x, y, z, maxFreeTreeHeight, branchDirection, firstBranchStart, firstBranchStart < trunkHeight - 1));
        if (placeSecondBranch) {
            attachments.add(generateBranch(context, x, y, z, maxFreeTreeHeight, opposite(branchDirection), secondBranchStart, secondBranchStart < trunkHeight - 1));
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var attachment : attachments) {
            placeCherryFoliage(context, attachment, 5, 4, 0, 0.25f, 0.5f, 0.16666667f, 0.33333334f, placedLeaves);
        }
        return true;
    }

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
        for (var direction : HorizontalDirection.values()) {
            int edge = direction.clockWiseAxisPositive() ? range + extra : range;
            int cursorX = x + direction.clockWiseStepX() * edge + direction.stepX() * -range;
            int cursorZ = z + direction.clockWiseStepZ() * edge + direction.stepZ() * -range;
            for (int i = -range; i < range + extra; i++) {
                if (isLeaves(context, cursorX, leafY, cursorZ) &&
                    tryPlaceLeafExtension(context, random, cursorX, leafY - 1, cursorZ, x, baseBelowY, z, hangingLeavesChance, placedLeaves)) {
                    tryPlaceLeafExtension(context, random, cursorX, leafY - 2, cursorZ, x, baseBelowY, z, hangingLeavesExtensionChance, placedLeaves);
                }
                cursorX += direction.stepX();
                cursorZ += direction.stepZ();
            }
        }
    }

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
        if (Math.abs(x - originX) + Math.abs(y - originY) + Math.abs(z - originZ) >= 7) {
            return false;
        }
        if (random.nextFloat() > chance) {
            return false;
        }
        return tryPlaceLeaves(context, x, y, z, placedLeaves);
    }

    private FoliageAttachment generateBranch(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            int treeHeight,
            HorizontalDirection direction,
            int branchStart,
            boolean doubleBranch
    ) {
        var random = ThreadLocalRandom.current();
        int currentX = x;
        int currentY = y + branchStart;
        int currentZ = z;
        int branchEndY = treeHeight - 1 + sampleUniform(random, -1, 0);
        boolean extended = doubleBranch || branchEndY < branchStart;
        int horizontalLength = sampleUniform(random, 2, 4) + (extended ? 1 : 0);
        int targetX = x + direction.stepX() * horizontalLength;
        int targetY = y + branchEndY;
        int targetZ = z + direction.stepZ() * horizontalLength;
        int firstSteps = extended ? 2 : 1;
        PillarAxis horizontalAxis = direction.stepX() != 0 ? PillarAxis.X : PillarAxis.Z;

        for (int i = 0; i < firstSteps; i++) {
            currentX += direction.stepX();
            currentZ += direction.stepZ();
            placeLogWithAxisIfValid(context, currentX, currentY, currentZ, horizontalAxis);
        }

        int verticalStep = targetY > currentY ? 1 : -1;
        while (true) {
            int distance = Math.abs(targetX - currentX) + Math.abs(targetY - currentY) + Math.abs(targetZ - currentZ);
            if (distance == 0) {
                return new FoliageAttachment(targetX, targetY + 1, targetZ, 0, false);
            }

            float verticalChance = (float) Math.abs(targetY - currentY) / distance;
            boolean moveVertical = random.nextFloat() < verticalChance;
            if (moveVertical) {
                currentY += verticalStep;
                placeLog(context, currentX, currentY, currentZ);
            } else {
                currentX += direction.stepX();
                currentZ += direction.stepZ();
                placeLogWithAxisIfValid(context, currentX, currentY, currentZ, horizontalAxis);
            }
        }
    }

    private HorizontalDirection opposite(HorizontalDirection direction) {
        return switch (direction) {
            case WEST -> HorizontalDirection.EAST;
            case EAST -> HorizontalDirection.WEST;
            case NORTH -> HorizontalDirection.SOUTH;
            case SOUTH -> HorizontalDirection.NORTH;
        };
    }

    private int sampleUniform(ThreadLocalRandom random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
