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
 * {@code FancyOakTreeFeature} places branching oak trees by selecting foliage
 * locations before writing the trunk and limbs. Clearance can shorten the
 * requested height when at least four free levels remain, and foliage
 * candidates must pass both vertical-space and connecting-limb tests.
 *
 * <p>
 * Candidate positions retain their branch-base heights so later trunk
 * connections and canopy placement refer to the same selected geometry. The
 * lower trunk excludes foliage candidates, and low branch bases are trimmed
 * before either their limbs or leaves are written. Limb rasterization uses the
 * largest coordinate difference as its step count, while log axes follow
 * horizontal displacement from the limb origin. Placement remains incremental
 * and does not roll back rejected individual writes.
 *
 * @author daoge_cmd
 */
public class FancyOakTreeFeature extends TreeWorldFeature {

    /**
     * Built-in fancyoak tree registry key. Runtime feature construction uses
     * this identifier to register the corresponding placement implementation.
     */
    public static final Identifier IDENTIFIER = new Identifier("minecraft:fancy_oak_tree");

    /**
     * Creates and immediately registers the built-in fancyoak tree feature.
     * The constructor fixes its log and leaf materials while shared clearance
     * and replacement behavior remains in the tree base.
     */
    public FancyOakTreeFeature() {
        super(
                IDENTIFIER,
                BlockTypes.OAK_LOG,
                BlockTypes.OAK_LEAVES
        );
    }

    /**
     * Attempts a branching oak after substrate and central-column clearance
     * checks. A shortened clear height is accepted when it still meets the
     * minimum, allowing foliage and branch selection to use the available
     * space.
     *
     * <p>
     * Foliage candidates are tested before soil or logs are changed. Each
     * accepted candidate retains the height where its connecting branch meets
     * the trunk, and a shared trimming rule controls both limb and foliage
     * placement. The trunk is then written before connecting branches and
     * canopies. Individual log replacement failures are skipped, so accepted
     * planning does not provide an atomic tree update or rollback after later
     * errors.
     */
    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        int height = calculateHeight(3, 11, 0);
        if (!canGrowOn(context.getBlockState(x, y - 1, z))) {
            return false;
        }

        int maxFreeTreeHeight = getMaxFreeTreeHeight(context, height, x, y, z, (treeHeight, currentHeight) -> 0, true);
        // A shortened column remains usable only with at least four free levels;
        // shorter obstruction results reject growth before any tree writes.
        if (maxFreeTreeHeight < height && maxFreeTreeHeight < 4) {
            return false;
        }

        int trunkAndFoliageHeight = maxFreeTreeHeight + 2;
        // The trunk occupies the lower 61.8 percent of the selected combined
        // height.
        int trunkTopOffset = (int) Math.floor(trunkAndFoliageHeight * 0.618);
        int branchBaseLimit = y + trunkTopOffset;
        int foliageStart = trunkAndFoliageHeight - 5;
        var foliageCoords = new ArrayList<FoliageCoords>();
        foliageCoords.add(new FoliageCoords(new Vector3i(x, y + foliageStart, z), branchBaseLimit));

        for (int current = foliageStart; current >= 0; current--) {
            float treeShape = treeShape(trunkAndFoliageHeight, current);
            if (treeShape < 0.0f) {
                continue;
            }

            // Randomized spread scales the height profile, and half-block rounding
            // maps its angular endpoint to block coordinates.
            double branchLength = treeShape * (ThreadLocalRandom.current().nextFloat() + 0.328);
            double angle = ThreadLocalRandom.current().nextFloat() * Math.PI * 2.0;
            int branchX = x + (int) Math.floor(branchLength * Math.sin(angle) + 0.5);
            int branchZ = z + (int) Math.floor(branchLength * Math.cos(angle) + 0.5);
            var foliagePos = new Vector3i(branchX, y + current - 1, branchZ);
            var foliageCheckTop = new Vector3i(branchX, y + current + 4, branchZ);
            if (!makeLimb(context, foliagePos, foliageCheckTop, false)) {
                continue;
            }

            int xDiff = x - branchX;
            int zDiff = z - branchZ;
            // Horizontal branch reach lowers its connection point, capped at the
            // selected trunk top.
            double branchBaseY = foliagePos.y() - Math.sqrt(xDiff * xDiff + zDiff * zDiff) * 0.381;
            int attachmentBaseY = branchBaseY > branchBaseLimit ? branchBaseLimit : (int) branchBaseY;
            var branchBase = new Vector3i(x, attachmentBaseY, z);
            if (makeLimb(context, branchBase, foliagePos, false)) {
                foliageCoords.add(new FoliageCoords(foliagePos, attachmentBaseY));
            }
        }

        placeDirtUnder(context, x, y - 1, z);
        makeLimb(context, new Vector3i(x, y, z), new Vector3i(x, y + trunkTopOffset, z), true);

        for (var foliageCoord : foliageCoords) {
            int branchBase = foliageCoord.branchBase() - y;
            if (trimBranches(trunkAndFoliageHeight, branchBase)) {
                var branchStart = new Vector3i(x, foliageCoord.branchBase(), z);
                if (!branchStart.equals(foliageCoord.attachment())) {
                    makeLimb(context, branchStart, foliageCoord.attachment(), true);
                }
            }
        }

