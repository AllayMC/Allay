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

/**
 * {@code TreeWorldFeature} supplies block predicates, placement helpers, and
 * foliage geometry shared by tree features. Its constructor registers each
 * concrete instance and retains the selected log and leaf types; species
 * implementations choose their own ground checks, clearance profiles, and
 * placement order. The context can discard writes to unavailable chunks, so
 * helper success reflects predicate acceptance rather than a guarantee that
 * every requested block was stored.
 *
 * <p>
 * Clearance scans combine a height-dependent horizontal radius with
 * replaceability and optional vine rejection. Foliage rows preserve both
 * signed offsets and distances from the nearest trunk column, allowing the
 * same shape rules to surround single trunks or two-by-two trunks. Placement
 * helpers record accepted positions for later vines and podzol, while those
 * decoration passes inspect current block state again.
 */
public abstract class TreeWorldFeature extends AbstractWorldFeature {

    /**
     * Default trunk and branch block type.
     */
    protected final BlockType<?> logType;
    /**
     * Default generated foliage block type.
     */
    protected final BlockType<?> leavesType;

    /**
     * Creates and registers a tree feature with the supplied log and leaf
     * types. Registration occurs in the base constructor before these fields
     * are assigned, so runtime setup must finish before feature use.
     */
    public TreeWorldFeature(Identifier identifier, BlockType<?> logType, BlockType<?> leavesType) {
        super(identifier);
        this.logType = logType;
        this.leavesType = leavesType;
    }

    /**
     * Returns the base height plus two independently sampled inclusive
     * increments. Both random bounds must be nonnegative and leave room for
     * adding one; this helper performs no validation before calling the random
     * generator.
     */
    protected int calculateHeight(int baseHeight, int heightRandA, int heightRandB) {
        var random = ThreadLocalRandom.current();
        // Both random increments include their upper bound, preserving the
        // configured height range.
        return baseHeight + random.nextInt(heightRandA + 1) + random.nextInt(heightRandB + 1);
    }

