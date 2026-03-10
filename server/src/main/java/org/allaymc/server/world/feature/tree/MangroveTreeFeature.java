package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Mangrove tree feature implementation.
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
                3, 21
        );
    }

    @Override
    protected boolean canGrowOn(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return super.canGrowOn(blockState) || blockType == BlockTypes.CLAY;
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        boolean tall = random.nextFloat() < 0.85f;
        int height = tall ? calculateHeight(4, 1, 9) : calculateHeight(2, 1, 4);
        int trunkOffset = tall ? sampleUniform(random, 3, 7) : sampleUniform(random, 1, 3);
        int trunkY = y + trunkOffset;

        int maxFreeTreeHeight = getMaxFreeTreeHeight(
                context,
                height,
                x,
                trunkY,
                z,
                (treeHeight, currentHeight) -> currentHeight < (tall ? 3 : 2) ? 0 : 2,
                true,
                this::isMangroveLogFree
        );
        if (maxFreeTreeHeight < height) {
            return false;
        }

        if (!placeMangroveRoots(context, x, y, z, trunkY)) {
            return false;
        }

        var attachments = new ArrayList<FoliageAttachment>();
        placeMangroveTrunk(context, x, trunkY, z, maxFreeTreeHeight, tall, attachments);

        var placedLeaves = new ArrayList<TreePos>();
        for (var attachment : attachments) {
            placeRandomSpreadFoliage(
                    context,
                    attachment,
                    2,
                    3,
                    70,
                    BlockTypes.MANGROVE_LEAVES.getDefaultState(),
                    placedLeaves
            );
        }
        placeLeafVines(context, placedLeaves, 0.125f);
        placeMangrovePropagules(context, placedLeaves);
        return true;
    }

    private boolean placeMangroveRoots(WorldFeatureContext context, int x, int y, int z, int trunkY) {
        for (int currentY = y; currentY < trunkY; currentY++) {
            if (!canPlaceMangroveRoot(context, x, currentY, z)) {
                return false;
            }
        }

        TreePos trunkOrigin = new TreePos(x, trunkY, z);
        var roots = new ArrayList<TreePos>();
        roots.add(new TreePos(x, trunkY - 1, z));

        for (var direction : HorizontalDirection.values()) {
            var directionalRoots = new ArrayList<TreePos>();
            TreePos sidePos = new TreePos(x + direction.stepX(), trunkY, z + direction.stepZ());
            if (!simulateRoots(context, sidePos, direction, trunkOrigin, directionalRoots, 0)) {
                return false;
            }
            roots.addAll(directionalRoots);
            roots.add(sidePos);
        }

        for (var root : roots) {
            placeMangroveRoot(context, root.x(), root.y(), root.z());
        }
        return true;
    }

    private boolean simulateRoots(
            WorldFeatureContext context,
            TreePos pos,
            HorizontalDirection direction,
            TreePos trunkOrigin,
            List<TreePos> roots,
            int length
    ) {
        if (length == 15 || roots.size() > 15) {
            return false;
        }

        for (var candidate : potentialRootPositions(pos, direction, trunkOrigin)) {
            if (canPlaceMangroveRoot(context, candidate.x(), candidate.y(), candidate.z())) {
                roots.add(candidate);
                if (!simulateRoots(context, candidate, direction, trunkOrigin, roots, length + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<TreePos> potentialRootPositions(TreePos pos, HorizontalDirection direction, TreePos trunkOrigin) {
        var random = ThreadLocalRandom.current();
        TreePos below = new TreePos(pos.x(), pos.y() - 1, pos.z());
        TreePos forward = new TreePos(pos.x() + direction.stepX(), pos.y(), pos.z() + direction.stepZ());
        int distance = Math.abs(pos.x() - trunkOrigin.x()) + Math.abs(pos.y() - trunkOrigin.y()) + Math.abs(pos.z() - trunkOrigin.z());
        if (distance > 5 && distance <= 8) {
            if (random.nextFloat() < 0.2f) {
                return List.of(below, new TreePos(forward.x(), forward.y() - 1, forward.z()));
            }
            return List.of(below);
        }
        if (distance > 8) {
            return List.of(below);
        }
        if (random.nextFloat() < 0.2f) {
            return List.of(below);
        }
        return random.nextBoolean() ? List.of(forward) : List.of(below);
    }

    private void placeMangroveRoot(WorldFeatureContext context, int x, int y, int z) {
        if (!canPlaceMangroveRoot(context, x, y, z)) {
            return;
        }

        var blockType = context.getBlockState(x, y, z).getBlockType();
        if (blockType == BlockTypes.MUD || blockType == BlockTypes.MUDDY_MANGROVE_ROOTS) {
            context.setBlockState(x, y, z, BlockTypes.MUDDY_MANGROVE_ROOTS.getDefaultState());
        } else {
            context.setBlockState(x, y, z, BlockTypes.MANGROVE_ROOTS.getDefaultState());
        }

        if (ThreadLocalRandom.current().nextFloat() < 0.5f && isAir(context, x, y + 1, z)) {
            context.setBlockState(x, y + 1, z, BlockTypes.MOSS_CARPET.getDefaultState());
        }
    }

    private void placeMangroveTrunk(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            int height,
            boolean tall,
            List<FoliageAttachment> attachments
    ) {
        var random = ThreadLocalRandom.current();
        for (int i = 0; i < height; i++) {
            int logY = y + i;
            if (placeMangroveLog(context, x, logY, z) && i < height - 1 && random.nextFloat() < 0.5f) {
                HorizontalDirection direction = HorizontalDirection.values()[random.nextInt(HorizontalDirection.values().length)];
                int extraBranchLength = Math.max(0, random.nextInt(2) - random.nextInt(2) - 1);
                int extraBranchSteps = tall ? sampleUniform(random, 1, 6) : sampleUniform(random, 1, 4);
                placeMangroveBranch(context, x, z, logY, height, direction, extraBranchLength, extraBranchSteps, attachments);
            }

            if (i == height - 1) {
                attachments.add(new FoliageAttachment(x, logY + 1, z, 0, false));
            }
        }
    }

    private void placeMangroveBranch(
            WorldFeatureContext context,
            int x,
            int z,
            int y,
            int freeTreeHeight,
            HorizontalDirection direction,
            int extraBranchLength,
            int extraBranchSteps,
            List<FoliageAttachment> attachments
    ) {
        int topY = y + extraBranchLength;
        int currentX = x;
        int currentZ = z;
        int currentStep = extraBranchLength;

        while (currentStep < freeTreeHeight && extraBranchSteps > 0) {
            if (currentStep >= 1) {
                int logY = y + currentStep;
                currentX += direction.stepX();
                currentZ += direction.stepZ();
                topY = logY;
                if (placeMangroveLog(context, currentX, logY, currentZ)) {
                    topY = logY + 1;
                }
                attachments.add(new FoliageAttachment(currentX, logY, currentZ, 0, false));
            }

            currentStep++;
            extraBranchSteps--;
        }

        if (topY - y > 1) {
            attachments.add(new FoliageAttachment(currentX, topY, currentZ, 0, false));
            attachments.add(new FoliageAttachment(currentX, topY - 2, currentZ, 0, false));
        }
    }

    private boolean placeMangroveLog(WorldFeatureContext context, int x, int y, int z) {
        if (!isMangroveLogFree(context, x, y, z)) {
            return false;
        }
        placeLog(context, x, y, z);
        return true;
    }

    private boolean isMangroveLogFree(WorldFeatureContext context, int x, int y, int z) {
        var blockType = context.getBlockState(x, y, z).getBlockType();
        return isFree(context, x, y, z) ||
               blockType == BlockTypes.MUD ||
               blockType == BlockTypes.MUDDY_MANGROVE_ROOTS ||
               blockType == BlockTypes.MANGROVE_ROOTS ||
               blockType == BlockTypes.MANGROVE_LOG ||
               blockType == BlockTypes.MANGROVE_PROPAGULE ||
               blockType == BlockTypes.MOSS_CARPET ||
               blockType == BlockTypes.VINE;
    }

    private boolean canPlaceMangroveRoot(WorldFeatureContext context, int x, int y, int z) {
        var blockType = context.getBlockState(x, y, z).getBlockType();
        return validTreePos(context, x, y, z) ||
               blockType == BlockTypes.MUD ||
               blockType == BlockTypes.MUDDY_MANGROVE_ROOTS ||
               blockType == BlockTypes.MANGROVE_ROOTS ||
               blockType == BlockTypes.MOSS_CARPET ||
               blockType == BlockTypes.VINE ||
               blockType == BlockTypes.MANGROVE_PROPAGULE ||
               blockType == BlockTypes.SNOW;
    }

    private int sampleUniform(ThreadLocalRandom random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
