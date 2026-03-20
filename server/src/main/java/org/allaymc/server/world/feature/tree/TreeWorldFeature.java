package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.server.world.feature.AbstractWorldFeature;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class TreeWorldFeature extends AbstractWorldFeature {

    protected final BlockType<?> logType;
    protected final BlockType<?> leavesType;

    public TreeWorldFeature(Identifier identifier, BlockType<?> logType, BlockType<?> leavesType) {
        super(identifier);
        this.logType = logType;
        this.leavesType = leavesType;
    }

    protected int calculateHeight(int baseHeight, int heightRandA, int heightRandB) {
        var random = ThreadLocalRandom.current();
        return baseHeight + random.nextInt(heightRandA + 1) + random.nextInt(heightRandB + 1);
    }

    protected boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType.hasBlockTag(BlockTags.REPLACEABLE);
    }

    protected boolean canGrowOn(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.DIRT);
    }

    protected boolean isAir(WorldFeatureContext context, int x, int y, int z) {
        return context.getBlockState(x, y, z).getBlockType() == BlockTypes.AIR;
    }

    protected boolean isLeaves(WorldFeatureContext context, int x, int y, int z) {
        return context.getBlockState(x, y, z).getBlockType().hasBlockTag(BlockTags.LEAVES);
    }

    protected boolean isAirOrLeaves(WorldFeatureContext context, int x, int y, int z) {
        return isAir(context, x, y, z) || isLeaves(context, x, y, z);
    }

    protected boolean validTreePos(WorldFeatureContext context, int x, int y, int z) {
        return canReplace(context.getBlockState(x, y, z));
    }

    protected boolean isFree(WorldFeatureContext context, int x, int y, int z) {
        var blockState = context.getBlockState(x, y, z);
        return canReplace(blockState) || blockState.getBlockType().hasBlockTag(BlockTags.LOG);
    }

    protected void placeLog(WorldFeatureContext context, int x, int y, int z) {
        context.setBlockState(x, y, z, logType.getDefaultState());
    }

    protected boolean placeLogIfValid(WorldFeatureContext context, int x, int y, int z) {
        if (!canReplace(context.getBlockState(x, y, z))) {
            return false;
        }
        placeLog(context, x, y, z);
        return true;
    }

    protected boolean placeLogIfValid(WorldFeatureContext context, int x, int y, int z, List<Vector3i> placedLogs) {
        if (!placeLogIfValid(context, x, y, z)) {
            return false;
        }
        placedLogs.add(new Vector3i(x, y, z));
        return true;
    }

    protected boolean placeLogWithAxisIfValid(WorldFeatureContext context, int x, int y, int z, PillarAxis axis) {
        if (!canReplace(context.getBlockState(x, y, z))) {
            return false;
        }
        var logState = logType.getDefaultState().setPropertyValue(BlockPropertyTypes.PILLAR_AXIS, axis);
        context.setBlockState(x, y, z, logState);
        return true;
    }

    protected boolean tryPlaceLeaves(WorldFeatureContext context, int x, int y, int z, List<Vector3i> placedLeaves) {
        return tryPlaceLeaves(context, x, y, z, leavesType.getDefaultState(), placedLeaves);
    }

    protected boolean tryPlaceLeaves(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            BlockState leavesState,
            List<Vector3i> placedLeaves
    ) {
        if (!canReplace(context.getBlockState(x, y, z))) {
            return false;
        }
        context.setBlockState(x, y, z, leavesState);
        placedLeaves.add(new Vector3i(x, y, z));
        return true;
    }

    protected void placeDirtUnder(WorldFeatureContext context, int x, int y, int z) {
        var blockBelow = context.getBlockState(x, y, z);
        if (blockBelow.getBlockType() == BlockTypes.GRASS_BLOCK ||
            blockBelow.getBlockType() == BlockTypes.FARMLAND) {
            context.setBlockState(x, y, z, BlockTypes.DIRT.getDefaultState());
        }
    }

    protected int getMaxFreeTreeHeight(
            WorldFeatureContext context,
            int treeHeight,
            int x,
            int y,
            int z,
            HeightRadiusFunction sizeAtHeight,
            boolean ignoreVines
    ) {
        return getMaxFreeTreeHeight(context, treeHeight, x, y, z, sizeAtHeight, ignoreVines, this::isFree);
    }

    protected int getMaxFreeTreeHeight(
            WorldFeatureContext context,
            int treeHeight,
            int x,
            int y,
            int z,
            HeightRadiusFunction sizeAtHeight,
            boolean ignoreVines,
            FreeBlockChecker freeBlockChecker
    ) {
        for (int dy = 0; dy <= treeHeight + 1; dy++) {
            int size = sizeAtHeight.sizeAtHeight(treeHeight, dy);
            for (int dx = -size; dx <= size; dx++) {
                for (int dz = -size; dz <= size; dz++) {
                    int checkX = x + dx;
                    int checkY = y + dy;
                    int checkZ = z + dz;
                    if (!freeBlockChecker.isFree(context, checkX, checkY, checkZ) ||
                        (!ignoreVines && context.getBlockState(checkX, checkY, checkZ).getBlockType() == BlockTypes.VINE)) {
                        return dy - 2;
                    }
                }
            }
        }
        return treeHeight;
    }

    protected void placeLeavesRow(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            int range,
            int localY,
            boolean large,
            RowSkipper skipper,
            List<Vector3i> placedLeaves
    ) {
        int extra = large ? 1 : 0;
        var random = ThreadLocalRandom.current();
        for (int signedX = -range; signedX <= range + extra; signedX++) {
            for (int signedZ = -range; signedZ <= range + extra; signedZ++) {
                int localX = large ? Math.min(Math.abs(signedX), Math.abs(signedX - 1)) : Math.abs(signedX);
                int localZ = large ? Math.min(Math.abs(signedZ), Math.abs(signedZ - 1)) : Math.abs(signedZ);
                var coord = new RowCoord(signedX, signedZ, localX, localZ);
                if (!skipper.shouldSkip(random, coord, localY, range, large)) {
                    tryPlaceLeaves(context, x + signedX, y + localY, z + signedZ, placedLeaves);
                }
            }
        }
    }

    protected void placeTrunkVines(WorldFeatureContext context, List<Vector3i> placedLogs) {
        var random = ThreadLocalRandom.current();
        for (var log : placedLogs) {
            if (random.nextInt(3) > 0 && isAir(context, log.x() - 1, log.y(), log.z())) {
                placeVine(context, log.x() - 1, log.y(), log.z(), VineFace.EAST);
            }
            if (random.nextInt(3) > 0 && isAir(context, log.x() + 1, log.y(), log.z())) {
                placeVine(context, log.x() + 1, log.y(), log.z(), VineFace.WEST);
            }
            if (random.nextInt(3) > 0 && isAir(context, log.x(), log.y(), log.z() - 1)) {
                placeVine(context, log.x(), log.y(), log.z() - 1, VineFace.SOUTH);
            }
            if (random.nextInt(3) > 0 && isAir(context, log.x(), log.y(), log.z() + 1)) {
                placeVine(context, log.x(), log.y(), log.z() + 1, VineFace.NORTH);
            }
        }
    }

    protected void placeLeafVines(WorldFeatureContext context, List<Vector3i> placedLeaves, float probability) {
        var random = ThreadLocalRandom.current();
        for (var leaf : placedLeaves) {
            if (random.nextFloat() < probability && isAir(context, leaf.x() - 1, leaf.y(), leaf.z())) {
                addHangingVine(context, leaf.x() - 1, leaf.y(), leaf.z(), VineFace.EAST);
            }
            if (random.nextFloat() < probability && isAir(context, leaf.x() + 1, leaf.y(), leaf.z())) {
                addHangingVine(context, leaf.x() + 1, leaf.y(), leaf.z(), VineFace.WEST);
            }
            if (random.nextFloat() < probability && isAir(context, leaf.x(), leaf.y(), leaf.z() - 1)) {
                addHangingVine(context, leaf.x(), leaf.y(), leaf.z() - 1, VineFace.SOUTH);
            }
            if (random.nextFloat() < probability && isAir(context, leaf.x(), leaf.y(), leaf.z() + 1)) {
                addHangingVine(context, leaf.x(), leaf.y(), leaf.z() + 1, VineFace.NORTH);
            }
        }
    }

    protected void placeVine(WorldFeatureContext context, int x, int y, int z, VineFace face) {
        context.setBlockState(
                x,
                y,
                z,
                BlockTypes.VINE.getDefaultState().setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, face.bits())
        );
    }

    protected void addHangingVine(WorldFeatureContext context, int x, int y, int z, VineFace face) {
        placeVine(context, x, y, z, face);
        for (int i = 1; i <= 4 && isAir(context, x, y - i, z); i++) {
            placeVine(context, x, y - i, z, face);
        }
    }

    protected void placePodzol(WorldFeatureContext context, List<Vector3i> placedLogs) {
        if (placedLogs.isEmpty()) {
            return;
        }

        int minY = placedLogs.stream().mapToInt(Vector3i::y).min().orElse(Integer.MIN_VALUE);
        var baseLogs = new ArrayList<Vector3i>();
        for (var log : placedLogs) {
            if (log.y() == minY) {
                baseLogs.add(log);
            }
        }

        var random = ThreadLocalRandom.current();
        for (var baseLog : baseLogs) {
            placePodzolCircle(context, baseLog.x() - 1, baseLog.y(), baseLog.z() - 1);
            placePodzolCircle(context, baseLog.x() + 2, baseLog.y(), baseLog.z() - 1);
            placePodzolCircle(context, baseLog.x() - 1, baseLog.y(), baseLog.z() + 2);
            placePodzolCircle(context, baseLog.x() + 2, baseLog.y(), baseLog.z() + 2);

            for (int i = 0; i < 5; i++) {
                int value = random.nextInt(64);
                int xOffset = value % 8;
                int zOffset = value / 8;
                if (xOffset == 0 || xOffset == 7 || zOffset == 0 || zOffset == 7) {
                    placePodzolCircle(context, baseLog.x() - 3 + xOffset, baseLog.y(), baseLog.z() - 3 + zOffset);
                }
            }
        }
    }

    private void placePodzolCircle(WorldFeatureContext context, int x, int y, int z) {
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.abs(dx) != 2 || Math.abs(dz) != 2) {
                    placePodzolAt(context, x + dx, y, z + dz);
                }
            }
        }
    }

    private void placePodzolAt(WorldFeatureContext context, int x, int y, int z) {
        for (int dy = 2; dy >= -3; dy--) {
            int checkY = y + dy;
            var blockState = context.getBlockState(x, checkY, z);
            var blockType = blockState.getBlockType();
            if (blockType == BlockTypes.GRASS_BLOCK || blockType == BlockTypes.DIRT) {
                context.setBlockState(x, checkY, z, BlockTypes.PODZOL.getDefaultState());
                return;
            }
            if (blockType != BlockTypes.AIR && dy < 0) {
                return;
            }
        }
    }

    protected void placeBlobFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        for (int localY = offset; localY >= offset - foliageHeight; localY--) {
            int range = Math.max(foliageRadius + attachment.radiusOffset() - 1 - localY / 2, 0);
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    range,
                    localY,
                    attachment.doubleTrunk(),
                    (random, coord, rowY, rowRange, large) ->
                            coord.localX() == rowRange && coord.localZ() == rowRange &&
                            (random.nextInt(2) == 0 || rowY == 0),
                    placedLeaves
            );
        }
    }

    protected void placeDarkOakFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        int baseX = attachment.x();
        int baseY = attachment.y() + offset;
        int baseZ = attachment.z();

        if (attachment.doubleTrunk()) {
            placeDarkOakRow(context, baseX, baseY, baseZ, foliageRadius + 2, -1, true, placedLeaves);
            placeDarkOakRow(context, baseX, baseY, baseZ, foliageRadius + 3, 0, true, placedLeaves);
            placeDarkOakRow(context, baseX, baseY, baseZ, foliageRadius + 2, 1, true, placedLeaves);
            if (ThreadLocalRandom.current().nextBoolean()) {
                placeDarkOakRow(context, baseX, baseY, baseZ, foliageRadius, 2, true, placedLeaves);
            }
        } else {
            placeDarkOakRow(context, baseX, baseY, baseZ, foliageRadius + 2, -1, false, placedLeaves);
            placeDarkOakRow(context, baseX, baseY, baseZ, foliageRadius + 1, 0, false, placedLeaves);
        }
    }

    private void placeDarkOakRow(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            int range,
            int localY,
            boolean large,
            List<Vector3i> placedLeaves
    ) {
        placeLeavesRow(
                context,
                x,
                y,
                z,
                range,
                localY,
                large,
                (random, coord, rowY, rowRange, isLarge) -> {
                    if (rowY == 0 && isLarge &&
                        (coord.signedX() == -rowRange || coord.signedX() >= rowRange) &&
                        (coord.signedZ() == -rowRange || coord.signedZ() >= rowRange)) {
                        return true;
                    }
                    if (rowY == -1 && !isLarge) {
                        return coord.localX() == rowRange && coord.localZ() == rowRange;
                    }
                    return rowY == 1 && coord.localX() + coord.localZ() > rowRange * 2 - 2;
                },
                placedLeaves
        );
    }

    protected void placeMegaPineFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        int previousRange = 0;
        for (int currentY = attachment.y() - foliageHeight + offset; currentY <= attachment.y() + offset; currentY++) {
            int heightFromTop = attachment.y() - currentY;
            int range = foliageRadius + attachment.radiusOffset() + (int) Math.floor((float) heightFromTop / foliageHeight * 3.5F);
            int actualRange;
            if (heightFromTop > 0 && range == previousRange && (currentY & 1) == 0) {
                actualRange = range + 1;
            } else {
                actualRange = range;
            }

            placeLeavesRow(
                    context,
                    attachment.x(),
                    currentY,
                    attachment.z(),
                    actualRange,
                    0,
                    attachment.doubleTrunk(),
                    (ignored, coord, rowY, rowRange, large) ->
                            coord.localX() + coord.localZ() >= 7 ||
                            coord.localX() * coord.localX() + coord.localZ() * coord.localZ() > rowRange * rowRange,
                    placedLeaves
            );
            previousRange = range;
        }
    }

    @FunctionalInterface
    protected interface HeightRadiusFunction {
        int sizeAtHeight(int treeHeight, int currentHeight);
    }

    @FunctionalInterface
    protected interface FreeBlockChecker {
        boolean isFree(WorldFeatureContext context, int x, int y, int z);
    }

    @FunctionalInterface
    protected interface RowSkipper {
        boolean shouldSkip(ThreadLocalRandom random, RowCoord coord, int localY, int range, boolean large);
    }

    public record FoliageAttachment(int x, int y, int z, int radiusOffset, boolean doubleTrunk) {
    }

    public record RowCoord(int signedX, int signedZ, int localX, int localZ) {
    }

    protected enum VineFace {
        SOUTH(1),
        WEST(2),
        NORTH(4),
        EAST(8);

        private final int bits;

        VineFace(int bits) {
            this.bits = bits;
        }

        public int bits() {
            return bits;
        }
    }

}
