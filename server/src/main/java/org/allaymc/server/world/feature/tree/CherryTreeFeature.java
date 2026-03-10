package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;
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
                BlockTypes.CHERRY_LEAVES,
                BlockTypes.CHERRY_SAPLING,
                7, 8
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

        var placedLeaves = new ArrayList<TreePos>();
        for (var attachment : attachments) {
            placeCherryFoliage(context, attachment, 5, 4, 0, 0.25f, 0.5f, 0.16666667f, 0.33333334f, placedLeaves);
        }
        return true;
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
