package org.allaymc.server.world.feature.tree;

import lombok.Getter;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.server.world.feature.AbstractWorldFeature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract base class for tree world features.
 * Provides common methods for tree generation.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class TreeWorldFeature extends AbstractWorldFeature {

    protected final BlockType<?> logType;
    protected final BlockType<?> leavesType;
    protected final BlockType<?> saplingType;
    protected final int minHeight;
    protected final int maxHeight;

    public TreeWorldFeature(
            Identifier identifier,
            BlockType<?> logType,
            BlockType<?> leavesType,
            BlockType<?> saplingType,
            int minHeight,
            int maxHeight
    ) {
        super(identifier);
        this.logType = logType;
        this.leavesType = leavesType;
        this.saplingType = saplingType;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    /**
     * Calculate the actual height for this tree placement.
     *
     * @return the calculated height
     */
    protected int calculateHeight() {
        return getMinHeight() + ThreadLocalRandom.current().nextInt(getMaxHeight() - getMinHeight() + 1);
    }

    protected int calculateHeight(int baseHeight, int heightRandA, int heightRandB) {
        var random = ThreadLocalRandom.current();
        return baseHeight + random.nextInt(heightRandA + 1) + random.nextInt(heightRandB + 1);
    }

    /**
     * Check if a block can be replaced by tree generation.
     *
     * @param blockState the block state to check
     * @return true if the block can be replaced
     */
    protected boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.AIR ||
               blockType.hasBlockTag(BlockTags.REPLACEABLE) ||
               blockType.hasBlockTag(BlockTags.LEAVES);
    }

    /**
     * Check if a block can support tree growth from below.
     *
     * @param blockState the block state to check
     * @return true if the block can support the tree
     */
    protected boolean canGrowOn(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.DIRT);
    }

    /**
     * Check if a log can be placed at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     * @return true if a log can be placed
     */
    protected boolean canPlaceLog(WorldFeatureContext context, int x, int y, int z) {
        return canReplace(context.getBlockState(x, y, z));
    }

    /**
     * Check if leaves can be placed at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     * @return true if leaves can be placed
     */
    protected boolean canPlaceLeaves(WorldFeatureContext context, int x, int y, int z) {
        var existing = context.getBlockState(x, y, z);
        return canReplace(existing);
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

    protected boolean isVine(WorldFeatureContext context, int x, int y, int z) {
        return context.getBlockState(x, y, z).getBlockType() == BlockTypes.VINE;
    }

    protected boolean isLog(WorldFeatureContext context, int x, int y, int z) {
        return context.getBlockState(x, y, z).getBlockType().hasBlockTag(BlockTags.LOG);
    }

    protected boolean validTreePos(WorldFeatureContext context, int x, int y, int z) {
        return canReplace(context.getBlockState(x, y, z));
    }

    protected boolean isFree(WorldFeatureContext context, int x, int y, int z) {
        return validTreePos(context, x, y, z) || isLog(context, x, y, z);
    }

    /**
     * Place a log block at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     */
    protected void placeLog(WorldFeatureContext context, int x, int y, int z) {
        context.setBlockState(x, y, z, logType.getDefaultState());
    }

    protected boolean placeLogIfValid(WorldFeatureContext context, int x, int y, int z) {
        if (!canPlaceLog(context, x, y, z)) {
            return false;
        }
        placeLog(context, x, y, z);
        return true;
    }

    protected boolean placeLogIfValid(WorldFeatureContext context, int x, int y, int z, List<TreePos> placedLogs) {
        if (!placeLogIfValid(context, x, y, z)) {
            return false;
        }
        placedLogs.add(new TreePos(x, y, z));
        return true;
    }

    protected boolean placeLogWithAxisIfValid(WorldFeatureContext context, int x, int y, int z, PillarAxis axis) {
        if (!canPlaceLog(context, x, y, z)) {
            return false;
        }
        var logState = logType.getDefaultState().setPropertyValue(BlockPropertyTypes.PILLAR_AXIS, axis);
        context.setBlockState(x, y, z, logState);
        return true;
    }

    protected boolean placeLogWithAxisIfValid(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            PillarAxis axis,
            List<TreePos> placedLogs
    ) {
        if (!placeLogWithAxisIfValid(context, x, y, z, axis)) {
            return false;
        }
        placedLogs.add(new TreePos(x, y, z));
        return true;
    }

    /**
     * Place a leaves block at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     */
    protected void placeLeaves(WorldFeatureContext context, int x, int y, int z) {
        if (canPlaceLeaves(context, x, y, z)) {
            context.setBlockState(x, y, z, leavesType.getDefaultState());
        }
    }

    protected boolean tryPlaceLeaves(WorldFeatureContext context, int x, int y, int z, BlockState leavesState) {
        if (!canPlaceLeaves(context, x, y, z)) {
            return false;
        }
        context.setBlockState(x, y, z, leavesState);
        return true;
    }

    protected boolean tryPlaceLeaves(WorldFeatureContext context, int x, int y, int z, List<TreePos> placedLeaves) {
        return tryPlaceLeaves(context, x, y, z, leavesType.getDefaultState(), placedLeaves);
    }

    protected boolean tryPlaceLeaves(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            BlockState leavesState,
            List<TreePos> placedLeaves
    ) {
        if (!tryPlaceLeaves(context, x, y, z, leavesState)) {
            return false;
        }
        placedLeaves.add(new TreePos(x, y, z));
        return true;
    }

    /**
     * Check if the tree can generate at the specified position.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate
     * @param z       the z coordinate
     * @param height  the proposed tree height
     * @return true if the tree can generate
     */
    protected boolean canGenerate(WorldFeatureContext context, int x, int y, int z, int height) {
        // Check ground
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        // Check trunk space
        for (int dy = 0; dy < height; dy++) {
            if (!canPlaceLog(context, x, y + dy, z)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Place dirt block under the tree.
     *
     * @param context the feature context
     * @param x       the x coordinate
     * @param y       the y coordinate (below trunk)
     * @param z       the z coordinate
     */
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
                        (!ignoreVines && isVine(context, checkX, checkY, checkZ))) {
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
            List<TreePos> placedLeaves
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

    protected void placeLeavesRow(
            WorldFeatureContext context,
            int x,
            int y,
            int z,
            int range,
            int localY,
            boolean large,
            RowSkipper skipper,
            BlockState leavesState,
            List<TreePos> placedLeaves
    ) {
        int extra = large ? 1 : 0;
        var random = ThreadLocalRandom.current();
        for (int signedX = -range; signedX <= range + extra; signedX++) {
            for (int signedZ = -range; signedZ <= range + extra; signedZ++) {
                int localX = large ? Math.min(Math.abs(signedX), Math.abs(signedX - 1)) : Math.abs(signedX);
                int localZ = large ? Math.min(Math.abs(signedZ), Math.abs(signedZ - 1)) : Math.abs(signedZ);
                var coord = new RowCoord(signedX, signedZ, localX, localZ);
                if (!skipper.shouldSkip(random, coord, localY, range, large)) {
                    tryPlaceLeaves(context, x + signedX, y + localY, z + signedZ, leavesState, placedLeaves);
                }
            }
        }
    }

    protected void placeLeavesRowWithHangingLeavesBelow(
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
            List<TreePos> placedLeaves
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
            List<TreePos> placedLeaves
    ) {
        if (Math.abs(x - originX) + Math.abs(y - originY) + Math.abs(z - originZ) >= 7) {
            return false;
        }
        if (random.nextFloat() > chance) {
            return false;
        }
        return tryPlaceLeaves(context, x, y, z, placedLeaves);
    }

    protected void placeTrunkVines(WorldFeatureContext context, List<TreePos> placedLogs) {
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

    protected void placeLeafVines(WorldFeatureContext context, List<TreePos> placedLeaves, float probability) {
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

    protected void placePodzol(WorldFeatureContext context, List<TreePos> placedLogs) {
        if (placedLogs.isEmpty()) {
            return;
        }

        int minY = placedLogs.stream().mapToInt(TreePos::y).min().orElse(Integer.MIN_VALUE);
        var baseLogs = new ArrayList<TreePos>();
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
            if (isGrassOrDirt(blockState)) {
                context.setBlockState(x, checkY, z, BlockTypes.PODZOL.getDefaultState());
                return;
            }
            if (!isAir(context, x, checkY, z) && dy < 0) {
                return;
            }
        }
    }

    protected void placeMangrovePropagules(WorldFeatureContext context, List<TreePos> placedLeaves) {
        var shuffledLeaves = new ArrayList<>(placedLeaves);
        Collections.shuffle(shuffledLeaves, ThreadLocalRandom.current());
        var random = ThreadLocalRandom.current();
        var exclusion = new ArrayList<TreePos>();

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

            exclusion.add(new TreePos(propX, propY, propZ));
            var propagule = BlockTypes.MANGROVE_PROPAGULE.getDefaultState()
                    .setPropertyValue(BlockPropertyTypes.HANGING, true)
                    .setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, random.nextInt(5));
            context.setBlockState(propX, propY, propZ, propagule);
        }
    }

    protected void placeBlobFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
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

    protected void placeFancyFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
    ) {
        for (int localY = offset; localY >= offset - foliageHeight; localY--) {
            int range = foliageRadius + (localY != offset && localY != offset - foliageHeight ? 1 : 0);
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    range,
                    localY,
                    attachment.doubleTrunk(),
                    (random, coord, rowY, rowRange, large) -> {
                        float dx = coord.localX() + 0.5f;
                        float dz = coord.localZ() + 0.5f;
                        return dx * dx + dz * dz > rowRange * rowRange;
                    },
                    placedLeaves
            );
        }
    }

    protected void placeSpruceFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
    ) {
        var random = ThreadLocalRandom.current();
        int range = random.nextInt(2);
        int maxRange = 1;
        int resetRange = 0;
        for (int localY = offset; localY >= -foliageHeight; localY--) {
            int currentRange = range;
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    currentRange,
                    localY,
                    attachment.doubleTrunk(),
                    (ignored, coord, rowY, rowRange, large) ->
                            coord.localX() == rowRange && coord.localZ() == rowRange && rowRange > 0,
                    placedLeaves
            );

            if (range >= maxRange) {
                range = resetRange;
                resetRange = 1;
                maxRange = Math.min(maxRange + 1, foliageRadius + attachment.radiusOffset());
            } else {
                range++;
            }
        }
    }

    protected void placePineFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
    ) {
        int range = 0;
        for (int localY = offset; localY >= offset - foliageHeight; localY--) {
            int currentRange = range;
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    currentRange,
                    localY,
                    attachment.doubleTrunk(),
                    (ignored, coord, rowY, rowRange, large) ->
                            coord.localX() == rowRange && coord.localZ() == rowRange && rowRange > 0,
                    placedLeaves
            );

            if (range >= 1 && localY == offset - foliageHeight + 1) {
                range--;
            } else if (range < foliageRadius + attachment.radiusOffset()) {
                range++;
            }
        }
    }

    protected void placeAcaciaFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
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

    protected void placeDarkOakFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
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
            List<TreePos> placedLeaves
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

    protected void placeMegaJungleFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
    ) {
        int foliageLayers = attachment.doubleTrunk() ? foliageHeight : 1 + ThreadLocalRandom.current().nextInt(2);
        for (int localY = offset; localY >= offset - foliageLayers; localY--) {
            int range = foliageRadius + attachment.radiusOffset() + 1 - localY;
            placeLeavesRow(
                    context,
                    attachment.x(),
                    attachment.y(),
                    attachment.z(),
                    range,
                    localY,
                    attachment.doubleTrunk(),
                    (ignored, coord, rowY, rowRange, large) ->
                            coord.localX() + coord.localZ() >= 7 ||
                            coord.localX() * coord.localX() + coord.localZ() * coord.localZ() > rowRange * rowRange,
                    placedLeaves
            );
        }
    }

    protected void placeMegaPineFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<TreePos> placedLeaves
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

    protected void placeRandomSpreadFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int leafPlacementAttempts,
            BlockState leavesState,
            List<TreePos> placedLeaves
    ) {
        var random = ThreadLocalRandom.current();
        for (int i = 0; i < leafPlacementAttempts; i++) {
            int targetX = attachment.x() + random.nextInt(foliageRadius) - random.nextInt(foliageRadius);
            int targetY = attachment.y() + random.nextInt(foliageHeight) - random.nextInt(foliageHeight);
            int targetZ = attachment.z() + random.nextInt(foliageRadius) - random.nextInt(foliageRadius);
            tryPlaceLeaves(context, targetX, targetY, targetZ, leavesState, placedLeaves);
        }
    }

    protected void placeCherryFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            float wideBottomLayerHoleChance,
            float cornerHoleChance,
            float hangingLeavesChance,
            float hangingLeavesExtensionChance,
            List<TreePos> placedLeaves
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

    private boolean isExcluded(int x, int y, int z, List<TreePos> exclusionCenters, int radiusXZ, int radiusY) {
        for (var center : exclusionCenters) {
            if (Math.abs(center.x() - x) <= radiusXZ &&
                Math.abs(center.y() - y) <= radiusY &&
                Math.abs(center.z() - z) <= radiusXZ) {
                return true;
            }
        }
        return false;
    }

    protected boolean isGrassOrDirt(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.GRASS_BLOCK || blockType == BlockTypes.DIRT;
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

    protected record TreePos(int x, int y, int z) {
    }

    protected record FoliageAttachment(int x, int y, int z, int radiusOffset, boolean doubleTrunk) {
    }

    protected record RowCoord(int signedX, int signedZ, int localX, int localZ) {
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

    protected enum HorizontalDirection {
        WEST(-1, 0, 0, -1, false),
        EAST(1, 0, 0, 1, true),
        NORTH(0, -1, 1, 0, true),
        SOUTH(0, 1, -1, 0, false);

        private final int stepX;
        private final int stepZ;
        private final int clockWiseStepX;
        private final int clockWiseStepZ;
        private final boolean clockWiseAxisPositive;

        HorizontalDirection(int stepX, int stepZ, int clockWiseStepX, int clockWiseStepZ, boolean clockWiseAxisPositive) {
            this.stepX = stepX;
            this.stepZ = stepZ;
            this.clockWiseStepX = clockWiseStepX;
            this.clockWiseStepZ = clockWiseStepZ;
            this.clockWiseAxisPositive = clockWiseAxisPositive;
        }

        public int stepX() {
            return stepX;
        }

        public int stepZ() {
            return stepZ;
        }

        public int clockWiseStepX() {
            return clockWiseStepX;
        }

        public int clockWiseStepZ() {
            return clockWiseStepZ;
        }

        public boolean clockWiseAxisPositive() {
            return clockWiseAxisPositive;
        }
    }
}
