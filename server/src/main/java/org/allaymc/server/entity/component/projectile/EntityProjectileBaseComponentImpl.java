package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityProjectileBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
public class EntityProjectileBaseComponentImpl extends EntityBaseComponentImpl implements EntityProjectileBaseComponent {

    @Getter
    @Setter
    protected Entity shootingEntity;

    public EntityProjectileBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }

    @Override
    public double getStepHeight() {
        return 0;
    }

    @Override
    public boolean computeEntityCollisionMotion() {
        return false;
    }

    @Override
    public boolean computeBlockCollisionMotion() {
        return false;
    }

    @Override
    public Vector3d updateMotion(boolean hasLiquidMotion) {
        return new Vector3d(
                this.motion.x * (1 - this.getDragFactorInAir()),
                this.motion.y - this.getGravity(),
                this.motion.z * (1 - this.getDragFactorInAir())
        );
    }

    @Override
    public boolean applyMotion() {
        // The position we expected to get to if no blocks/entities prevent us
        var newPos = new Location3d(location);
        newPos.add(motion);
        var aabb = new AABBd(
                Math.min(location.x, newPos.x),
                Math.min(location.y, newPos.y),
                Math.min(location.z, newPos.z),
                Math.max(location.x, newPos.x),
                Math.max(location.y, newPos.y),
                Math.max(location.z, newPos.z)
        );
        var builder = VoxelShape.builder();
        getDimension().forEachBlockStates(aabb, 0, (x, y, z, block) ->
                block.getBlockStateData().computeOffsetCollisionShape(x, y, z).getSolids().forEach(builder::solid));
        getDimension().getEntityService().getPhysicsService().computeCollidingEntities(aabb).forEach(entity -> {
            if (entity != thisEntity && entity != shootingEntity) {
                builder.solid(entity.getOffsetAABB());
            }
        });
        var shape = builder.build();
        var result = new Vector2d();
        if (shape.intersectsRay(location, newPos.sub(location, new Vector3d()), result)) {
            // There are blocks/entities in our way, let's move as far as possible
            newPos = new Location3d(location);
            newPos.add(motion.mul(Math.clamp(result.x, 0, 1), new Vector3d()));
        }

        return !newPos.equals(location) && trySetLocation(newPos);
    }
}
