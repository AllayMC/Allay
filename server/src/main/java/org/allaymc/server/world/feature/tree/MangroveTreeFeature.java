package org.allaymc.server.world.feature.tree;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code MangroveTreeFeature} places elevated mangrove trunks, simulated
 * roots, scattered foliage, vines, and hanging propagules. A sampled tall or
 * short profile controls trunk height, root offset, and branch reach; trunk
 * clearance uses a mangrove-specific replacement predicate.
 *
 * <p>
 * Root paths are collected and validated before any roots are written, so an
 * overlong path rejects the attempt without a partial root structure. Once
 * roots are accepted, trunk and foliage placement mutate the context
 * incrementally. Propagule candidates are shuffled and tracked with exclusion
 * centers so nearby accepted hanging positions suppress later candidates.
 * Context availability and individual block checks can still discard or skip
 * writes, and later exceptions have no rollback.
 *
 * @author daoge_cmd
 */
public class MangroveTreeFeature extends TreeWorldFeature {

    /**
     * Built-in mangrove tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:mangrove_tree");

    /**
     * Creates and immediately registers the built-in mangrove tree feature.
     * The constructor fixes its log and leaf materials while shared clearance
     * and replacement behavior remains in the tree base.
     */
    public MangroveTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.MANGROVE_LOG,
                BlockTypes.MANGROVE_LEAVES
        );
    }

    /**
     * Returns whether dirt-tagged soil or clay passes this inherited ground
     * predicate.
     */
    @Override
    protected boolean canGrowOn(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return super.canGrowOn(blockState) || blockType == BlockTypes.CLAY;
    }

    /**
     * Attempts a tall or short mangrove with a raised trunk and simulated root
     * paths. Trunk-clearance failure or rejected root simulation returns
     * {@code false} before root writes; later successful phases mutate the
     * context incrementally.
     *
     * <p>
     * The tall choice controls trunk height, elevation, and branch limits
     * together. Accepted roots precede trunk and foliage, and recorded leaf
     * positions feed vine and propagule decoration. Propagule placement
     * combines random acceptance, two empty cells, and exclusion centers from
     * earlier accepted candidates. This method returns {@code true} after
     * those passes finish even when individual context writes were discarded,
     * and exceptions do not roll back earlier phases.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        // The tall profile jointly selects trunk height, elevation, and branch
        // reach.
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

    /**
     * Validates root space, simulates four directional paths, and writes roots
     * only after every path is accepted. The caller supplies the raised trunk
     * height; failure returns {@code false} before this helper changes
     * terrain.
     *
     * <p>
     * Each direction accumulates candidate coordinates separately, allowing an
     * overlong recursive path to reject the whole root plan without exposing
     * partial roots. The accepted lists are then combined with the trunk's
     * lower root position and written through the root material selector. That
     * writer rechecks each coordinate, so terrain changes or inaccessible
     * chunks can still omit individual planned roots.
     */
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

    /**
     * Appends acceptable recursive root candidates and returns whether their
     * path stays within the length and count limits. This helper changes the
     * supplied coordinate list but never writes terrain, allowing its caller
     * to discard a rejected plan.
     *
     * <p>
     * Candidate selection depends on position relative to the trunk and a
     * sampled direction decision. Replaceable candidates are appended before
     * their descendants are explored, so a failed descendant can leave
     * coordinates in the local list; callers must ignore that entire list
     * after {@code false}. Reaching recursion depth fifteen or exceeding
     * fifteen accumulated positions rejects the directional plan.
     */
    private boolean simulateRoots(
            WorldFeatureContext context,
            Vector3i pos,
            BlockFace direction,
            Vector3i trunkOrigin,
            List<Vector3i> roots,
            int length
    ) {
        // Depth and accumulated-position limits reject oversized directional root
        // plans before their coordinates are committed as world blocks.
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

    /**
     * Returns sampled next root coordinates based on Manhattan distance from
     * the raised trunk origin. The returned list is immutable, but its newly
     * created vectors remain mutable and belong to the root simulation.
     *
     * <p>
     * Near the trunk, candidates can continue forward or down. Beyond distance
     * five the choice favors downward movement, and beyond eight only a
     * downward position is returned. The middle band occasionally returns both
     * a downward and a forward-downward position, allowing the recursive
     * caller to branch while applying its own length and count limits.
     */
    private List<Vector3i> potentialRootPositions(Vector3i pos, BlockFace direction, Vector3i trunkOrigin) {
        var random = ThreadLocalRandom.current();
        var below = new Vector3i(pos.x(), pos.y() - 1, pos.z());
        var forward = new Vector3i(pos.x() + direction.getOffset().x(), pos.y(), pos.z() + direction.getOffset().z());
        int distance = Math.abs(pos.x() - trunkOrigin.x()) + Math.abs(pos.y() - trunkOrigin.y()) + Math.abs(pos.z() - trunkOrigin.z());
        // Roots beyond five Manhattan blocks favor downward growth; the middle
        // band can still branch forward and down.
        if (distance > 5 && distance <= 8) {
            if (random.nextFloat() < 0.2f) {
                return List.of(below, new Vector3i(forward.x(), forward.y() - 1, forward.z()));
            }
            return List.of(below);
        }
        // Beyond eight Manhattan blocks, roots continue downward without a forward
        // candidate.
        if (distance > 8) {
            return List.of(below);
        }
        if (random.nextFloat() < 0.2f) {
            return List.of(below);
        }
        return random.nextBoolean() ? List.of(forward) : List.of(below);
    }

    /**
     * Places a root only when the mangrove root predicate accepts the current
     * state. Mud and existing muddy roots select muddy roots; other accepted
     * materials select ordinary roots, with an optional moss carpet replacing
     * air directly above the root.
     */
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

    /**
     * Places the elevated trunk and appends foliage attachments for its top
     * and sampled branches. Successful non-top logs can start a branch, with
     * tall and short profiles using different step limits.
     *
     * <p>
     * The extra horizontal branch-length expression currently clamps every
     * possible sample to zero, so branch progression depends on the separately
     * sampled step count. The top attachment is still recorded at the selected
     * trunk height even when that final log is rejected. The caller later uses
     * these attachments for foliage, and accepted writes are not rolled back
     * if later branches fail.
     */
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
                // Each random term is zero or one; subtracting one and clamping means this
                // extra length currently always remains zero.
                int extraBranchLength = Math.max(0, random.nextInt(2) - random.nextInt(2) - 1);
                int extraBranchSteps = tall ? sampleUniform(random, 1, 6) : sampleUniform(random, 1, 4);
                placeMangroveBranch(context, x, z, logY, height, direction, extraBranchLength, extraBranchSteps, attachments);
            }

            if (i == height - 1) {
                attachments.add(new FoliageAttachment(x, logY + 1, z, 0, false));
            }
        }
    }

    /**
     * Places a diagonal mangrove branch while its height and remaining step
     * budget permit progress. Each progressed position adds a foliage
     * attachment even when its log is rejected, and taller results add two
     * extra terminal attachments.
     *
     * <p>
     * Horizontal coordinates move only after the first branch level, while the
     * vertical coordinate follows the relative branch step. Successful log
     * placement can raise the terminal attachment height by one; otherwise the
     * last attempted log height is retained. The caller supplies the shared
     * attachment list, which is mutated incrementally without undoing earlier
     * entries after failed block writes.
     */
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

    /**
     * Attempts a mangrove log at a position accepted by the species-specific
     * free-block predicate. Rejection returns {@code false}; acceptance
     * requests a direct log write, subject to the context's chunk
     * availability.
     */
    private boolean placeMangroveLog(WorldFeatureContext context, int x, int y, int z) {
        if (!isMangroveLogFree(context, x, y, z)) {
            return false;
        }
        placeLog(context, x, y, z);
        return true;
    }

    /**
     * Returns whether ordinary free tree space or mangrove-associated terrain
     * accepts a log.
     */
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

    /**
     * Returns whether replaceable space or supported mangrove-associated
     * blocks accept a root.
     */
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

    /**
     * Returns an integer sampled uniformly from the inclusive supplied bounds.
     * The caller must provide an ordered range whose inclusive width fits a
     * positive integer bound; invalid bounds are delegated to the random
     * generator.
     */
    private int sampleUniform(ThreadLocalRandom random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Attempts the requested number of leaves around an attachment using
     * paired random offsets. Both height and radius bounds must be positive;
     * accepted writes append coordinates to the supplied list, and duplicate
     * candidates remain possible.
     */
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

    /**
     * Attempts hanging propagules beneath a shuffled copy of the recorded
     * leaves. The input list stays in its existing order, while accepted
     * candidates reserve exclusion centers before their context write is
     * requested.
     *
     * <p>
     * A candidate must pass its random threshold, avoid nearby accepted
     * centers, and have two consecutive air cells below its leaf. The
     * exclusion radius applies within the same vertical level, so nearby
     * positions at different heights do not suppress each other under these
     * arguments. Each accepted propagule is marked hanging with a sampled
     * stage, and discarded context writes still leave their exclusion center
     * recorded for this pass.
     */
    private void placeMangrovePropagules(WorldFeatureContext context, List<Vector3i> placedLeaves) {
        var shuffledLeaves = new ArrayList<>(placedLeaves);
        Collections.shuffle(shuffledLeaves, ThreadLocalRandom.current());
        var random = ThreadLocalRandom.current();
        var exclusion = new ArrayList<Vector3i>();

        // Shuffling changes which nearby candidate reserves an exclusion center
        // first; each accepted center suppresses later candidates at that same
        // vertical level.
        for (var leaf : shuffledLeaves) {
            int propX = leaf.x();
            int propY = leaf.y() - 1;
            int propZ = leaf.z();
            // The fourteen-percent selection is further restricted by spacing and two
            // air cells, leaving room below an accepted hanging propagule.
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

    /**
     * Returns whether any recorded center lies within all supplied horizontal
     * and vertical radii. Bounds are inclusive and axis-aligned; the method
     * reads the center list without adding candidates or modifying its
     * vectors.
     */
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
