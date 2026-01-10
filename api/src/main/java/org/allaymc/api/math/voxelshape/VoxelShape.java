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
 * VoxelShape represents the shape of a block.
 * <p>
 * Compared to aabb, voxel shape can have multiple solid areas. which means
 * it can represent a more complex shape (stairs, walls).
 *
 * @author daoge_cmd
 */
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class VoxelShape {

    public static final VoxelShape EMPTY = new VoxelShape(Collections.emptySet());

    private static final double EDGE_WIDTH = 0.125d;
    private static final double CENTER_MIN = 3d / 8d;
    private static final double CENTER_MAX = 5d / 8d;

    private final Set<AABBdc> solids;

    public static VoxelShapeBuilder builder() {
        return new VoxelShapeBuilder();
    }

    /**
     * Calculate the minimum AABB that can contain this voxel shape.
     *
     * @return the minimum AABB that can contain this voxel shape
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
     * Rotate this voxel shape by the specified face.
     *
     * @param face the face to rotate the voxel shape
     * @return the rotated voxel shape
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
     * Add a specified offset to this voxel shape.
     *
     * @param vec the offset vector
     * @return the translated voxel shape
     */
    public VoxelShape translate(Vector3dc vec) {
        return this.translate(vec.x(), vec.y(), vec.z());
    }

    /**
     * Add a specified offset to this voxel shape.
     *
     * @param x the x offset
     * @param y the y offset
     * @param z the z offset
     * @return the translated voxel shape
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
     * Check if this voxel shape intersects with the specified AABB.
     *
     * @param other the AABB to check
     * @return true if this voxel shape intersects with the specified AABB, otherwise false
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
     * Check if this voxel shape intersects with the specified point.
     *
     * @param vec the point to check
     * @return {@code true} if this voxel shape intersects with the specified point, otherwise {@code false}.
     */
    public boolean intersectsPoint(Vector3dc vec) {
        return this.intersectsPoint(vec.x(), vec.y(), vec.z());
    }

    /**
     * Check if this voxel shape intersects with the specified point.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param z the z coordinate of the point
     * @return {@code true} if this voxel shape intersects with the specified point, otherwise {@code false}.
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
     * Checks if the specified face is "full", i.e. whether the [0,1]×[0,1] region of that face
     * is completely covered.
     *
     * @param face the face to check
     * @return true if the face is completely covered; otherwise, false
     */
    public boolean isFull(BlockFace face) {
        var uvRectangles = this.extractAlignedRectangles(face);
        return this.isRegionFullyCovered(0, 1, 0, 1, uvRectangles);
    }

    /**
     * Checks if the center of the specified face is full. The center is defined as the region
     * [3/8, 5/8]×[3/8, 5/8].
     *
     * @param face the face to check
     * @return true if the center region is completely covered; otherwise, false
     */
    public boolean isCenterFull(BlockFace face) {
        var uvRectangles = this.extractAlignedRectangles(face);
        return this.isRegionFullyCovered(CENTER_MIN, CENTER_MAX, CENTER_MIN, CENTER_MAX, uvRectangles);
    }

    /**
     * Checks if the edges of the specified face are full. The edge region is defined by the given
     * edgeWidth (here, 0.125).
     *
     * @param face the face to check
     * @return true if all edge regions are completely covered; otherwise, false
     */
    public boolean isEdgeFull(BlockFace face) {
        var uvRectangles = this.extractAlignedRectangles(face);
        // Retrieve the edge regions for the face (each represented as [minU, maxU, minV, maxV])
        for (var edge : this.getEdgeRegions(face, EDGE_WIDTH)) {
            if (!this.isRegionFullyCovered(edge[0], edge[1], edge[2], edge[3], uvRectangles)) {
                return false;
            }
        }
        return true;
    }

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
     * Helper method: Checks whether the union of the provided UV rectangles completely covers the specified region.
     * The method divides the target region into a grid using all boundary lines (from both the target region and
     * the intersected rectangles) and then verifies that each cell's center is covered by at least one rectangle.
     *
     * @param regionMinU   the minimum U coordinate of the target region
     * @param regionMaxU   the maximum U coordinate of the target region
     * @param regionMinV   the minimum V coordinate of the target region
     * @param regionMaxV   the maximum V coordinate of the target region
     * @param uvRectangles a list of rectangles, each represented as [minU, maxU, minV, maxV]
     * @return true if the union of the rectangles completely covers the target region; otherwise, false
     */
    private boolean isRegionFullyCovered(
            double regionMinU, double regionMaxU,
            double regionMinV, double regionMaxV,
            List<double[]> uvRectangles
    ) {
        if (uvRectangles.isEmpty()) {
            return false;
        }

        // Collect boundaries from the target region and the intersections with each rectangle.
        Set<Double> uBoundaries = new TreeSet<>();
        Set<Double> vBoundaries = new TreeSet<>();
        uBoundaries.add(regionMinU);
        uBoundaries.add(regionMaxU);
        vBoundaries.add(regionMinV);
        vBoundaries.add(regionMaxV);

        for (var rect : uvRectangles) {
            // Compute the intersection of the rectangle with the target region.
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
        // Iterate over each sub-cell defined by adjacent boundaries.
        for (int i = 0; i < uList.size() - 1; i++) {
            var u0 = uList.get(i);
            var u1 = uList.get(i + 1);
            var uc = (u0 + u1) / 2d; // Sample point (cell center)
            for (int j = 0; j < vList.size() - 1; j++) {
                var v0 = vList.get(j);
                var v1 = vList.get(j + 1);
                var vc = (v0 + v1) / 2d;
                // Check if the cell center is covered by any rectangle.
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
     * Check if this voxel shape is full block.
     * <p>
     * Full block means that all faces of this voxel shape are full.
     *
     * @return {@code true} if this voxel shape is full block, otherwise {@code false}.
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
     * @see #intersectsRay(Raydc)
     */
    public boolean intersectsRay(double ox, double oy, double oz, double dx, double dy, double dz) {
        return intersectsRay(new Vector3d(ox, oy, oz), new Vector3d(dx, dy, dz));
    }

    /**
     * @see #intersectsRay(Raydc)
     */
    public boolean intersectsRay(Vector3dc origin, Vector3dc direction) {
        return intersectsRay(new Rayd(origin, direction));
    }

    /**
     * Determine whether the given ray intersects this voxel shape.
     *
     * @param ray the ray to check
     * @return {@code true} if the ray intersects this voxel shape, otherwise {@code false}.
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
     * @see #intersectsRay(Raydc, Vector2d)
     */
    public boolean intersectsRay(double ox, double oy, double oz, double dx, double dy, double dz, Vector2d result) {
        return intersectsRay(new Vector3d(ox, oy, oz), new Vector3d(dx, dy, dz), result);
    }

    /**
     * @see #intersectsRay(Raydc, Vector2d)
     */
    public boolean intersectsRay(Vector3dc origin, Vector3dc direction, Vector2d result) {
        return intersectsRay(new Rayd(origin, direction), result);
    }

    /**
     * Determine whether the given ray intersects this voxel shape. This method is similar to
     * {@link #intersectsRay(Raydc)} but will save the intersection result.
     *
     * @param ray    the ray to check
     * @param result a vector which will hold the resulting values of the parameter <i>t</i> in the ray equation <i>p(t) = origin + t * dir</i>
     *               of the near and far point of intersection if the ray intersects this VoxelShape. Notes that {@code result.y()} will be bigger
     *               than {@code result.x()}. If the ray does not intersect this VoxelShape, the result will be {@code (Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)}.
     * @return {@code true} if the ray intersects this voxel shape, otherwise {@code false}.
     */
    public boolean intersectsRay(Raydc ray, Vector2d result) {
        var nearestNear = Double.POSITIVE_INFINITY;
        var farthestFar = Double.NEGATIVE_INFINITY;

        var hasIntersection = false;
        var vec = new Vector2d();

        for (var solid : this.solids) {
            if (solid.intersectsRay(ray, vec)) {
                hasIntersection = true;

                // Ensure vec.x <= vec.y
                if (vec.x > vec.y) {
                    var temp = vec.x;
                    vec.x = vec.y;
                    vec.y = temp;
                }

                nearestNear = Math.min(nearestNear, vec.x);
                farthestFar = Math.max(farthestFar, vec.y);
            }
        }

        if (hasIntersection) {
            result.set(nearestNear, farthestFar);
        } else {
            result.set(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        return hasIntersection;
    }

    private List<double[]> getEdgeRegions(BlockFace face, double edgeWidth) {
        List<double[]> edgeRegions = new ArrayList<>();

        // Define templates for the four edge regions
        double[][] templateEdges = {
                {0, edgeWidth, 0, 1},                        // First edge
                {1 - edgeWidth, 1, 0, 1},                    // Corresponding opposite edge
                {edgeWidth, 1 - edgeWidth, 0, edgeWidth},    // Third edge
                {edgeWidth, 1 - edgeWidth, 1 - edgeWidth, 1} // Corresponding opposite edge
        };

        // Adjust the edge coordinates based on the plane of the BlockFace
        switch (face) {
            // X-Z plane
            case UP, DOWN -> {
                return Arrays.asList(templateEdges);
            }
            case NORTH, SOUTH -> {
                // X-Y plane, swap V and U
                for (var edge : templateEdges) {
                    edgeRegions.add(new double[]{edge[0], edge[1], edge[2], edge[3]});
                }
            }
            case WEST, EAST -> {
                // Y-Z plane, swap U and V
                for (var edge : templateEdges) {
                    edgeRegions.add(new double[]{edge[2], edge[3], edge[0], edge[1]});
                }
            }
        }

        return edgeRegions;
    }

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

    private double[] getUVMinMax(AABBdc solid, BlockFace face) {
        return switch (face) {
            case UP, DOWN -> new double[]{solid.minX(), solid.maxX(), solid.minZ(), solid.maxZ()};
            case NORTH, SOUTH -> new double[]{solid.minX(), solid.maxX(), solid.minY(), solid.maxY()};
            case WEST, EAST -> new double[]{solid.minZ(), solid.maxZ(), solid.minY(), solid.maxY()};
        };
    }

    public static class VoxelShapeBuilder {
        private final Set<AABBdc> solids = new HashSet<>();

        /**
         * Add a solid area to the voxel shape.
         *
         * @param solid the solid area to add
         * @return this builder
         */
        public VoxelShapeBuilder solid(AABBdc solid) {
            this.solids.add(solid);
            return this;
        }

        /**
         * Add a solid area to the voxel shape.
         *
         * @param minX the minimum x coordinate of the solid area
         * @param minY the minimum y coordinate of the solid area
         * @param minZ the minimum z coordinate of the solid area
         * @param maxX the maximum x coordinate of the solid area
         * @param maxY the maximum y coordinate of the solid area
         * @param maxZ the maximum z coordinate of the solid area
         * @return this builder
         */
        public VoxelShapeBuilder solid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return this.solid(new AABBd(minX, minY, minZ, maxX, maxY, maxZ));
        }

        /**
         * Build the voxel shape.
         *
         * @return the voxel shape
         */
        public VoxelShape build() {
            return new VoxelShape(this.solids);
        }
    }
}
