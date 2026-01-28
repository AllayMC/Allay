package org.allaymc.server.entity.component;

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
 * Physics component for fishing hook entity.
 * Handles water floating and collision detection.
 *
 * @author daoge_cmd
 */
public class EntityFishingHookPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    @ComponentObject
    protected EntityFishingHook thisEntity;

    @Dependency
    protected EntityFishingHookBaseComponent fishingHookBaseComponent;

    @Override
    public double getGravity() {
        return 0.04;
    }

    @Override
    protected void onHitEntity(Entity entity, Vector3dc hitPos) {
        if (fishingHookBaseComponent.hasHookedEntity()) {
            return;
        }

        // Hook the entity
        fishingHookBaseComponent.setHookedEntity(entity);
        // Deal damage to trigger hurt animation and knockback
        if (entity instanceof EntityLiving living) {
            var damageContainer = DamageContainer.projectile(thisEntity, 0);
            living.attack(damageContainer);
        }
    }

    @Override
    public Vector3d updateMotion(boolean hasLiquidMotion) {
        // If hooked an entity, the hook will be teleported to the entity every tick,
        // so we do not need to update the motion
        if (fishingHookBaseComponent.hasHookedEntity()) {
            return new Vector3d(0, 0, 0);
        }

        if (thisEntity.isTouchingWater()) {
            // Water physics: float and slow down
            var location = thisEntity.getLocation();
            double waterY = getWaterSurfaceY(location);
            if (location.y() < waterY - 0.1) {
                // Below water surface: float up
                return new Vector3d(
                        motion.x * 0.3,
                        0.15,
                        motion.z * 0.3
                );
            } else {
                // At water surface: stay still with minor drift
                return new Vector3d(
                        motion.x * 0.9,
                        motion.y * 0.3,
                        motion.z * 0.9
                );
            }
        } else {
            // Air physics: normal gravity
            return new Vector3d(
                    motion.x * (1 - getDragFactorInAir()),
                    (motion.y - getGravity()) * (1 - getDragFactorInAir()),
                    motion.z * (1 - getDragFactorInAir())
            );
        }
    }

    protected double getWaterSurfaceY(Location3dc location) {
        var dimension = location.dimension();
        int x = (int) Math.floor(location.x());
        int z = (int) Math.floor(location.z());
        int y = (int) Math.floor(location.y());

        // Find the top of the water column
        while (y < dimension.getDimensionInfo().maxHeight()) {
            var blockState = dimension.getBlockState(x, y + 1, z);
            if (!blockState.getBlockType().hasBlockTag(BlockTags.WATER)) {
                return y + 1;
            }
            y++;
        }
        return y;
    }
}
