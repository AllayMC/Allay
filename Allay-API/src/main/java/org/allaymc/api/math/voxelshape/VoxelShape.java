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
     * @return the translated voxel shape.
     */
    public VoxelShape translate(Vector3fc vec) {
        Set<AABBfc> newSolids = solids.stream()
                .map(solid -> solid.translate(vec, new AABBf()))
                .collect(Collectors.toSet());
        Set<AABBfc> newVacancies = vacancies.stream()
                .map(vacancy -> vacancy.translate(vec, new AABBf()))
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids, newVacancies);
    }

    /**
     * Check if this voxel shape intersects with the specified AABB.
     *
     * @param other the AABB to check.
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
     * @return {@code true} if the specified face of this voxel shape is full, otherwise {@code false}.
     */
    public boolean isFull(BlockFace face) {
        // 检查 vacancies 是否在面上造成任何空缺
        for (AABBfc vacancy : vacancies) {
            if (isAlignedWithFace(vacancy, face)) {
                float[] uvMinMax = getUVMinMax(vacancy, face);
                // 只要有任何空缺的存在，面就不完整
                if (uvMinMax[0] < 1.0f || uvMinMax[1] > 0.0f || uvMinMax[2] < 1.0f || uvMinMax[3] > 0.0f) {
                    return false;
                }
            }
        }

        float minU = Float.MAX_VALUE, maxU = -Float.MAX_VALUE;
        float minV = Float.MAX_VALUE, maxV = -Float.MAX_VALUE;

        // 计算 solids 覆盖的范围
        for (AABBfc solid : solids) {
            if (isAlignedWithFace(solid, face)) {
                float[] uvMinMax = getUVMinMax(solid, face);
                minU = Math.min(minU, uvMinMax[0]);
                maxU = Math.max(maxU, uvMinMax[1]);
                minV = Math.min(minV, uvMinMax[2]);
                maxV = Math.max(maxV, uvMinMax[3]);
            }
        }

        // 如果 solids 没有完全覆盖这个面，则返回 false
        // 面完全被 solids 覆盖且没有空缺，则返回 true
        return minU == 0.0f && maxU == 1.0f && minV == 0.0f && maxV == 1.0f;
    }

    /**
     * Check if the specified face of this voxel shape is center full.
     *
     * @param face the face to check.
     * @return {@code true} if the specified face of this voxel shape is center full, otherwise {@code false}.
     */
    public boolean isCenterFull(BlockFace face) {
        // 中心区域的边界，从 3/8 到 5/8
        float centerMinU = 0.375f;
        float centerMaxU = 0.625f;
        float centerMinV = 0.375f;
        float centerMaxV = 0.625f;

        // 先检查是否有 vacancy 影响中心区域
        boolean vacancyAffectsCenter = vacancies.stream()
                .filter(vacancy -> isAlignedWithFace(vacancy, face))
                .anyMatch(vacancy -> {
                    float[] uvMinMax = getUVMinMax(vacancy, face);
                    return uvMinMax[0] < centerMaxU && uvMinMax[1] > centerMinU
                           && uvMinMax[2] < centerMaxV && uvMinMax[3] > centerMinV;
                });

        if (vacancyAffectsCenter) {
            return false; // 如果 vacancy 影响中心区域，则返回 false
        }

        // 再检查 solid 是否完全覆盖了中心区域
        // 如果 solid 完全覆盖中心区域，则返回 true
        return solids.stream()
                .filter(solid -> isAlignedWithFace(solid, face))
                .anyMatch(solid -> {
                    float[] uvMinMax = getUVMinMax(solid, face);
                    return uvMinMax[0] <= centerMinU && uvMinMax[1] >= centerMaxU
                           && uvMinMax[2] <= centerMinV && uvMinMax[3] >= centerMaxV;
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
        for (var face : BlockFace.values()) {
            if (!isFull(face)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if the specified face of this voxel shape is edge full.
     *
     * @param face the face to check.
     * @return {@code true} if the specified face of this voxel shape is edge full, otherwise {@code false}.
     */
    public boolean isEdgeFull(BlockFace face) {
        // 定义边缘区域的宽度
        float edgeWidth = 0.125f;

        // 获取所有边缘区域的边界
        List<float[]> edgeRegions = getEdgeRegions(face, edgeWidth);

        // 检查是否有 vacancy 影响任何一个边缘区域
        boolean vacancyAffectsEdge = vacancies.stream()
                .filter(vacancy -> isAlignedWithFace(vacancy, face))
                .anyMatch(vacancy -> edgeRegions.stream()
                        .anyMatch(edge -> intersectsRegion(vacancy, face, edge[0], edge[1], edge[2], edge[3])));

        if (vacancyAffectsEdge) {
            return false;
        }

        // 检查是否有 solid 完全覆盖每个边缘区域
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

        // 定义四个边缘区域的模板
        float[][] templateEdges = {
                {0, edgeWidth, 0, 1},          // 第一条边
                {1 - edgeWidth, 1, 0, 1},      // 对应的对边
                {edgeWidth, 1 - edgeWidth, 0, edgeWidth},    // 第三条边
                {edgeWidth, 1 - edgeWidth, 1 - edgeWidth, 1} // 对应的对边
        };

        // 根据 BlockFace 所处的平面调整边缘坐标的顺序
        switch (face) {
            case UP, DOWN -> {
                // X-Z plane
                edgeRegions.addAll(Arrays.asList(templateEdges));
            }
            case NORTH, SOUTH -> {
                // X-Y plane, V和U之间交换
                for (float[] edge : templateEdges) {
                    edgeRegions.add(new float[]{edge[0], edge[1], edge[2], edge[3]});
                }
            }
            case WEST, EAST -> {
                // Y-Z plane, U和V之间交换
                for (float[] edge : templateEdges) {
                    edgeRegions.add(new float[]{edge[2], edge[3], edge[0], edge[1]});
                }
            }
        }

        return edgeRegions;
    }

    private boolean intersectsRegion(AABBfc aabb, BlockFace face, float minU, float maxU, float minV, float maxV) {
        float[] uvMinMax = getUVMinMax(aabb, face);
        return uvMinMax[0] < maxU && uvMinMax[1] > minU
               && uvMinMax[2] < maxV && uvMinMax[3] > minV;
    }

    private boolean isRegionFullyCovered(AABBfc aabb, BlockFace face, float minU, float maxU, float minV, float maxV) {
        float[] uvMinMax = getUVMinMax(aabb, face);
        return uvMinMax[0] <= minU && uvMinMax[1] >= maxU
               && uvMinMax[2] <= minV && uvMinMax[3] >= maxV;
    }

    private boolean isAlignedWithFace(AABBfc solid, BlockFace face) {
        return switch (face) {
            case UP -> solid.maxY() == 1.0f;
            case DOWN -> solid.minY() == 0.0f;
            case NORTH -> solid.minZ() == 0.0f;
            case SOUTH -> solid.maxZ() == 1.0f;
            case WEST -> solid.minX() == 0.0f;
            case EAST -> solid.maxX() == 1.0f;
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
         * @return this builder.
         */
        public VoxelShapeBuilder solid(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return solid(new AABBf(minX, minY, minZ, maxX, maxY, maxZ));
        }

        /**
         * Add a vacancy area to the voxel shape.
         *
         * @param vacancy the vacancy area to add.
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
