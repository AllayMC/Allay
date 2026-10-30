package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
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
 * {@code EntityProjectilePhysicsComponentImpl} moves projectiles by ray-based
 * collision selection and defers impact callbacks until the engine finishes
 * movement. A movement pass searches blocks and entities around the proposed
 * travel bounds, chooses the smallest ray entry parameter, and attempts to
 * place the projectile at that collision point. Ordinary entity pushing and
 * stuck-block escape are disabled for this path.
 *
 * <p>
 * Accepted hit events retain a target and copied position in {@code
 * pendingCollisionResult}. The engine updates its spatial tree after parallel
 * movement, then invokes deferred callbacks sequentially. Target callbacks
 * precede projectile-specific hooks, and pending state is cleared only after
 * those callbacks return normally. Cancellation suppresses queued impact
 * handling after positioning, while a thrown callback leaves the pending
 * result uncleared.
 *
 * @author daoge_cmd
 */
public class EntityProjectilePhysicsComponentImpl extends EntityPhysicsComponentImpl {

    /**
     * {@code CollisionResult} retains a selected collision target and its
     * deferred world-space hit position.
     *
     * @param hit retained block or entity whose concrete type selects the
     *     target callback in the deferred phase
     * @param hitPos retained world-space position; the record stores this
     *     reference directly without performing another defensive copy
     */
    protected record CollisionResult(Object hit, Vector3dc hitPos) {}

    /**
     * Injected composed projectile owner.
     */
    @ComponentObject
    protected Entity thisEntity;

    /**
     * {@code pendingCollisionResult} retains an accepted hit between {@code
     * applyMotion} and {@code afterApplyMotion}. Callback completion clears
     * it, while an exception before cleanup leaves the same result installed.
     */
    protected CollisionResult pendingCollisionResult;

    /**
     * Injected projectile age counter.
     */
    @Dependency
    protected EntityAgeComponent ageComponent;
    /**
     * Injected retained shooter reference component.
     */
    @Dependency
    protected EntityProjectileComponent projectileComponent;

    /**
     * Returns {@code false} to suppress ordinary entity pushing for
     * projectiles.
     */
    @Override
    public boolean computeEntityCollisionMotion() {
        return false;
    }

    /**
     * Returns {@code false} to suppress ordinary stuck-block escape motion for
     * projectiles.
     */
    @Override
    public boolean computeBlockCollisionMotion() {
        return false;
    }

    /**
     * Returns default projectile downward acceleration in blocks per tick
     * squared.
     */
    @Override
    public double getGravity() {
        return 0.03;
    }

    /**
     * Returns the fraction of projectile motion removed by air drag.
     */
    @Override
    public double getDragFactorInAir() {
        return 0.01;
    }

    /**
     * Returns zero upward buoyancy contribution for projectiles moving through
     * water.
     */
    @Override
    public double getWaterBuoyancy() {
        return 0;
    }

    /**
     * Returns the fraction of projectile motion removed by water drag.
     */
    @Override
    public double getWaterDragFactor() {
        return 0.2;
    }

    /**
     * Returns zero upward buoyancy contribution for projectiles moving through
     * lava.
     */
    @Override
    public double getLavaBuoyancy() {
        return 0;
    }

    /**
     * Returns the fraction of projectile motion removed by lava drag.
     */
    @Override
    public double getLavaDragFactor() {
        return 0.2;
    }

