package org.allaymc.api.math.voxelshape;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.joml.primitives.Rayd;
import org.joml.primitives.Raydc;

import java.util.*;

/**
 * {@code VoxelShape} represents block geometry as a set of axis-aligned solid boxes. Local
 * block geometry normally uses coordinates from zero to one, while translation supports
 * queries in another coordinate space. Box and point queries test individual solids, and
 * face-support queries project only solids exactly aligned with the requested unit-block
 * boundary.
 *
 * <p>
 * Face coverage combines overlapping projected rectangles by splitting the target region
 * at every intersecting rectangle boundary. Testing a point inside each resulting cell
 * detects gaps that a single enclosing box would hide. Ray queries with an output vector
 * instead combine the nearest entry and farthest exit across all hit solids; the returned
 * span can include empty space between separate solids. The builder and generated
 * {@code getSolids()} accessor expose shared storage, so callers must control mutation
 * themselves.
 * @author daoge_cmd
 */
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class VoxelShape {

    /**
     * {@code EMPTY} represents geometry with no solid boxes. Empty transformations reuse
     * this instance, and its solid set does not accept additions through the generated
     * getter.
     */
    public static final VoxelShape EMPTY = new VoxelShape(Collections.emptySet());

    /**
     * {@code EDGE_WIDTH}, {@code CENTER_MIN}, and {@code CENTER_MAX} define the unit-block
     * edge strips and central square tested by the face-support queries.
     */
    private static final double EDGE_WIDTH = 0.125d; // edge-strip width in blocks
    /**
     * Minimum central-support coordinate in local block units.
     */
    private static final double CENTER_MIN = 3d / 8d;
    /**
     * Maximum central-support coordinate in local block units.
     */
    private static final double CENTER_MAX = 5d / 8d;

    /**
     * {@code solids} holds the boxes used by all queries. The constructor retains the set,
     * and the generated getter returns it directly; mutable boxes remain shared as well.
     */
    private final Set<AABBdc> solids;

    /**
     * Returns a new builder with an initially empty solid set.
     */
    public static VoxelShapeBuilder builder() {
        return new VoxelShapeBuilder();
    }

    /**
     * Returns a newly allocated box enclosing every solid in this shape. An empty shape
     * returns a zero-sized box at the origin; an enclosing box can include gaps between
     * separate solids.
     */
    public AABBd unionAABB() {
        if (this.solids.isEmpty()) {
            return new AABBd(0, 0, 0, 0, 0, 0);
        }

        double
                minX = Double.POSITIVE_INFINITY,
                minY = Double.POSITIVE_INFINITY,
                minZ = Double.POSITIVE_INFINITY;
        double
                maxX = Double.NEGATIVE_INFINITY,
                maxY = Double.NEGATIVE_INFINITY,
                maxZ = Double.NEGATIVE_INFINITY;
        for (var solid : this.solids) {
            minX = Math.min(minX, solid.minX());
            minY = Math.min(minY, solid.minY());
            minZ = Math.min(minZ, solid.minZ());

            maxX = Math.max(maxX, solid.maxX());
            maxY = Math.max(maxY, solid.maxY());
            maxZ = Math.max(maxZ, solid.maxZ());
        }

        return new AABBd(
                minX, minY, minZ,
                maxX, maxY, maxZ
        );
    }

    /**
     * Returns a shape whose solid boxes are rotated for {@code face} using
     * {@link BlockFace#rotateAABB(AABBdc)}. Empty geometry returns {@code EMPTY}; nonempty
     * geometry uses a new set of transformed boxes.
     */
    public VoxelShape rotate(BlockFace face) {
        if (this.solids.isEmpty()) {
            return EMPTY;
        }

        Set<AABBdc> newSolids = new HashSet<>(this.solids.size());
        for (var solid : this.solids) {
            newSolids.add(face.rotateAABB(solid));
        }
        return new VoxelShape(newSolids);
    }

    /**
     * Returns a translated shape without modifying the original solid boxes. Empty
     * geometry returns {@code EMPTY}; otherwise each source box is translated into a new
     * box stored in a new set.
     */
    public VoxelShape translate(Vector3dc vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    /**
     * Returns a translated shape without modifying the original solid boxes. Empty
     * geometry returns {@code EMPTY}; otherwise each source box is translated into a new
     * box stored in a new set.
     */
    public VoxelShape translate(double x, double y, double z) {
        if (this.solids.isEmpty()) {
            return EMPTY;
        }

        Set<AABBdc> newSolids = new HashSet<>(this.solids.size());
        for (var solid : this.solids) {
            newSolids.add(solid.translate(x, y, z, new AABBd()));
        }
        return new VoxelShape(newSolids);
    }

    /**
     * Returns whether any solid overlaps or touches {@code other}. The underlying box
     * comparison includes matching boundaries, so this result differs from point
     * containment, which excludes points on a solid boundary.
     */
    public boolean intersectsAABB(AABBdc other) {
        for (var solid : this.solids) {
            if (solid.intersectsAABB(other)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the point lies strictly inside any solid box. A point on a minimum
     * or maximum boundary is excluded by the underlying {@code containsPoint} comparison,
     * even when that boundary touches another solid.
     */
    public boolean intersectsPoint(Vector3dc vec) {
        return this.intersectsPoint(vec.x(), vec.y(), vec.z());
    }

    /**
     * Returns whether the point lies strictly inside any solid box. A point on a minimum
     * or maximum boundary is excluded by the underlying {@code containsPoint} comparison,
     * even when that boundary touches another solid.
     */
    public boolean intersectsPoint(double x, double y, double z) {
        for (var solid : this.solids) {
            if (solid.containsPoint(x, y, z)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether aligned solids cover the complete unit square of {@code face}. Only
     * boxes with a boundary exactly at the face's zero or one coordinate contribute;
     * interior solids do not provide face support.
     */
    public boolean isFull(BlockFace face) {
        var uvRectangles = this.extractAlignedRectangles(face);
        return this.isRegionFullyCovered(0, 1, 0, 1, uvRectangles);
    }

    /**
     * Returns whether aligned solids cover the central square of {@code face}. Both
     * projected axes range inclusively from {@code 3/8} to {@code 5/8}; only solids
     * exactly aligned with the unit-block face contribute.
     */
    public boolean isCenterFull(BlockFace face) {
        var uvRectangles = this.extractAlignedRectangles(face);
        return this.isRegionFullyCovered(CENTER_MIN, CENTER_MAX, CENTER_MIN, CENTER_MAX, uvRectangles);
    }

    /**
     * Returns whether aligned solids cover every perimeter strip of {@code face}. Each
     * strip is one eighth of a block wide, and the central area does not need to be
     * covered.
     */
    public boolean isEdgeFull(BlockFace face) {
        var uvRectangles = this.extractAlignedRectangles(face);
        // Edge support requires every strip to be covered; coverage of one strip cannot
        // compensate for a gap in another.
        for (var edge : this.getEdgeRegions(face, EDGE_WIDTH)) {
            if (!this.isRegionFullyCovered(edge[0], edge[1], edge[2], edge[3], uvRectangles)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns projected bounds for solids exactly touching the requested unit-block face.
     * Each new array stores {@code minU}, {@code maxU}, {@code minV}, and {@code maxV},
     * retaining neither the solid nor its mutable box storage.
     */
    private List<double[]> extractAlignedRectangles(BlockFace face) {
        List<double[]> rectangles = new ArrayList<>(this.solids.size());
        for (var solid : this.solids) {
            if (this.isAlignedWithFace(solid, face)) {
                rectangles.add(this.getUVMinMax(solid, face));
            }
        }
        return rectangles;
    }

    /**
     * Returns whether the rectangle union covers the requested projected region. The
     * target bounds and each positive-area intersection supply sorted grid boundaries.
     * Within one resulting cell, no rectangle boundary crosses its interior, so testing
     * its center is enough to decide whether that cell is covered.
     *
     * <p>
     * The method returns {@code false} when no rectangles exist or when any cell center is
     * uncovered. Rectangle clipping contributes boundaries only; the final containment
     * check still uses each original rectangle. This preserves overlapping contributions
     * without counting overlap twice or mistaking their enclosing bounds for complete
     * coverage.
     */
    private boolean isRegionFullyCovered(
            double regionMinU, double regionMaxU,
            double regionMinV, double regionMaxV,
            List<double[]> uvRectangles
    ) {
        if (uvRectangles.isEmpty()) {
            return false;
        }

        // Split only where coverage can change, so a cell center represents its entire interior
        // rather than an arbitrary sample.
        Set<Double> uBoundaries = new TreeSet<>();
        Set<Double> vBoundaries = new TreeSet<>();
        uBoundaries.add(regionMinU);
        uBoundaries.add(regionMaxU);
        vBoundaries.add(regionMinV);
        vBoundaries.add(regionMaxV);

        for (var rect : uvRectangles) {
            // Ignore zero-area intersections when introducing grid boundaries; they cannot
            // cover an interior cell in the requested region.
            var u0 = Math.max(regionMinU, rect[0]);
            var u1 = Math.min(regionMaxU, rect[1]);
            var v0 = Math.max(regionMinV, rect[2]);
            var v1 = Math.min(regionMaxV, rect[3]);
            if (u0 < u1 && v0 < v1) {
                uBoundaries.add(u0);
                uBoundaries.add(u1);
                vBoundaries.add(v0);
                vBoundaries.add(v1);
            }
        }

        List<Double> uList = new ArrayList<>(uBoundaries);
        List<Double> vList = new ArrayList<>(vBoundaries);
        // Adjacent sorted boundaries form cells with constant rectangle membership, allowing
        // one midpoint test instead of area summation.
        for (int i = 0; i < uList.size() - 1; i++) {
            var u0 = uList.get(i);
            var u1 = uList.get(i + 1);
            var uc = (u0 + u1) / 2d;
            for (int j = 0; j < vList.size() - 1; j++) {
                var v0 = vList.get(j);
                var v1 = vList.get(j + 1);
                var vc = (v0 + v1) / 2d;
                // Overlap contributes coverage once: one containing rectangle is sufficient,
                // while an uncovered center proves a gap in this cell.
                boolean covered = false;
                for (var rect : uvRectangles) {
                    if (uc >= rect[0] && uc <= rect[1] &&
                        vc >= rect[2] && vc <= rect[3]) {
                        covered = true;
                        break;
                    }
                }

                if (!covered) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Returns whether all six unit-block faces are fully supported by solids. This tests
     * exterior face coverage rather than interior volume, so a hollow shape can still
     * satisfy the result.
     */
    public boolean isFullBlock() {
        for (var face : BlockFace.VALUES) {
            if (!this.isFull(face)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns whether the supplied ray hits any solid in this shape. Direction components
     * need not be normalized; the operation delegates each box test to JOML and does not
     * change the ray or shape.
     */
    public boolean intersectsRay(double ox, double oy, double oz, double dx, double dy, double dz) {
        return intersectsRay(new Vector3d(ox, oy, oz), new Vector3d(dx, dy, dz));
    }

    /**
     * Returns whether the supplied ray hits any solid in this shape. Direction components
     * need not be normalized; the operation delegates each box test to JOML and does not
     * change the ray or shape.
     */
    public boolean intersectsRay(Vector3dc origin, Vector3dc direction) {
        return intersectsRay(new Rayd(origin, direction));
    }

    /**
     * Returns whether the supplied ray hits any solid in this shape. Direction components
     * need not be normalized; the operation delegates each box test to JOML and does not
     * change the ray or shape.
     */
    public boolean intersectsRay(Raydc ray) {
        for (var solid : this.solids) {
            if (solid.intersectsRay(ray)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tests the supplied ray coordinates and stores the combined solid-hit span in
     * {@code result}. The result follows {@link #intersectsRay(Raydc, Vector2d)},
     * including its miss sentinel and possible gaps between hit solids.
     */
    public boolean intersectsRay(double ox, double oy, double oz, double dx, double dy, double dz, Vector2d result) {
        return intersectsRay(new Vector3d(ox, oy, oz), new Vector3d(dx, dy, dz), result);
    }

    /**
     * Tests the supplied ray coordinates and stores the combined solid-hit span in
     * {@code result}. The result follows {@link #intersectsRay(Raydc, Vector2d)},
     * including its miss sentinel and possible gaps between hit solids.
     */
    public boolean intersectsRay(Vector3dc origin, Vector3dc direction, Vector2d result) {
        return intersectsRay(new Rayd(origin, direction), result);
    }

    /**
     * Returns whether the ray hits any solid and writes the combined hit span to
     * {@code result}. Successful output contains the smallest entry parameter and largest
     * exit parameter in {@code origin + t * direction}, rather than separate intervals for
     * separate solids.
     *
     * <p>
     * The combined span can cross empty space between solids. A miss writes
     * {@code Double.NEGATIVE_INFINITY} and {@code Double.POSITIVE_INFINITY}, so callers
     * must inspect the boolean result before treating the output as a hit. The underlying
     * box intersection can return a negative entry parameter when the ray begins inside a
     * box.
     */
    public boolean intersectsRay(Raydc ray, Vector2d result) {
        var nearestNear = Double.POSITIVE_INFINITY;
        var farthestFar = Double.NEGATIVE_INFINITY;

        var hasIntersection = false;
        var vec = new Vector2d();

        for (var solid : this.solids) {
            if (solid.intersectsRay(ray, vec)) {
                hasIntersection = true;

                // Compare ordered entry and exit parameters before merging solids, so the
                // combined span retains both extremes regardless of box order.
                if (vec.x > vec.y) {
                    var temp = vec.x;
                    vec.x = vec.y;
                    vec.y = temp;
                }

                nearestNear = Math.min(nearestNear, vec.x);
                farthestFar = Math.max(farthestFar, vec.y);
            }
        }

        // A miss still overwrites the output, preventing callers from accidentally reusing an
        // earlier successful interval after checking the result.
        if (hasIntersection) {
            result.set(nearestNear, farthestFar);
        } else {
            result.set(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        return hasIntersection;
    }

    /**
     * Returns four projected rectangles describing the requested face's perimeter strips.
     * {@code edgeWidth} uses block-coordinate units, and the returned arrays are newly
     * allocated so callers can adjust them independently of later calls.
     */
    private List<double[]> getEdgeRegions(BlockFace face, double edgeWidth) {
        List<double[]> edgeRegions = new ArrayList<>();

        // The full-height side strips include corners, so the remaining strips cover only the
        // width between those corners.
        double[][] templateEdges = {
                {0, edgeWidth, 0, 1},
                {1 - edgeWidth, 1, 0, 1},
                {edgeWidth, 1 - edgeWidth, 0, edgeWidth},
                {edgeWidth, 1 - edgeWidth, 1 - edgeWidth, 1}
        };

        // Preserve perimeter coverage after projection; the west/east mapping swaps projected
        // axes to match that face orientation.
        switch (face) {
            case UP, DOWN -> {
                return Arrays.asList(templateEdges);
            }
            case NORTH, SOUTH -> {
                for (var edge : templateEdges) {
                    edgeRegions.add(new double[]{edge[0], edge[1], edge[2], edge[3]});
                }
            }
            case WEST, EAST -> {
                for (var edge : templateEdges) {
                    edgeRegions.add(new double[]{edge[2], edge[3], edge[0], edge[1]});
                }
            }
        }

        return edgeRegions;
    }

    /**
     * Returns whether the selected solid boundary exactly equals the unit-block face
     * coordinate. The comparison uses no tolerance, so translated or nearly aligned solids
     * do not automatically qualify as face support.
     */
    private boolean isAlignedWithFace(AABBdc solid, BlockFace face) {
        return switch (face) {
            case UP -> solid.maxY() == 1;
            case DOWN -> solid.minY() == 0;
            case NORTH -> solid.minZ() == 0;
            case SOUTH -> solid.maxZ() == 1;
            case WEST -> solid.minX() == 0;
            case EAST -> solid.maxX() == 1;
        };
    }

    /**
     * Returns a new array containing the solid's projected face bounds. The array order is
     * {@code minU}, {@code maxU}, {@code minV}, {@code maxV}; horizontal faces use the
     * horizontal coordinate plane.
     */
    private double[] getUVMinMax(AABBdc solid, BlockFace face) {
        return switch (face) {
            case UP, DOWN -> new double[]{solid.minX(), solid.maxX(), solid.minZ(), solid.maxZ()};
            case NORTH, SOUTH -> new double[]{solid.minX(), solid.maxX(), solid.minY(), solid.maxY()};
            case WEST, EAST -> new double[]{solid.minZ(), solid.maxZ(), solid.minY(), solid.maxY()};
        };
    }

    /**
     * {@code VoxelShapeBuilder} accumulates solid boxes in a shared mutable set. Built
     * shapes retain that same set, and box references are not copied before queries use
     * them.
     */
    public static class VoxelShapeBuilder {
        /**
         * {@code solids} stores this builder's boxes and backs every shape it builds.
         * Additions remain visible through those shapes because {@code build()} does not
         * copy the set.
         */
        private final Set<AABBdc> solids = new HashSet<>();

        /**
         * Adds a solid box to this builder and returns this builder. The box overload
         * retains the supplied reference without copying, so subsequent box changes affect
         * every shape built from this set.
         */
        public VoxelShapeBuilder solid(AABBdc solid) {
            this.solids.add(solid);
            return this;
        }

        /**
         * Adds a newly allocated box with the supplied minimum and maximum coordinates.
         * This builder returns itself for chaining and retains the box in the same shared
         * set used by built shapes.
         */
        public VoxelShapeBuilder solid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return this.solid(new AABBd(minX, minY, minZ, maxX, maxY, maxZ));
        }

        /**
         * Returns a shape backed by this builder's current solid set. The set is retained
         * without copying, so later builder additions also change previously built shapes
         * and their query results.
         */
        public VoxelShape build() {
            return new VoxelShape(this.solids);
        }
    }
}
