package org.allaymc.api.math.voxelshape;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

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

    private final Set<AABBfc> solids;
    private final Set<AABBfc> vacancies;

    public static VoxelShapeBuilder builder() {
        return new VoxelShapeBuilder();
    }

    /**
     * Calculate the minimum AABB that can contain this voxel shape.
     *
     * @return the minimum AABB that can contain this voxel shape.
     */
    public AABBf unionAABB() {
        float
                minX = Float.MAX_VALUE,
                minY = Float.MAX_VALUE,
                minZ = Float.MAX_VALUE;
        float
                maxX = -Float.MAX_VALUE,
                maxY = -Float.MAX_VALUE,
                maxZ = -Float.MAX_VALUE;
        for (var solid : solids) {
            if (solid.minX() < minX) minX = solid.minX();
            if (solid.minY() < minY) minY = solid.minY();
            if (solid.minZ() < minZ) minZ = solid.minZ();
            if (solid.maxX() > maxX) maxX = solid.maxX();
            if (solid.maxY() > maxY) maxY = solid.maxY();
            if (solid.maxZ() > maxZ) maxZ = solid.maxZ();
        }
        return new AABBf(
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
        Set<AABBfc> newSolids = solids.stream()
                .map(face::rotateAABB)
                .collect(Collectors.toSet());
        Set<AABBfc> newVacancies = vacancies.stream()
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
    public VoxelShape translate(float x, float y, float z) {
        Set<AABBfc> newSolids = solids.stream()
                .map(solid -> solid.translate(x, y, z, new AABBf()))
                .collect(Collectors.toSet());
        Set<AABBfc> newVacancies = vacancies.stream()
                .map(vacancy -> vacancy.translate(x, y, z, new AABBf()))
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
    public VoxelShape translate(Vector3fc vec) {
        // This method is frequently called in physics calculation,
        // So performance in mind

        // Set the size of the set as we know that how many entries
        // will be put into this set to speed up.
        var newSolids = new HashSet<AABBfc>(solids.size());
        // Simply use for-each instead of stream to get better performance
        for (var solid : solids) {
            newSolids.add(solid.translate(vec, new AABBf()));
        }
        var newVacancies = new HashSet<AABBfc>(vacancies.size());
        for (var vacancy : vacancies) {
            newVacancies.add(vacancy.translate(vec, new AABBf()));
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
    public boolean intersectsAABB(AABBfc other) {
        var aabb = unionAABB();
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
    public boolean intersectsPoint(Vector3fc vec) {
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
    public boolean intersectsPoint(float x, float y, float z) {
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
        for (AABBfc vacancy : vacancies) {
            if (isAlignedWithFace(vacancy, face)) {
                float[] uvMinMax = getUVMinMax(vacancy, face);
                // As long as there is any gap, the face is incomplete
                if (uvMinMax[0] < 1f || uvMinMax[1] > 0f || uvMinMax[2] < 1f || uvMinMax[3] > 0f) {
                    return false;
                }
            }
        }

        float minU = Float.MAX_VALUE, maxU = -Float.MAX_VALUE;
        float minV = Float.MAX_VALUE, maxV = -Float.MAX_VALUE;

        // Calculate the range covered by solids
        for (AABBfc solid : solids) {
            if (isAlignedWithFace(solid, face)) {
                float[] uvMinMax = getUVMinMax(solid, face);
                minU = Math.min(minU, uvMinMax[0]);
                maxU = Math.max(maxU, uvMinMax[1]);
                minV = Math.min(minV, uvMinMax[2]);
                maxV = Math.max(maxV, uvMinMax[3]);
            }
        }

        // If the solids do not completely cover the face, return false
        // If the face is fully covered by solids with no gaps, return true
        return minU == 0f && maxU == 1f && minV == 0f && maxV == 1f;
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
        float centerMinUV = (float) 3 / 8;
        float centerMaxUV = (float) 5 / 8;

        // First, check if any vacancy affects the center region
        boolean vacancyAffectsCenter = vacancies.stream()
                .filter(vacancy -> isAlignedWithFace(vacancy, face))
                .anyMatch(vacancy -> {
                    float[] uvMinMax = getUVMinMax(vacancy, face);
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
                    float[] uvMinMax = getUVMinMax(solid, face);
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
        float edgeWidth = 0.125f;

        // Get the boundaries of all edge regions
        List<float[]> edgeRegions = getEdgeRegions(face, edgeWidth);

        // Check if any vacancy affects any edge region
        boolean vacancyAffectsEdge = vacancies.stream()
                .filter(vacancy -> isAlignedWithFace(vacancy, face))
                .anyMatch(vacancy -> edgeRegions.stream()
                        .anyMatch(edge -> intersectsRegion(vacancy, face, edge[0], edge[1], edge[2], edge[3])));

        if (vacancyAffectsEdge) {
            return false;
        }

        // Check if any solid fully covers each edge region
        for (float[] edge : edgeRegions) {
            boolean edgeCovered = solids.stream()
                    .filter(solid -> isAlignedWithFace(solid, face))
                    .anyMatch(solid -> isRegionFullyCovered(solid, face, edge[0], edge[1], edge[2], edge[3]));

            if (!edgeCovered) {
                return false;
            }
        }

        return true;
    }

    private List<float[]> getEdgeRegions(BlockFace face, float edgeWidth) {
        List<float[]> edgeRegions = new ArrayList<>();

        // Define templates for the four edge regions
        float[][] templateEdges = {
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
                for (float[] edge : templateEdges) {
                    edgeRegions.add(new float[]{edge[0], edge[1], edge[2], edge[3]});
                }
            }
            case WEST, EAST -> {
                // Y-Z plane, swap U and V
                for (float[] edge : templateEdges) {
                    edgeRegions.add(new float[]{edge[2], edge[3], edge[0], edge[1]});
                }
            }
        }

        return edgeRegions;
    }

    private boolean intersectsRegion(AABBfc aabb, BlockFace face, float minU, float maxU, float minV, float maxV) {
        float[] uvMinMax = getUVMinMax(aabb, face);
        return uvMinMax[0] < maxU && uvMinMax[1] > minU &&
               uvMinMax[2] < maxV && uvMinMax[3] > minV;
    }

    private boolean isRegionFullyCovered(AABBfc aabb, BlockFace face, float minU, float maxU, float minV, float maxV) {
        float[] uvMinMax = getUVMinMax(aabb, face);
        return uvMinMax[0] <= minU && uvMinMax[1] >= maxU &&
               uvMinMax[2] <= minV && uvMinMax[3] >= maxV;
    }

    private boolean isAlignedWithFace(AABBfc solid, BlockFace face) {
        return switch (face) {
            case UP -> solid.maxY() == 1f;
            case DOWN -> solid.minY() == 0f;
            case NORTH -> solid.minZ() == 0f;
            case SOUTH -> solid.maxZ() == 1f;
            case WEST -> solid.minX() == 0f;
            case EAST -> solid.maxX() == 1f;
        };
    }

    private float[] getUVMinMax(AABBfc solid, BlockFace face) {
        return switch (face) {
            case UP, DOWN -> new float[]{solid.minX(), solid.maxX(), solid.minZ(), solid.maxZ()};
            case NORTH, SOUTH -> new float[]{solid.minX(), solid.maxX(), solid.minY(), solid.maxY()};
            case WEST, EAST -> new float[]{solid.minZ(), solid.maxZ(), solid.minY(), solid.maxY()};
        };
    }

    public static class VoxelShapeBuilder {
        private final Set<AABBfc> solids = new HashSet<>();
        private final Set<AABBfc> vacancies = new HashSet<>();

        /**
         * Add a solid area to the voxel shape.
         *
         * @param solid the solid area to add.
         *
         * @return this builder.
         */
        public VoxelShapeBuilder solid(AABBfc solid) {
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
        public VoxelShapeBuilder solid(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return solid(new AABBf(minX, minY, minZ, maxX, maxY, maxZ));
        }

        /**
         * Add a vacancy area to the voxel shape.
         *
         * @param vacancy the vacancy area to add.
         *
         * @return this builder.
         */
        public VoxelShapeBuilder vacancy(AABBfc vacancy) {
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
        public VoxelShapeBuilder vacancy(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return vacancy(new AABBf(minX, minY, minZ, maxX, maxY, maxZ));
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
