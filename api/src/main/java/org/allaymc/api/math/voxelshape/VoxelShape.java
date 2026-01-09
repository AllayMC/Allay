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
import java.util.stream.Collectors;

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
        double
                minX = Double.MAX_VALUE,
                minY = Double.MAX_VALUE,
                minZ = Double.MAX_VALUE;
        double
                maxX = -Double.MAX_VALUE,
                maxY = -Double.MAX_VALUE,
                maxZ = -Double.MAX_VALUE;
        for (var solid : solids) {
            if (solid.minX() < minX) minX = solid.minX();
            if (solid.minY() < minY) minY = solid.minY();
            if (solid.minZ() < minZ) minZ = solid.minZ();
            if (solid.maxX() > maxX) maxX = solid.maxX();
            if (solid.maxY() > maxY) maxY = solid.maxY();
            if (solid.maxZ() > maxZ) maxZ = solid.maxZ();
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
        Set<AABBdc> newSolids = solids.stream()
                .map(face::rotateAABB)
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids);
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
        Set<AABBdc> newSolids = solids.stream()
                .map(solid -> solid.translate(x, y, z, new AABBd()))
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids);
    }

    /**
     * Add a specified offset to this voxel shape.
     *
     * @param vec the offset vector
     * @return the translated voxel shape
     */
    public VoxelShape translate(Vector3dc vec) {
        // This method is frequently called in physics calculation,
        // So performance in mind

        // Set the size of the set as we know that how many entries
        // will be put into this set to speed up.
        var newSolids = new HashSet<AABBdc>(solids.size());
        // Simply use for-each instead of stream to get better performance
        for (var solid : solids) {
            newSolids.add(solid.translate(vec, new AABBd()));
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
        return solids.stream().anyMatch(solid -> solid.intersectsAABB(other));
    }

    /**
     * Check if this voxel shape intersects with the specified point.
     *
     * @param vec the point to check
     * @return {@code true} if this voxel shape intersects with the specified point, otherwise {@code false}.
     */
    public boolean intersectsPoint(Vector3dc vec) {
        return solids.stream().anyMatch(solid -> solid.containsPoint(vec));
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
        return solids.stream().anyMatch(solid -> solid.containsPoint(x, y, z));
    }

    /**
     * Checks if the specified face is "full", i.e. whether the [0,1]×[0,1] region of that face
     * is completely covered.
     *
     * @param face the face to check
     * @return true if the face is completely covered; otherwise, false
     */
    public boolean isFull(BlockFace face) {
        List<double[]> uvRectangles = new ArrayList<>();
        for (AABBdc solid : solids) {
            if (isAlignedWithFace(solid, face)) {
                uvRectangles.add(getUVMinMax(solid, face));
            }
        }
        return isRegionFullyCovered(0.0, 1.0, 0.0, 1.0, uvRectangles);
    }

    /**
     * Checks if the center of the specified face is full. The center is defined as the region
     * [3/8, 5/8]×[3/8, 5/8].
     *
     * @param face the face to check
     * @return true if the center region is completely covered; otherwise, false
     */
    public boolean isCenterFull(BlockFace face) {
        List<double[]> uvRectangles = new ArrayList<>();
        for (AABBdc solid : solids) {
            if (isAlignedWithFace(solid, face)) {
                uvRectangles.add(getUVMinMax(solid, face));
            }
        }
        double centerMin = 3.0 / 8.0;
        double centerMax = 5.0 / 8.0;
        return isRegionFullyCovered(centerMin, centerMax, centerMin, centerMax, uvRectangles);
    }

    /**
     * Checks if the edges of the specified face are full. The edge region is defined by the given
     * edgeWidth (here, 0.125).
     *
     * @param face the face to check
     * @return true if all edge regions are completely covered; otherwise, false
     */
    public boolean isEdgeFull(BlockFace face) {
        double edgeWidth = 0.125;
        List<double[]> uvRectangles = new ArrayList<>();
        for (AABBdc solid : solids) {
            if (isAlignedWithFace(solid, face)) {
                uvRectangles.add(getUVMinMax(solid, face));
            }
        }
        // Retrieve the edge regions for the face (each represented as [minU, maxU, minV, maxV])
        for (double[] edge : getEdgeRegions(face, edgeWidth)) {
            if (!isRegionFullyCovered(edge[0], edge[1], edge[2], edge[3], uvRectangles)) {
                return false;
            }
        }
        return true;
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
    private boolean isRegionFullyCovered(double regionMinU, double regionMaxU,
                                         double regionMinV, double regionMaxV,
                                         List<double[]> uvRectangles) {
        // Collect boundaries from the target region and the intersections with each rectangle.
        TreeSet<Double> uBoundaries = new TreeSet<>();
        TreeSet<Double> vBoundaries = new TreeSet<>();
        uBoundaries.add(regionMinU);
        uBoundaries.add(regionMaxU);
        vBoundaries.add(regionMinV);
        vBoundaries.add(regionMaxV);
        for (double[] rect : uvRectangles) {
            // Compute the intersection of the rectangle with the target region.
            double u0 = Math.max(regionMinU, rect[0]);
            double u1 = Math.min(regionMaxU, rect[1]);
            double v0 = Math.max(regionMinV, rect[2]);
            double v1 = Math.min(regionMaxV, rect[3]);
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
            double u0 = uList.get(i);
            double u1 = uList.get(i + 1);
            double uc = (u0 + u1) / 2.0; // Sample point (cell center)
            for (int j = 0; j < vList.size() - 1; j++) {
                double v0 = vList.get(j);
                double v1 = vList.get(j + 1);
                double vc = (v0 + v1) / 2.0;
                // Check if the cell center is covered by any rectangle.
                boolean covered = false;
                for (double[] rect : uvRectangles) {
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
        return Arrays.stream(BlockFace.VALUES).allMatch(this::isFull);
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
        return solids.stream().anyMatch(solid -> solid.intersectsRay(ray));
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
        result.set(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
        var hasIntersection = false;
        for (var solid : solids) {
            var vec = new Vector2d();
            if (solid.intersectsRay(ray, vec)) {
                order(vec);
                hasIntersection = true;
                if (vec.x < result.x) {
                    result.x = vec.x;
                }
                if (vec.y > result.y) {
                    result.y = vec.y;
                }
            }
        }

        if (!hasIntersection) {
            result.set(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        return hasIntersection;
    }

    private Vector2d order(Vector2d vec) {
        return vec.x > vec.y ? swap(vec) : vec;
    }

    private Vector2d swap(Vector2d vec) {
        double temp = vec.x;
        vec.x = vec.y;
        vec.y = temp;
        return vec;
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
            case UP, DOWN -> edgeRegions.addAll(Arrays.asList(templateEdges));
            case NORTH, SOUTH -> {
                // X-Y plane, swap V and U
                for (double[] edge : templateEdges) {
                    edgeRegions.add(new double[]{edge[0], edge[1], edge[2], edge[3]});
                }
            }
            case WEST, EAST -> {
                // Y-Z plane, swap U and V
                for (double[] edge : templateEdges) {
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
            solids.add(solid);
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
            return solid(new AABBd(minX, minY, minZ, maxX, maxY, maxZ));
        }

        /**
         * Build the voxel shape.
         *
         * @return the voxel shape
         */
        public VoxelShape build() {
            return new VoxelShape(solids);
        }
    }
}
