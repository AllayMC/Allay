package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityProjectileBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.entity.ProjectileHitEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.joml.primitives.Rayd;

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
    public double getGravity() {
        return 0.03;
    }

    @Override
    public double getDragFactorInAir() {
        return 0.01;
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
        var dimension = getDimension();
        var ray = new Rayd(location, newPos.sub(location, new Vector3d()));

        // Local class that stores used values during ray casting
        final class RayCastResult {
            Object hit = null;
            double result = Double.MAX_VALUE;
            boolean hitBlock = false;
        }
        var rayCastResult = new RayCastResult();

        // Ray cast blocks
        dimension.forEachBlockStates(aabb, 0, (x, y, z, block) -> {
            var result = new Vector2d();
            if (block.getBlockStateData().computeOffsetCollisionShape(x, y, z).intersectsRay(ray, result)) {
                if (result.x() < rayCastResult.result) {
                    rayCastResult.result = result.x();
                    rayCastResult.hit = new BlockStateWithPos(block, new Position3i(x, y, z, dimension));
                    rayCastResult.hitBlock = true;
                }
            }
        });

        // Ray cast entities
        dimension.getEntityService().getPhysicsService().computeCollidingEntities(aabb).forEach(entity -> {
            if (entity == thisEntity || entity == shootingEntity) {
                return;
            }

            var result = new Vector2d();
            if (entity.getOffsetAABB().intersectsRay(ray, result)) {
                if (result.x() < rayCastResult.result) {
                    rayCastResult.result = result.x();
                    rayCastResult.hit = entity;
                    rayCastResult.hitBlock = false;
                }
            }
        });

        // Let's move as far as possible if there are blocks/entities in our way,
        if (rayCastResult.hit != null) {
            newPos = new Location3d(location);
            newPos.add(motion.mul(rayCastResult.result, new Vector3d()));
        }

        if (!newPos.equals(location) && trySetLocation(newPos)) {
            if (rayCastResult.hitBlock && callHitEvent(null, (BlockStateWithPos) rayCastResult.hit)) {
                this.onHitBlock((BlockStateWithPos) rayCastResult.hit);
            } else if (callHitEvent((Entity) rayCastResult.hit, null)) {
                this.onHitEntity((Entity) rayCastResult.hit);
            }

            return true;
        }

        return false;
    }

    protected boolean callHitEvent(Entity victim, BlockStateWithPos block) {
        var event = new ProjectileHitEvent((EntityProjectile) thisEntity, victim, block);
        return event.call();
    }

    protected void onHitBlock(BlockStateWithPos block) {
    }

    protected void onHitEntity(Entity entity) {
    }
}
