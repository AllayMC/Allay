package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityProjectile;
import org.allaymc.api.eventbus.event.entity.ProjectileHitEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;
import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;
import org.joml.primitives.Rayd;

/**
 * @author daoge_cmd
 */
public class EntityProjectilePhysicsComponentImpl extends EntityPhysicsComponentImpl {

    /**
     * Stores the result of a ray-cast collision.
     *
     * @param hit    the object that was hit (Block or Entity)
     * @param hitPos the position where the collision occurred
     */
    protected record CollisionResult(Object hit, Vector3dc hitPos) {}

    @ComponentObject
    protected Entity thisEntity;

    /**
     * Collision result storage (set in applyMotion, consumed in afterApplyMotion).
     */
    protected CollisionResult pendingCollisionResult;

    @Dependency
    protected EntityAgeComponent ageComponent;
    @Dependency
    protected EntityProjectileComponent projectileComponent;

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
                (this.motion.y - this.getGravity()) * (1 - this.getDragFactorInAir()),
                this.motion.z * (1 - this.getDragFactorInAir())
        );
    }

    @Override
    public boolean applyMotion() {
        if (motion.lengthSquared() == 0) {
            return false;
        }

        // The position we expected to get to if no blocks/entities prevent us
        var location = thisEntity.getLocation();
        var newPos = new Location3d(location);
        newPos.add(motion);
        var aabb = new AABBd(
                Math.min(location.x(), newPos.x),
                Math.min(location.y(), newPos.y),
                Math.min(location.z(), newPos.z),
                Math.max(location.x(), newPos.x),
                Math.max(location.y(), newPos.y),
                Math.max(location.z(), newPos.z)
        );
        var dimension = thisEntity.getDimension();
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
                    rayCastResult.hit = new Block(block, new Position3i(x, y, z, dimension));
                }
            }
        });

        // Ray cast entities
        dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb).forEach(entity -> {
            if (entity == thisEntity || (ageComponent.getAge() <= 10 && entity == projectileComponent.getShooter())) {
                return;
            }

            // Allow subclasses to skip specific entities (e.g., pierced entities for arrows)
            if (shouldSkipEntityCollision(entity)) {
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

        if (newPos.distance(location) > 0) {
            // Compute rotation based on the motion only when the projectile moved
            computeRotationFromMotion(newPos, this.motion);
        }

        if (!newPos.equals(location) && thisEntity.trySetLocation(newPos)) {
            if (rayCastResult.hit instanceof Block block && callHitEvent(newPos, null, block)) {
                // Store for later processing in afterApplyMotion()
                this.pendingCollisionResult = new CollisionResult(block, new Vector3d(newPos.x(), newPos.y(), newPos.z()));
            } else if (rayCastResult.hit instanceof Entity entity && callHitEvent(newPos, entity, null)) {
                // Store for later processing in afterApplyMotion()
                this.pendingCollisionResult = new CollisionResult(entity, new Vector3d(newPos.x(), newPos.y(), newPos.z()));
            }

            return true;
        }

        return false;
    }

    protected void computeRotationFromMotion(Location3d pos, Vector3dc motion) {
        pos.setYaw(-MathUtils.getYawFromVector(motion));
        pos.setPitch(-MathUtils.getPitchFromVector(motion));
    }

    protected boolean callHitEvent(Vector3dc hitPos, Entity victim, Block block) {
        var event = new ProjectileHitEvent((EntityProjectile) thisEntity, hitPos, victim, block);
        return event.call();
    }

    @Override
    public void afterApplyMotion() {
        if (pendingCollisionResult == null) {
            return;
        }

        var hit = pendingCollisionResult.hit();
        var hitPos = pendingCollisionResult.hitPos();

        if (hit instanceof Block block) {
            block.getBehavior().onProjectileHit(block, (EntityProjectile) thisEntity, hitPos);
            onHitBlock(block, hitPos);
        } else if (hit instanceof Entity entity) {
            entity.onProjectileHit((EntityProjectile) thisEntity, hitPos);
            onHitEntity(entity, hitPos);
        }

        // Clear pending state
        pendingCollisionResult = null;
    }

    protected void onHitBlock(Block block, Vector3dc hitPos) {
    }

    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
    }

    /**
     * Check if collision with the given entity should be skipped.
     * Override this method to skip collisions with specific entities (e.g., pierced entities for arrows).
     *
     * @param entity the entity to check
     * @return {@code true} if collision should be skipped, {@code false} otherwise
     */
    protected boolean shouldSkipEntityCollision(Entity entity) {
        // Skip collisions with other projectiles from the same shooter (e.g., Multishot arrows)
        if (entity instanceof EntityProjectile otherProjectile) {
            var shooter = projectileComponent.getShooter();
            return shooter != null && shooter == otherProjectile.getShooter();
        }

        return false;
    }
}
