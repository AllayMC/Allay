package org.allaymc.server.entity.component.item;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityFishingHookBaseComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.projectile.EntityProjectilePhysicsComponentImpl;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * {@code EntityFishingHookPhysicsComponentImpl} resolves bobber movement and
 * hooked-target collision. A retained target suppresses independent motion
 * because the fishing component teleports the hook to that target. Water
 * movement uses the column surface to choose upward flotation or damped drift,
 * while other movement applies projectile gravity.
 *
 * <p>
 * The water surface is found by scanning upward through water-tagged blocks,
 * rather than using each liquid block fractional depth. The hook rises when
 * its location is below that surface by more than a small tolerance. Entity
 * impacts retain the first target and offer a zero-damage projectile attack to
 * living targets, leaving their attack pipeline to decide the visible
 * response.
 *
 * @author daoge_cmd
 */
public class EntityFishingHookPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * Injected composed fishing hook owner.
     */
    @ComponentObject
    protected EntityFishingHook thisEntity;

    /**
     * Injected hook target and fishing-state component.
     */
    @Dependency
    protected EntityFishingHookBaseComponent fishingHookBaseComponent;

    /**
     * Retains the first impacted entity as the hook target. A later impact
     * does nothing while a target is retained, and living targets receive a
     * zero-damage projectile attack through their ordinary attack handling.
     */
    @Override
    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
        if (fishingHookBaseComponent.hasHookedEntity()) {
            return;
        }

        fishingHookBaseComponent.setHookedEntity(entity);
        // Submit zero damage through the living attack path so that target handling
        // can decide whether a hit response occurs.
        if (entity instanceof EntityLiving living) {
            var damageContainer = DamageContainer.projectile(thisEntity, 0);
            living.attack(damageContainer);
        }
    }

    /**
     * Returns independent bobber motion or zero motion while a target is
     * retained. Water state selects surface-based flotation without consulting
     * the generic liquid-physics flag. A submerged bobber rises at a fixed
     * vertical speed while horizontal motion is strongly damped.
     *
     * <p>
     * Near the column surface, the update instead damps existing vertical
     * motion and retains more horizontal drift. Outside water, the update
     * applies projectile gravity and air drag. Every branch returns a new
     * vector; the engine proposes it after the movement phase through the
     * motion setter for a later physics tick.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        // Target-follow ticking teleports the hook, so independent projectile
        // motion would drift away from the retained target between updates.
        if (fishingHookBaseComponent.hasHookedEntity()) {
            return new Vector3d(0, 0, 0);
        }

        if (liquidState.inWater()) {
            var location = thisEntity.getLocation();
            double waterY = getWaterSurfaceY(location);
            if (location.y() < waterY - 0.1) {
                // A small surface tolerance prevents the upward branch from
                // demanding exact surface coordinate equality.
                return new Vector3d(
                        motion.x * 0.3,
                        0.15,
                        motion.z * 0.3
                );
            } else {
                // Damp vertical movement near the surface so the bobber settles
                // while retaining some horizontal drift.
                return new Vector3d(
                        motion.x * 0.9,
                        motion.y * 0.3,
                        motion.z * 0.9
                );
            }
        } else {
            return new Vector3d(
                    motion.x * (1 - getDragFactorInAir()),
                    (motion.y - getGravity()) * (1 - getDragFactorInAir()),
                    motion.z * (1 - getDragFactorInAir())
            );
        }
    }

    /**
     * Returns the top boundary of the water-tagged column above the floored
     * hook cell. The scan stops before the first nonwater block or at the
     * dimension maximum height and ignores fractional liquid depth.
     */
    protected double getWaterSurfaceY(Location3dc location) {
        var dimension = location.dimension();
        int x = (int) Math.floor(location.x());
        int z = (int) Math.floor(location.z());
        int y = (int) Math.floor(location.y());

        while (y < dimension.getDimensionType().getMaxHeight()) {
            var blockState = dimension.getBlockState(x, y + 1, z);
            if (!blockState.getBlockType().hasBlockTag(BlockTags.WATER)) {
                return y + 1;
            }
            y++;
        }
        return y;
    }
}