    /**
     * Returns a new projectile motion vector after liquid or air acceleration.
     * Liquid handling delegates when enabled; otherwise gravity and air drag
     * apply directly, leaving assignment to the engine motion setter.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (liquidState.inLiquid() && computeLiquidPhysics()) {
            return updateMotionInLiquid(liquidState);
        }

        return new Vector3d(
                this.motion.x * (1 - this.getDragFactorInAir()),
                (this.motion.y - this.getGravity()) * (1 - this.getDragFactorInAir()),
                this.motion.z * (1 - this.getDragFactorInAir())
        );
    }

    /**
     * Attempts projectile travel and retains a permitted impact for the
     * deferred callback phase. Zero motion returns {@code false}. Block and
     * entity searches share travel bounds, and the smallest ray entry
     * parameter wins without an explicit segment-parameter clamp. Shooter
     * exclusion applies through age ten, and phantom players are skipped.
     *
     * <p>
     * The method updates rotation only for nonzero displacement and queues a
     * hit only after the location change succeeds. Hit-event cancellation
     * leaves the accepted position and pending grounded flag but suppresses
     * the target callback result. A successful location change returns {@code
     * true} even without an impact, allowing the engine to update the spatial
     * tree before callbacks.
     */
    @Override
    public boolean applyMotion() {
        if (motion.lengthSquared() == 0) {
            return false;
        }

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

        /**
         * {@code RayCastResult} accumulates the nearest target across block
         * and entity ray searches.
         */
        final class RayCastResult {
            // hit and result describe one selected collision. Strictly smaller
            // entry parameters replace both values, preserving the earlier
            // selection when two targets tie.
            /** Nearest selected collision target. */
            Object hit = null;
            /** Smallest observed ray entry parameter. */
            double result = Double.MAX_VALUE;
        }
        var rayCastResult = new RayCastResult();

        // Include the cell below the travel volume because tall block shapes can
        // extend into the projectile path from that lower origin.
        var blockSearchAABB = new AABBd(aabb);
        blockSearchAABB.minY -= 1;
        dimension.forEachBlockStates(blockSearchAABB, 0, (x, y, z, block) -> {
            var result = new Vector2d();
            if (block.getBlockStateData().computeOffsetCollisionShape(x, y, z).intersectsRay(ray, result)) {
                if (result.x() < rayCastResult.result) {
                    rayCastResult.result = result.x();
                    rayCastResult.hit = new Block(block, new Position3i(x, y, z, dimension));
                }
            }
        });

        dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb).forEach(entity -> {
            // Exclude the shooter during the first ten age ticks so a newly
            // launched projectile can leave its launch bounds.
            if (entity == thisEntity || (ageComponent.getAge() <= 10 && entity == projectileComponent.getShooter())) {
                return;
            }

            if (entity instanceof EntityPlayer player && player.isPhantom()) {
                return;
            }

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

        if (rayCastResult.hit != null) {
            newPos = new Location3d(location);
            newPos.add(motion.mul(rayCastResult.result, new Vector3d()));
        }

        if (newPos.distance(location) > 0) {
            computeRotationFromMotion(newPos, this.motion);
        }

        if (!newPos.equals(location) && thisEntity.trySetLocation(newPos)) {
            this.pendingOnGround = rayCastResult.hit instanceof Block;

            // Publish only accepted hits for the deferred phase, after positioning
            // succeeds, so impact callbacks run after the engine updates spatial
            // bounds.
            if (rayCastResult.hit instanceof Block block && callHitEvent(newPos, null, block)) {
                this.pendingCollisionResult = new CollisionResult(block, new Vector3d(newPos.x(), newPos.y(), newPos.z()));
            } else if (rayCastResult.hit instanceof Entity entity && callHitEvent(newPos, entity, null)) {
                this.pendingCollisionResult = new CollisionResult(entity, new Vector3d(newPos.x(), newPos.y(), newPos.z()));
            }

            return true;
        }

        return false;
    }

    /**
     * Writes projectile yaw and pitch derived from the supplied motion vector.
     */
    protected void computeRotationFromMotion(Location3d pos, Vector3dc motion) {
        pos.setYaw(-MathUtils.getYawFromVector(motion));
        pos.setPitch(-MathUtils.getPitchFromVector(motion));
    }

    /**
     * Dispatches a projectile hit event and returns whether impact processing
     * is permitted. The movement caller has already accepted the new position,
     * so cancellation suppresses deferred callbacks rather than reversing
     * travel.
     */
    protected boolean callHitEvent(Vector3dc hitPos, Entity victim, Block block) {
        var event = new ProjectileHitEvent((EntityProjectile) thisEntity, hitPos, victim, block);
        return event.call();
    }

    /**
     * Completes inherited post-motion handling before delivering a retained
     * collision result. The engine invokes this phase after its spatial-tree
     * updates, so target callbacks observe the completed movement phase rather
     * than a partially updated tree. A missing result requires no impact work.
     *
     * <p>
     * For blocks, the block behavior callback precedes {@code onHitBlock}; for
     * entities, the target callback precedes {@code onHitEntity}. Pending
     * state is cleared after the selected callback sequence returns. An
     * exception in inherited or impact handling prevents cleanup, so this
     * method does not promise exactly-once delivery after failure.
     */
    @Override
    public void afterApplyMotion() {
        super.afterApplyMotion();

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

        pendingCollisionResult = null;
    }

    /**
     * Provides the default empty projectile-specific hook for a permitted
     * block impact.
     */
    protected void onHitBlock(Block block, Vector3dc hitPos) {
    }

    /**
     * Provides the default empty projectile-specific hook for a permitted
     * entity impact.
     */
    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
    }

    /**
     * Returns whether another projectile has the same nonnull shooter
     * reference. A missing shooter does not group unrelated projectiles
     * together, and subclasses can add exclusions after retaining this shared
     * rule.
     */
    protected boolean shouldSkipEntityCollision(Entity entity) {
        if (entity instanceof EntityProjectile otherProjectile) {
            var shooter = projectileComponent.getShooter();
            return shooter != null && shooter == otherProjectile.getShooter();
        }

        return false;
    }
}
