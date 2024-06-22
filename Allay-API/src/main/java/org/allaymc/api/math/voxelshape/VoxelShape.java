package org.allaymc.api.math.voxelshape;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Allay Project 2023/8/26
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

    public VoxelShape rotate(BlockFace face) {
        Set<AABBfc> newSolids = solids.stream()
                .map(face::rotateAABB)
                .collect(Collectors.toSet());
        Set<AABBfc> newVacancies = vacancies.stream()
                .map(face::rotateAABB)
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids, newVacancies);
    }

    public VoxelShape translate(float x, float y, float z) {
        Set<AABBfc> newSolids = solids.stream()
                .map(solid -> solid.translate(x, y, z, new AABBf()))
                .collect(Collectors.toSet());
        Set<AABBfc> newVacancies = vacancies.stream()
                .map(vacancy -> vacancy.translate(x, y, z, new AABBf()))
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids, newVacancies);
    }

    public VoxelShape translate(Vector3fc vec) {
        Set<AABBfc> newSolids = solids.stream()
                .map(solid -> solid.translate(vec, new AABBf()))
                .collect(Collectors.toSet());
        Set<AABBfc> newVacancies = vacancies.stream()
                .map(vacancy -> vacancy.translate(vec, new AABBf()))
                .collect(Collectors.toSet());
        return new VoxelShape(newSolids, newVacancies);
    }

    public boolean intersectsAABB(AABBfc other) {
        var aabb = unionAABB();
        if (!aabb.intersectsAABB(other)) return false;

        other.intersection(aabb, aabb);
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsAABB(aabb))) return false;
        return solids.stream().anyMatch(solid -> solid.intersectsAABB(aabb));
    }

    public boolean intersectsPoint(Vector3fc vec) {
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsPoint(vec))) return false;
        return solids.stream().anyMatch(solid -> solid.containsPoint(vec));
    }

    public boolean intersectsPoint(float x, float y, float z) {
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsPoint(x, y, z))) return false;
        return solids.stream().anyMatch(solid -> solid.containsPoint(x, y, z));
    }

    public static class VoxelShapeBuilder {
        private final Set<AABBfc> solids = new HashSet<>();
        private final Set<AABBfc> vacancies = new HashSet<>();

        public VoxelShapeBuilder solid(AABBfc solid) {
            solids.add(solid);
            return this;
        }

        public VoxelShapeBuilder solid(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return solid(new AABBf(minX, minY, minZ, maxX, maxY, maxZ));
        }

        public VoxelShapeBuilder vacancy(AABBfc vacancy) {
            vacancies.add(vacancy);
            return this;
        }

        public VoxelShapeBuilder vacancy(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
            return vacancy(new AABBf(minX, minY, minZ, maxX, maxY, maxZ));
        }

        public VoxelShape build() {
            return new VoxelShape(solids, vacancies);
        }
    }
}
