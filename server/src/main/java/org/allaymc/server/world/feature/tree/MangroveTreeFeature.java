package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.Collections;
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
                BlockTypes.MANGROVE_LEAVES
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

        var placedLeaves = new ArrayList<Vector3i>();
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

        var trunkOrigin = new Vector3i(x, trunkY, z);
        var roots = new ArrayList<Vector3i>();
        roots.add(new Vector3i(x, trunkY - 1, z));

        for (var direction : BlockFace.getHorizontalBlockFaces()) {
            var directionalRoots = new ArrayList<Vector3i>();
            var sidePos = new Vector3i(x + direction.getOffset().x(), trunkY, z + direction.getOffset().z());
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
            Vector3i pos,
            BlockFace direction,
            Vector3i trunkOrigin,
            List<Vector3i> roots,
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

    private List<Vector3i> potentialRootPositions(Vector3i pos, BlockFace direction, Vector3i trunkOrigin) {
        var random = ThreadLocalRandom.current();
        var below = new Vector3i(pos.x(), pos.y() - 1, pos.z());
        var forward = new Vector3i(pos.x() + direction.getOffset().x(), pos.y(), pos.z() + direction.getOffset().z());
        int distance = Math.abs(pos.x() - trunkOrigin.x()) + Math.abs(pos.y() - trunkOrigin.y()) + Math.abs(pos.z() - trunkOrigin.z());
        if (distance > 5 && distance <= 8) {
            if (random.nextFloat() < 0.2f) {
                return List.of(below, new Vector3i(forward.x(), forward.y() - 1, forward.z()));
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
        var horizontalFaces = BlockFace.getHorizontalBlockFaces();
        for (int i = 0; i < height; i++) {
            int logY = y + i;
            if (placeMangroveLog(context, x, logY, z) && i < height - 1 && random.nextFloat() < 0.5f) {
                BlockFace direction = horizontalFaces[random.nextInt(horizontalFaces.length)];
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
            BlockFace direction,
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
                currentX += direction.getOffset().x();
                currentZ += direction.getOffset().z();
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

    private void placeRandomSpreadFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int leafPlacementAttempts,
            BlockState leavesState,
            List<Vector3i> placedLeaves
    ) {
        var random = ThreadLocalRandom.current();
        for (int i = 0; i < leafPlacementAttempts; i++) {
            int targetX = attachment.x() + random.nextInt(foliageRadius) - random.nextInt(foliageRadius);
            int targetY = attachment.y() + random.nextInt(foliageHeight) - random.nextInt(foliageHeight);
            int targetZ = attachment.z() + random.nextInt(foliageRadius) - random.nextInt(foliageRadius);
            tryPlaceLeaves(context, targetX, targetY, targetZ, leavesState, placedLeaves);
        }
    }

    private void placeMangrovePropagules(WorldFeatureContext context, List<Vector3i> placedLeaves) {
        var shuffledLeaves = new ArrayList<>(placedLeaves);
        Collections.shuffle(shuffledLeaves, ThreadLocalRandom.current());
        var random = ThreadLocalRandom.current();
        var exclusion = new ArrayList<Vector3i>();

        for (var leaf : shuffledLeaves) {
            int propX = leaf.x();
            int propY = leaf.y() - 1;
            int propZ = leaf.z();
            if (random.nextFloat() >= 0.14f ||
                isExcluded(propX, propY, propZ, exclusion, 1, 0) ||
                !isAir(context, propX, propY, propZ) ||
                !isAir(context, propX, propY - 1, propZ)) {
                continue;
            }

            exclusion.add(new Vector3i(propX, propY, propZ));
            var propagule = BlockTypes.MANGROVE_PROPAGULE.getDefaultState()
                    .setPropertyValue(BlockPropertyTypes.HANGING, true)
                    .setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, random.nextInt(5));
            context.setBlockState(propX, propY, propZ, propagule);
        }
    }

    private boolean isExcluded(int x, int y, int z, List<Vector3i> exclusionCenters, int radiusXZ, int radiusY) {
        for (var center : exclusionCenters) {
            if (Math.abs(center.x() - x) <= radiusXZ &&
                Math.abs(center.y() - y) <= radiusY &&
                Math.abs(center.z() - z) <= radiusXZ) {
                return true;
            }
        }
        return false;
    }
}
