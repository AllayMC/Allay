package cn.allay.api.math.voxelshape;

import cn.allay.api.block.data.BlockFace;
import lombok.Getter;
import org.joml.Vector3fc;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import java.util.HashSet;
import java.util.Set;

/**
 * Allay Project 2023/8/26
 *
 * @author daoge_cmd
 */
@Getter
public final class VoxelShape {
    private final Set<AABBfc> vacancies;
    private final Set<AABBfc> solids;

    private VoxelShape(Set<AABBfc> solids, Set<AABBfc> vacancies) {
        this.solids = solids;
        this.vacancies = vacancies;
    }

    public static VoxelShapeBuilder builder() {
        return new VoxelShapeBuilder();
    }

    public AABBf unionAABB() {
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE, minZ = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE, maxZ = Float.MIN_VALUE;
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
        var newVacancies = new HashSet<AABBfc>();
        var newSolids = new HashSet<AABBfc>();
        vacancies.forEach(vacancy -> newVacancies.add(face.rotateAABB(vacancy)));
        solids.forEach(solid -> newSolids.add(face.rotateAABB(solid)));
        return new VoxelShape(newSolids, newVacancies);
    }

    public VoxelShape translate(float x, float y, float z) {
        var newVacancies = new HashSet<AABBfc>();
        var newSolids = new HashSet<AABBfc>();
        vacancies.forEach(vacancy -> newVacancies.add(vacancy.translate(x, y, z, new AABBf())));
        solids.forEach(solid -> newSolids.add(solid.translate(x, y, z, new AABBf())));
        return new VoxelShape(newSolids, newVacancies);
    }

    public VoxelShape translate(Vector3fc vec) {
        var newVacancies = new HashSet<AABBfc>();
        var newSolids = new HashSet<AABBfc>();
        vacancies.forEach(vacancy -> newVacancies.add(vacancy.translate(vec, new AABBf())));
        solids.forEach(solid -> newSolids.add(solid.translate(vec, new AABBf())));
        return new VoxelShape(newSolids, newVacancies);
    }

    public boolean intersectsAABB(AABBfc other) {
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsAABB(other))) {
            return false;
        }
        return solids.stream().anyMatch(solid -> solid.intersectsAABB(other));
    }

    public boolean intersectsPoint(Vector3fc vec) {
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsPoint(vec))) {
            return false;
        }
        return solids.stream().anyMatch(solid -> solid.containsPoint(vec));
    }

    public boolean intersectsPoint(float x, float y, float z) {
        if (vacancies.stream().anyMatch(vacancy -> vacancy.containsPoint(x, y, z))) {
            return false;
        }
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
