package org.allaymc.api.math.voxelshape;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.joml.primitives.Rayd;

import java.util.*;
import java.util.stream.Collectors;

/**
 * VoxelShape represents the shape of a block.
 * <p>
 * Compared to aabb, voxel shape can have multiple solid and vacancy areas. which means
 * it can represent a more complex shape (stairs, walls).
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class VoxelShape {

    public static final VoxelShape EMPTY = new VoxelShape(Collections.emptySet(), Collections.emptySet());

    private final Set<AABBdc> solids;
    private final Set<AABBdc> vacancies;

    public static VoxelShapeBuilder builder() {
        return new VoxelShapeBuilder();
    }

    /**
     * Calculate the minimum AABB that can contain this voxel shape.
     *
     * @return the minimum AABB that can contain this voxel shape.
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
     * @param face the face to rotate the voxel shape.
     *
     * @return the rotated voxel shape.
     */
    public VoxelShape rotate(BlockFace face) {
        Set<AABBdc> newSolids = solids.stream()
                .map(face::rotateAABB)
                .collect(Collectors.toSet());
        Set<AABBdc> newVacancies = vacancies.stream()
                .map(face::rotateAABB)
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids, newVacancies);
    }

    /**
     * Add a specified offset to this voxel shape.
     *
     * @param x the x offset.
     * @param y the y offset.
     * @param z the z offset.
     *
     * @return the translated voxel shape.
     */
    public VoxelShape translate(double x, double y, double z) {
        Set<AABBdc> newSolids = solids.stream()
                .map(solid -> solid.translate(x, y, z, new AABBd()))
                .collect(Collectors.toSet());
        Set<AABBdc> newVacancies = vacancies.stream()
                .map(vacancy -> vacancy.translate(x, y, z, new AABBd()))
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids, newVacancies);
    }

    /**
     * Add a specified offset to this voxel shape.
     *
     * @param vec the offset vector.
     *
     * @return the translated voxel shape.
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
        var newVacancies = new HashSet<AABBdc>(vacancies.size());
        for (var vacancy : vacancies) {
            newVacancies.add(vacancy.translate(vec, new AABBd()));
        }
        return new VoxelShape(newSolids, newVacancies);
    }

    /**
     * Check if this voxel shape intersects with the specified AABB.
     *
     * @param other the AABB to check.
     *
     * @return true if this voxel shape intersects with the specified AABB, otherwise false.
     */
    public boolean intersectsAABB(AABBdc other) {
        var aabb = unionAABB();
        // TODO: This is a bug in JOML-primitives
        if (!aabb.intersectsAABB(other)) return false;

        other.intersection(aabb, aabb);
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsAABB(aabb))) return false;
        return solids.stream().anyMatch(solid -> solid.intersectsAABB(aabb));
    }

    /**
     * Check if this voxel shape intersects with the specified point.
     *
     * @param vec the point to check.
     *
     * @return {@code true} if this voxel shape intersects with the specified point, otherwise {@code false}.
     */
    public boolean intersectsPoint(Vector3dc vec) {
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsPoint(vec))) return false;
        return solids.stream().anyMatch(solid -> solid.containsPoint(vec));
    }

    /**
     * Check if this voxel shape intersects with the specified point.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     * @param z the z coordinate of the point.
     *
     * @return {@code true} if this voxel shape intersects with the specified point, otherwise {@code false}.
     */
    public boolean intersectsPoint(double x, double y, double z) {
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsPoint(x, y, z))) return false;
        return solids.stream().anyMatch(solid -> solid.containsPoint(x, y, z));
    }

    /**
     * Check if the specified face of this voxel shape is full.
     *
     * @param face the face to check.
     *
     * @return {@code true} if the specified face of this voxel shape is full, otherwise {@code false}.
     */
    public boolean isFull(BlockFace face) {
        // Check if vacancies cause any gaps on the face
        for (AABBdc vacancy : vacancies) {
            if (isAlignedWithFace(vacancy, face)) {
                double[] uvMinMax = getUVMinMax(vacancy, face);
                // As long as there is any gap, the face is incomplete
                if (uvMinMax[0] < 1 || uvMinMax[1] > 0f || uvMinMax[2] < 1 || uvMinMax[3] > 0f) {
                    return false;
                }
            }
        }

        double minU = Double.MAX_VALUE, maxU = -Double.MAX_VALUE;
        double minV = Double.MAX_VALUE, maxV = -Double.MAX_VALUE;

        // Calculate the range covered by solids
        for (AABBdc solid : solids) {
            if (isAlignedWithFace(solid, face)) {
                double[] uvMinMax = getUVMinMax(solid, face);
                minU = Math.min(minU, uvMinMax[0]);
                maxU = Math.max(maxU, uvMinMax[1]);
                minV = Math.min(minV, uvMinMax[2]);
                maxV = Math.max(maxV, uvMinMax[3]);
            }
        }

        // If the solids do not completely cover the face, return false
        // If the face is fully covered by solids with no gaps, return true
        return minU == 0 && maxU == 1 && minV == 0 && maxV == 1;
    }

    /**
     * Check if the specified face of this voxel shape is center full.
     *
     * @param face the face to check.
     *
     * @return {@code true} if the specified face of this voxel shape is center full, otherwise {@code false}.
     */
    public boolean isCenterFull(BlockFace face) {
        // The boundaries of the center region, from 3/8 to 5/8
        double centerMinUV = (double) 3 / 8;
        double centerMaxUV = (double) 5 / 8;

        // First, check if any vacancy affects the center region
        boolean vacancyAffectsCenter = vacancies.stream()
                .filter(vacancy -> isAlignedWithFace(vacancy, face))
                .anyMatch(vacancy -> {
                    double[] uvMinMax = getUVMinMax(vacancy, face);
                    return uvMinMax[0] < centerMaxUV && uvMinMax[1] > centerMinUV &&
                           uvMinMax[2] < centerMaxUV && uvMinMax[3] > centerMinUV;
                });

        if (vacancyAffectsCenter) {
            return false; // If a vacancy affects the center region, return false
        }

        // Then check if the solid fully covers the center region
        // If the solid fully covers the center region, return true
        return solids.stream()
                .filter(solid -> isAlignedWithFace(solid, face))
                .anyMatch(solid -> {
                    double[] uvMinMax = getUVMinMax(solid, face);
                    return uvMinMax[0] <= centerMinUV && uvMinMax[1] >= centerMaxUV &&
                           uvMinMax[2] <= centerMinUV && uvMinMax[3] >= centerMaxUV;
                });
    }

    /**
     * Check if this voxel shape is full block.
     * <p>
     * Full block means that all faces of this voxel shape are full.
     *
     * @return {@code true} if this voxel shape is full block, otherwise {@code false}.
     */
    public boolean isFullBlock() {
        return Arrays.stream(BlockFace.values()).allMatch(this::isFull);
    }

    /**
     * Check if the specified face of this voxel shape is edge full.
     *
     * @param face the face to check.
     *
     * @return {@code true} if the specified face of this voxel shape is edge full, otherwise {@code false}.
     */
    public boolean isEdgeFull(BlockFace face) {
        // Define the width of the edge region
        double edgeWidth = 0.125;

        // Get the boundaries of all edge regions
        List<double[]> edgeRegions = getEdgeRegions(face, edgeWidth);

        // Check if any vacancy affects any edge region
        boolean vacancyAffectsEdge = vacancies.stream()
                .filter(vacancy -> isAlignedWithFace(vacancy, face))
                .anyMatch(vacancy -> edgeRegions.stream()
                        .anyMatch(edge -> intersectsRegion(vacancy, face, edge[0], edge[1], edge[2], edge[3])));

        if (vacancyAffectsEdge) {
            return false;
        }

        // Check if any solid fully covers each edge region
        for (double[] edge : edgeRegions) {
            boolean edgeCovered = solids.stream()
                    .filter(solid -> isAlignedWithFace(solid, face))
                    .anyMatch(solid -> isRegionFullyCovered(solid, face, edge[0], edge[1], edge[2], edge[3]));

            if (!edgeCovered) {
                return false;
            }
        }

        return true;
    }

    /**
     * @see #intersectsRay(Vector3dc, Vector3dc)
     */
    public boolean intersectsRay(double sx, double sy, double sz, double ex, double ey, double ez) {
        return intersectsRay(new Vector3d(sx, sy, sz), new Vector3d(ex, ey, ez));
    }

    /**
     * Determine whether the given ray intersects this voxel shape.
     *
     * @param start the start point of the ray.
     * @param end   the end point of the ray.
     *
     * @return {@code true} if the ray intersects this voxel shape, otherwise {@code false}.
     */
    public boolean intersectsRay(Vector3dc start, Vector3dc end) {
        var ray = new Rayd(start, end.sub(start, new Vector3d()));

        if (vacancies.isEmpty()) {
            // This would be quicker if no vacancy exists
            return solids.stream().anyMatch(solid -> solid.intersectsRay(ray));
        }

        var solidIntervals = mergeIntervals(intersectsRay(solids, ray));
        var vacancyIntervals = mergeIntervals(intersectsRay(vacancies, ray));

        // For each solid intervals, check if they can be fully covered by vacancy intervals
        for (var solidInterval : solidIntervals) {
            double currentStart = solidInterval.x;
            boolean isCovered = false;

            for (var vacancyInterval : vacancyIntervals) {
                // Check if vacancy interval cover the starting point of the currently uncovered part of solid interval
                if (vacancyInterval.x <= currentStart && vacancyInterval.y >= currentStart) {
                    // Update the current starting point to the end point of the vacancy interval
                    currentStart = Math.max(currentStart, vacancyInterval.y);
                    // If the current starting point has exceeded the end point of the solid interval, it means it is completely covered.
                    if (currentStart >= solidInterval.y) {
                        isCovered = true;
                        break;
                    }
                }
            }

            if (!isCovered) {
                // Return false directly if any of the solid interval is not fully covered
                return true;
            }
        }

        return false;
    }

    private List<Vector2d> mergeIntervals(List<Vector2d> intervals) {
        // Sort by interval starting point
        intervals.sort((a, b) -> Double.compare(a.x, b.x));
        List<Vector2d> merged = new ArrayList<>();

        for (Vector2d interval : intervals) {
            // If merged is empty or the current interval does not overlap with the previous merged interval
            if (merged.isEmpty() || merged.getLast().y < interval.x) {
                // Add current interval
                merged.add(new Vector2d(interval));
            } else {
                // Merge interval
                merged.getLast().y = Math.max(merged.getLast().y, interval.y);
            }
        }

        return merged;
    }

    private List<Vector2d> intersectsRay(Set<AABBdc> aabbs, Rayd ray) {
        var set = new ArrayList<Vector2d>();
        for (var aabb : aabbs) {
            var result = new Vector2d();
            if (aabb.intersectsRay(ray, result)) {
                set.add(order(result));
            }
        }

        return set;
    }

    private Vector2d order(Vector2d vec) {
        if (vec.x > vec.y) {
            double temp = vec.x;
            vec.x = vec.y;
            vec.y = temp;
        }

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

    private boolean intersectsRegion(AABBdc aabb, BlockFace face, double minU, double maxU, double minV, double maxV) {
        double[] uvMinMax = getUVMinMax(aabb, face);
        return uvMinMax[0] < maxU && uvMinMax[1] > minU &&
               uvMinMax[2] < maxV && uvMinMax[3] > minV;
    }

    private boolean isRegionFullyCovered(AABBdc aabb, BlockFace face, double minU, double maxU, double minV, double maxV) {
        double[] uvMinMax = getUVMinMax(aabb, face);
        return uvMinMax[0] <= minU && uvMinMax[1] >= maxU &&
               uvMinMax[2] <= minV && uvMinMax[3] >= maxV;
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
        private final Set<AABBdc> vacancies = new HashSet<>();

        /**
         * Add a solid area to the voxel shape.
         *
         * @param solid the solid area to add.
         *
         * @return this builder.
         */
        public VoxelShapeBuilder solid(AABBdc solid) {
            solids.add(solid);
            return this;
        }

        /**
         * Add a solid area to the voxel shape.
         *
         * @param minX the minimum x coordinate of the solid area.
         * @param minY the minimum y coordinate of the solid area.
         * @param minZ the minimum z coordinate of the solid area.
         * @param maxX the maximum x coordinate of the solid area.
         * @param maxY the maximum y coordinate of the solid area.
         * @param maxZ the maximum z coordinate of the solid area.
         *
         * @return this builder.
         */
        public VoxelShapeBuilder solid(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return solid(new AABBd(minX, minY, minZ, maxX, maxY, maxZ));
        }

        /**
         * Add a vacancy area to the voxel shape.
         *
         * @param vacancy the vacancy area to add.
         *
         * @return this builder.
         */
        public VoxelShapeBuilder vacancy(AABBdc vacancy) {
            vacancies.add(vacancy);
            return this;
        }

        /**
         * Add a vacancy area to the voxel shape.
         *
         * @param minX the minimum x coordinate of the vacancy area.
         * @param minY the minimum y coordinate of the vacancy area.
         * @param minZ the minimum z coordinate of the vacancy area.
         * @param maxX the maximum x coordinate of the vacancy area.
         * @param maxY the maximum y coordinate of the vacancy area.
         * @param maxZ the maximum z coordinate of the vacancy area.
         *
         * @return this builder.
         */
        public VoxelShapeBuilder vacancy(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            return vacancy(new AABBd(minX, minY, minZ, maxX, maxY, maxZ));
        }

        /**
         * Build the voxel shape.
         *
         * @return the voxel shape.
         */
        public VoxelShape build() {
            return new VoxelShape(solids, vacancies);
        }
    }
}