    /**
     * Returns whether the block type carries the replaceable tree-placement
     * tag.
     */
    protected boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType.hasBlockTag(BlockTags.REPLACEABLE);
    }

    /**
     * Returns whether the block type carries the dirt substrate tag.
     */
    protected boolean canGrowOn(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.DIRT);
    }

    /**
     * Returns whether the context exposes air at the requested position.
     */
    protected boolean isAir(WorldFeatureContext context, int x, int y, int z) {
        return context.getBlockState(x, y, z).getBlockType() == BlockTypes.AIR;
    }

    /**
     * Returns whether the context exposes a block with the leaves tag.
     */
    protected boolean isLeaves(WorldFeatureContext context, int x, int y, int z) {
        return context.getBlockState(x, y, z).getBlockType().hasBlockTag(BlockTags.LEAVES);
    }

    /**
     * Returns whether the requested position currently contains air or tagged
     * leaves.
     */
    protected boolean isAirOrLeaves(WorldFeatureContext context, int x, int y, int z) {
        return isAir(context, x, y, z) || isLeaves(context, x, y, z);
    }

    /**
     * Returns whether the requested position passes this feature's
     * replaceability predicate.
     */
    protected boolean validTreePos(WorldFeatureContext context, int x, int y, int z) {
        return canReplace(context.getBlockState(x, y, z));
    }

    /**
     * Returns whether the requested position is replaceable or contains tagged
     * logs.
     */
    protected boolean isFree(WorldFeatureContext context, int x, int y, int z) {
        var blockState = context.getBlockState(x, y, z);
        return canReplace(blockState) || blockState.getBlockType().hasBlockTag(BlockTags.LOG);
    }

    /**
     * Writes the default log state at dimension block coordinates without
     * checking replacement eligibility. Callers select and validate the
     * position; an unavailable context chunk can still discard the write.
     */
    protected void placeLog(WorldFeatureContext context, int x, int y, int z) {
        context.setBlockState(x, y, z, logType.getDefaultState());
    }

    /**
     * Attempts to replace an eligible block with the default log state. A
     * rejected position returns {@code false}; {@code true} means the context
     * write was requested, even if its source discards it.
     */
    protected boolean placeLogIfValid(WorldFeatureContext context, int x, int y, int z) {
        if (!canReplace(context.getBlockState(x, y, z))) {
            return false;
        }
        placeLog(context, x, y, z);
        return true;
    }

    /**
     * Attempts default-log placement and records a new coordinate when the
     * predicate accepts it. The supplied list is mutated only after the
     * delegated write request succeeds, and context availability still
     * controls actual storage.
     */
    protected boolean placeLogIfValid(WorldFeatureContext context, int x, int y, int z, List<Vector3i> placedLogs) {
        if (!placeLogIfValid(context, x, y, z)) {
            return false;
        }
        placedLogs.add(new Vector3i(x, y, z));
        return true;
    }

    /**
     * Attempts to place a log with the supplied pillar axis at a replaceable
     * position. Rejection returns {@code false}; accepted writes return {@code
     * true}, subject to the context's own availability and mutation rules.
     */
    protected boolean placeLogWithAxisIfValid(WorldFeatureContext context, int x, int y, int z, PillarAxis axis) {
        if (!canReplace(context.getBlockState(x, y, z))) {
            return false;
        }
        var logState = logType.getDefaultState().setPropertyValue(BlockPropertyTypes.PILLAR_AXIS, axis);
        context.setBlockState(x, y, z, logState);
        return true;
    }

    /**
     * Attempts default-leaf placement and appends accepted coordinates to the
     * supplied list. Replacement rejection returns {@code false}, while
     * context availability controls whether an accepted write actually reaches
     * stored terrain.
     */
    protected boolean tryPlaceLeaves(WorldFeatureContext context, int x, int y, int z, List<Vector3i> placedLeaves) {
        return tryPlaceLeaves(context, x, y, z, leavesType.getDefaultState(), placedLeaves);
    }

    /**
     * Attempts placement of the supplied leaf state when the existing block is
     * replaceable. Accepted writes append a newly allocated coordinate to
     * {@code placedLeaves}; rejection leaves that list unchanged and returns
     * {@code false}.
     */
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

    /**
     * Converts grass or farmland at the supplied substrate position into dirt.
     * Other block types remain unchanged, so species callers can normalize
     * ordinary soil without replacing every accepted ground material.
     */
    protected void placeDirtUnder(WorldFeatureContext context, int x, int y, int z) {
        var blockBelow = context.getBlockState(x, y, z);
        if (blockBelow.getBlockType() == BlockTypes.GRASS_BLOCK ||
            blockBelow.getBlockType() == BlockTypes.FARMLAND) {
            context.setBlockState(x, y, z, BlockTypes.DIRT.getDefaultState());
        }
    }

    /**
     * Returns the supported tree height using this feature's ordinary
     * free-block predicate. The scan includes one level above the requested
     * height and optionally treats vines as obstructions; lower obstructions
     * can produce negative results.
     */
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

    /**
     * Returns the largest permitted height from a bottom-up clearance scan.
     * The supplied radius function and free-block checker determine each
     * tested volume, while {@code ignoreVines} controls an additional vine
     * obstruction rule.
     *
     * <p>
     * The scan includes the level above the requested tree height. When the
     * first blocked position occurs at relative height {@code dy}, the result
     * is {@code dy - 2}, reserving clearance beneath that obstruction; a block
     * at the base can therefore return a negative value. A fully clear scan
     * returns the original requested height, and this method performs no
     * terrain mutations.
     */
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
                        // Keep two levels below the first obstruction available for the tree's
                        // upper clearance rather than returning the obstructed layer itself.
                        return dy - 2;
                    }
                }
            }
        }
        return treeHeight;
    }

    /**
     * Places a foliage row using signed offsets for positioning and folded
     * distances for shape decisions. The supplied skipper decides omissions,
     * and accepted leaf writes append new coordinates to {@code placedLeaves}
     * for later decoration.
     *
     * <p>
     * A single trunk uses absolute offsets about one column. A large trunk
     * extends the positive row bounds by one and measures each coordinate from
     * the nearer of columns zero and one, keeping the same skipper symmetric
     * about a two-by-two trunk. The context's replaceability and availability
     * rules still determine which requested leaves reach terrain.
     */
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
        // A two-by-two trunk extends positive bounds and folds distances around
        // columns zero and one, keeping shape tests symmetric for both trunk
        // widths.
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

    /**
     * Attempts vines independently on four horizontal sides of each recorded
     * log, with two-thirds acceptance per side. Only air positions receive
     * vines, and the attachment bits point back toward the corresponding log.
     */
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

    /**
     * Attempts hanging vines independently beside recorded leaves using the
     * supplied probability. Candidates require air at their starting position,
     * and each accepted vine can extend downward through up to four additional
     * air blocks.
     */
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

    /**
     * Writes a vine state with the supplied attachment direction bits at
     * dimension coordinates. The caller establishes air or replacement
     * eligibility; this helper neither merges existing vine bits nor performs
     * a support check.
     */
    protected void placeVine(WorldFeatureContext context, int x, int y, int z, VineFace face) {
        context.setBlockState(
                x,
                y,
                z,
                BlockTypes.VINE.getDefaultState().setPropertyValue(BlockPropertyTypes.VINE_DIRECTION_BITS, face.bits())
        );
    }

    /**
     * Writes a starting vine and extends it downward through at most four
     * further air blocks. The caller validates the starting position, while
     * the loop stops before the first nonair continuation block.
     */
    protected void addHangingVine(WorldFeatureContext context, int x, int y, int z, VineFace face) {
        placeVine(context, x, y, z, face);
        // Four additional air cells cap a hanging vine below its initial
        // attachment.
        for (int i = 1; i <= 4 && isAir(context, x, y - i, z); i++) {
            placeVine(context, x, y - i, z, face);
        }
    }

    /**
     * Decorates the soil around the lowest recorded log positions with podzol
     * patches. An empty log list performs no work, and the caller's position
     * list is read without being modified.
     *
     * <p>
     * The lowest log height identifies trunk bases rather than elevated
     * branches. Each base produces four fixed offset patches and five sampled
     * attempts from an eight-by-eight area; only perimeter samples add
     * patches. Each patch searches a short vertical band for grass or dirt and
     * stops beneath obstructing terrain, limiting the conversion to reachable
     * substrate.
     */
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

            // Five sampled border candidates add irregular patches beyond the four
            // fixed circles.
            for (int i = 0; i < 5; i++) {
                // Sixty-four values encode an eight-by-eight square of optional patch
                // centers.
                int value = random.nextInt(64);
                int xOffset = value % 8;
                int zOffset = value / 8;
                if (xOffset == 0 || xOffset == 7 || zOffset == 0 || zOffset == 7) {
                    placePodzolCircle(context, baseLog.x() - 3 + xOffset, baseLog.y(), baseLog.z() - 3 + zOffset);
                }
            }
        }
    }

    /**
     * Attempts a five-by-five podzol patch with its four corners omitted.
     * Every included horizontal position delegates vertical substrate
     * discovery, so patch shape does not guarantee conversion where eligible
     * soil is absent.
     */
    private void placePodzolCircle(WorldFeatureContext context, int x, int y, int z) {
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.abs(dx) != 2 || Math.abs(dz) != 2) {
                    placePodzolAt(context, x + dx, y, z + dz);
                }
            }
        }
    }

    /**
     * Searches from two blocks above to three below the supplied base for
     * grass or dirt. The first eligible block becomes podzol; a nonair
     * obstruction below the base terminates the search without conversion.
     */
    private void placePodzolAt(WorldFeatureContext context, int x, int y, int z) {
        // The short vertical band finds nearby surface soil without scanning an
        // entire column.
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

    /**
     * Places rounded foliage layers from the supplied offset through the
     * requested depth. The radius combines the attachment adjustment with
     * integer half-height expansion, while zero is the minimum row radius.
     *
     * <p>
     * Corner blocks are randomly omitted on ordinary rows and always omitted
     * on the row at local height zero. Large attachments use the shared
     * two-by-two distance folding, so the same corner rule surrounds either
     * trunk width. Accepted leaf positions accumulate in the caller's list;
     * obstructed positions are skipped rather than aborting the canopy.
     */
    protected void placeBlobFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
    ) {
        for (int localY = offset; localY >= offset - foliageHeight; localY--) {
            // Integer half-height expansion widens lower blob rows around the
            // attachment.
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

    /**
     * Places dark-oak-style foliage around a trunk or branch attachment. Large
     * attachments use three broad rows with an optional fourth top row;
     * single-trunk attachments use two smaller rows.
     *
     * <p>
     * The attachment width selects both the row radii and their offsets before
     * {@code placeDarkOakRow} applies row-specific clipping. The resulting
     * leaves are appended to the caller's collection for later processing, and
     * existing nonreplaceable blocks remain untouched. The random top row
     * changes canopy height without changing the attachment or trunk
     * coordinates.
     */
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

    /**
     * Places one dark-oak foliage row with clipping selected by row height and
     * trunk width. Large middle rows omit extreme corners, single lower rows
     * omit corner cells, and upper rows apply a diagonal cutoff.
     *
     * <p>
     * The skipper uses signed offsets where the two-by-two row bounds are
     * asymmetric about zero and folded distances where trunk-relative geometry
     * matters. This distinction keeps the expanded positive edge consistent
     * with the trunk footprint. Accepted leaves are recorded through the
     * shared row helper, while blocked locations remain unchanged.
     */
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

    /**
     * Places a conical foliage sequence whose radii depend on distance below
     * the attachment top. The supplied foliage height must be positive for
     * meaningful scaling, and accepted leaf coordinates accumulate in the
     * caller's list.
     *
     * <p>
     * Repeated nominal radii widen by one on even absolute heights below the
     * top, producing alternating edges without changing the next row's nominal
     * radius. Each row then applies both a radial boundary and a diagonal
     * limit using trunk-folded distances. Large attachments therefore retain
     * their two-by-two symmetry while the vertical sequence controls the cone.
     */
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
            // A repeated radius widens on even heights to vary the cone edge;
            // previousRange retains the nominal value so this widening does not
            // compound.
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

    /**
     * {@code HeightRadiusFunction} supplies the horizontal clearance radius
     * for a tree layer.
     */
    @FunctionalInterface
    protected interface HeightRadiusFunction {
        /**
         * Returns the horizontal clearance radius for the requested relative
         * tree height.
         */
        int sizeAtHeight(int treeHeight, int currentHeight);
    }

    /**
     * {@code FreeBlockChecker} tests whether a position permits a species'
     * clearance scan.
     */
    @FunctionalInterface
    protected interface FreeBlockChecker {
        /**
     * Returns whether the supplied dimension position can pass the clearance
     * scan.
     */
    boolean isFree(WorldFeatureContext context, int x, int y, int z);
    }

    /**
     * {@code RowSkipper} selects foliage cells to omit using trunk-relative
     * row geometry.
     */
    @FunctionalInterface
    protected interface RowSkipper {
        /**
         * Returns whether this foliage cell should be omitted from the row.
         */
        boolean shouldSkip(ThreadLocalRandom random, RowCoord coord, int localY, int range, boolean large);
    }

    /**
     * {@code FoliageAttachment} describes a canopy origin and its trunk-width
     * adjustment.
     *
     * @param x canopy origin X in blocks
     *
     * @param y canopy origin Y in blocks
     *
     * @param z canopy origin Z in blocks
     *
     * @param radiusOffset additional horizontal canopy radius
     *
     * @param doubleTrunk whether the canopy surrounds a two-by-two trunk
     */
    public record FoliageAttachment(int x, int y, int z, int radiusOffset, boolean doubleTrunk) {
    }

    /**
     * {@code RowCoord} carries signed placement offsets and folded
     * trunk-relative foliage distances.
     *
     * @param signedX signed X offset from the row origin
     *
     * @param signedZ signed Z offset from the row origin
     *
     * @param localX distance from the nearest trunk X column
     *
     * @param localZ distance from the nearest trunk Z column
     */
    public record RowCoord(int signedX, int signedZ, int localX, int localZ) {
    }

    /**
     * {@code VineFace} identifies the support direction encoded in a vine
     * block state for generated tree decorations.
     */
    protected enum VineFace {
        /**
         * South-facing vine support bit.
         */
        SOUTH(1),
        /**
         * West-facing vine support bit.
         */
        WEST(2),
        /**
         * North-facing vine support bit.
         */
        NORTH(4),
        /**
         * East-facing vine support bit.
         */
        EAST(8);

        /**
         * Encoded vine attachment bits.
         */
        private final int bits;

        /**
         * Creates a support-direction value with its corresponding vine
         * metadata bits.
         */
        VineFace(int bits) {
            this.bits = bits;
        }

        /**
         * Returns the vine metadata bits for this support direction.
         */
        public int bits() {
            return bits;
        }
    }

}