        var placedLeaves = new ArrayList<Vector3i>();
        for (var foliageCoord : foliageCoords) {
            if (trimBranches(trunkAndFoliageHeight, foliageCoord.branchBase() - y)) {
                placeFancyFoliage(
                        context,
                        new FoliageAttachment(foliageCoord.attachment().x(), foliageCoord.attachment().y(), foliageCoord.attachment().z(), 0, false),
                        4,
                        2,
                        4,
                        placedLeaves
                );
            }
        }
        return true;
    }

    /**
     * Places rounded foliage layers around one selected branch attachment.
     * Intermediate layers use an extra radius block, and half-block-centered
     * radial clipping removes outer cells before accepted leaf positions are
     * appended to the supplied list.
     */
    private void placeFancyFoliage(
            WorldFeatureContext context,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset,
            List<Vector3i> placedLeaves
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
                        // Half-block offsets round foliage against cell centers rather than grid
                        // corners.
                        float dx = coord.localX() + 0.5f;
                        float dz = coord.localZ() + 0.5f;
                        return dx * dx + dz * dz > rowRange * rowRange;
                    },
                    placedLeaves
            );
        }
    }

    /**
     * Tests or writes the rasterized line between two limb endpoints,
     * including both ends. Check mode accepts existing logs through {@code
     * isFree}; placement mode uses conditional axis-aware log writes and still
     * returns {@code true} when individual writes are rejected.
     *
     * <p>
     * The largest coordinate delta determines the step count so every sample
     * advances along the dominant axis. Other components are interpolated and
     * rounded using a half-block offset. The axis selector compares each
     * sampled position with the original endpoint, keeping horizontal
     * orientation tied to the limb's overall displacement rather than the
     * preceding sample.
     */
    private boolean makeLimb(WorldFeatureContext context, Vector3i from, Vector3i to, boolean place) {
        if (!place && from.equals(to)) {
            return true;
        }

        int deltaX = to.x() - from.x();
        int deltaY = to.y() - from.y();
        int deltaZ = to.z() - from.z();
        // The largest coordinate difference sets enough samples to cover the
        // dominant axis.
        int steps = Math.max(Math.abs(deltaX), Math.max(Math.abs(deltaY), Math.abs(deltaZ)));
        float stepX = (float) deltaX / steps;
        float stepY = (float) deltaY / steps;
        float stepZ = (float) deltaZ / steps;

        for (int i = 0; i <= steps; i++) {
            int currentX = from.x() + (int) Math.floor(0.5f + i * stepX);
            int currentY = from.y() + (int) Math.floor(0.5f + i * stepY);
            int currentZ = from.z() + (int) Math.floor(0.5f + i * stepZ);
            if (place) {
                PillarAxis axis = determineLogAxis(from, new Vector3i(currentX, currentY, currentZ));
                placeLogWithAxisIfValid(context, currentX, currentY, currentZ, axis);
            } else if (!isFree(context, currentX, currentY, currentZ)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the pillar axis selected from the limb endpoint's displacement
     * relative to its origin. The method uses absolute X and Z differences, so
     * east and west share one axis and north and south share another.
     *
     * <p>
     * A zero horizontal displacement selects {@code PillarAxis.Y} even when
     * the endpoints differ vertically. Otherwise the larger horizontal
     * difference selects its corresponding axis, with equal nonzero
     * differences choosing {@code PillarAxis.X}. Vertical displacement does
     * not compete with those horizontal magnitudes, allowing a rising branch
     * to retain its horizontal orientation once it leaves the origin column.
     */
    private PillarAxis determineLogAxis(Vector3i from, Vector3i to) {
        int xDiff = Math.abs(to.x() - from.x());
        int zDiff = Math.abs(to.z() - from.z());
        int max = Math.max(xDiff, zDiff);
        if (max == 0) {
            return PillarAxis.Y;
        }
        return xDiff == max ? PillarAxis.X : PillarAxis.Z;
    }

    /**
     * Returns whether a branch base reaches twenty percent of the chosen
     * height. Placement uses this same test for both connecting limbs and
     * their foliage, preventing the two passes from retaining different low
     * branches.
     */
    private boolean trimBranches(int maxHeight, int currentHeight) {
        // The same lower-fifth cutoff controls both branches and their foliage.
        return currentHeight >= maxHeight * 0.2;
    }

    /**
     * Returns the horizontal branch-spread factor for the requested relative
     * height. The bottom thirty percent returns negative one to exclude
     * foliage candidates, and positions at or beyond the outer vertical radius
     * return zero.
     *
     * <p>
     * Within that band, the factor follows a half-radius circular profile
     * centered on half the selected tree height. The midpoint uses the maximum
     * radius directly, while other positions derive radius from their vertical
     * displacement. The caller combines this factor with random length and
     * angle choices to locate candidate foliage, so a negative result means
     * omission rather than a physical radius.
     */
    private float treeShape(int height, int currentY) {
        // The lower thirty percent excludes foliage candidates beneath the crown.
        if (currentY < height * 0.3f) {
            return -1.0f;
        }

        float midpoint = height / 2.0f;
        float heightFromMid = midpoint - currentY;
        float radius = (float) Math.sqrt(midpoint * midpoint - heightFromMid * heightFromMid);
        if (heightFromMid == 0.0f) {
            radius = midpoint;
        } else if (Math.abs(heightFromMid) >= midpoint) {
            return 0.0f;
        }
        // Halving the circular profile limits horizontal branch spread at each
        // level.
        return radius * 0.5f;
    }

    /**
     * {@code FoliageCoords} associates a selected canopy position with its
     * trunk connection height.
     *
     * @param attachment {@code attachment} retains the selected mutable
     * vector. Placement keeps it stable while branch and foliage passes read
     * it.
     *
     * @param branchBase absolute trunk connection height in blocks
     */
    private record FoliageCoords(Vector3i attachment, int branchBase) {
    }
}
