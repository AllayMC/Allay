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
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.joml.primitives.Rayd;

/**
 * @author daoge_cmd
 */
public class EntityProjectileBaseComponentImpl extends EntityBaseComponentImpl implements EntityProjectileBaseComponent {

    protected static final int MAX_AGE = 6000;

    @Getter
    @Setter
    protected Entity shooter;
    protected int age;

    public EntityProjectileBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);

        if (age != -1) {
            age++;
            if (age >= MAX_AGE) {
                despawn();
            }
        }
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
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
        }
        var rayCastResult = new RayCastResult();

        // Ray cast blocks
        dimension.forEachBlockStates(aabb, 0, (x, y, z, block) -> {
            var result = new Vector2d();
            if (block.getBlockStateData().computeOffsetCollisionShape(x, y, z).intersectsRay(ray, result)) {
                if (result.x() < rayCastResult.result) {
                    rayCastResult.result = result.x();
                    rayCastResult.hit = new BlockStateWithPos(block, new Position3i(x, y, z, dimension));
                }
            }
        });

        // Ray cast entities
        dimension.getEntityService().getPhysicsService().computeCollidingEntities(aabb).forEach(entity -> {
            if (entity == thisEntity || (age <= 10 && entity == shooter)) {
                return;
            }

            var result = new Vector2d();
            if (entity.getOffsetAABB().intersectsRay(ray, result)) {
                if (result.x() < rayCastResult.result) {
                    rayCastResult.result = result.x();
                    rayCastResult.hit = entity;
                }
            }
        });

        // Let's move as far as possible if there are blocks/entities in our way,
        if (rayCastResult.hit != null) {
            newPos = new Location3d(location);
            newPos.add(motion.mul(rayCastResult.result, new Vector3d()));
        }

        if (!newPos.equals(location) && trySetLocation(newPos)) {
            if (rayCastResult.hit instanceof BlockStateWithPos block && callHitEvent(newPos, null, block)) {
                block.getBehavior().onProjectileHit(block, (EntityProjectile) thisEntity, newPos);
                onHitBlock(block, newPos);
            } else if (rayCastResult.hit instanceof Entity entity && callHitEvent(newPos, entity, null)) {
                entity.onProjectileHit((EntityProjectile) thisEntity, newPos);
                onHitEntity(entity, newPos);
            }

            return true;
        }

        return false;
    }

    protected boolean callHitEvent(Vector3dc hitPos, Entity victim, BlockStateWithPos block) {
        var event = new ProjectileHitEvent((EntityProjectile) thisEntity, hitPos, victim, block);
        return event.call();
    }

    protected void onHitBlock(BlockStateWithPos block, Vector3dc hitPos) {
    }

    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
    }
}
