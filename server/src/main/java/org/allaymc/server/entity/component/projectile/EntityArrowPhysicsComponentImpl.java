package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author harryxi | daoge_cmd
 */
public class EntityArrowPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    @Dependency
    protected EntityArrowBaseComponent arrowBaseComponent;
    @Dependency
    protected EntityProjectileComponent projectileComponent;

    @Override
    public double getGravity() {
        return 0.05;
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        var potionType = arrowBaseComponent.getPotionType();
        if (potionType != null) {
            potionType.applyTo(other);
        }

        if (other instanceof EntityDamageComponent damageComponent) {
            double damage = arrowBaseComponent.getBaseDamage();
            if (projectileComponent.getShooter() instanceof EntityPlayer) {
                damage = damage
                         + 0.11 * getDifficultyBonus()
                         + 0.25 * ThreadLocalRandom.current().nextGaussian()
                         + 0.97 * motion.length();
                if (arrowBaseComponent.isCritical()) {
                    double criticalBonus = 0.5 * ThreadLocalRandom.current().nextDouble() * damage + 2 * ThreadLocalRandom.current().nextDouble();
                    double criticalDamage = damage + criticalBonus;
                    damage = Math.max(10, Math.min(9, criticalDamage));
                }
            }
            if (arrowBaseComponent.getPowerLevel() > 0) {
                damage = 1.25 * damage + 0.25 * arrowBaseComponent.getPowerLevel() + damage;
            }

            if (damageComponent.attack(DamageContainer.projectile(thisEntity, (float) damage)) && other instanceof EntityPhysicsComponent physicsComponent) {
                var kb = EntityPhysicsComponent.DEFAULT_KNOCKBACK;
                var additionalMotion = new Vector3d();
                var punchLevel = arrowBaseComponent.getPunchLevel();
                if (punchLevel != 0) {
                    kb /= 2.0;
                    additionalMotion = MathUtils.normalizeIfNotZero(MathUtils.getDirectionVector(other.getLocation()).setComponent(1, 0));
                    // TODO: This factor (1) is only obtained based on the relative knockback distance relationship between knockback and punch, and may not be accurate.
                    var factor = 1;
                    additionalMotion.mul(punchLevel * factor);
                }
                physicsComponent.knockback(hitPos.sub(this.motion, new Vector3d()), kb, EntityPhysicsComponent.DEFAULT_KNOCKBACK, additionalMotion);
            }

            if (arrowBaseComponent.getFlameLevel() > 0) {
                damageComponent.setOnFireTicks(100);
            }
        }

        thisEntity.despawn();
    }

    private int getDifficultyBonus() {
        return switch (thisEntity.getWorld().getWorldData().getDifficulty()) {
            case EASY -> 1;
            case NORMAL -> 2;
            case HARD -> 3;
            default -> 0;
        };
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        this.arrowBaseComponent.applyEntityEvent(
                EntityEventType.ARROW_SHAKE,
                7 // How many times the arrow shakes
        );
    }

    @Override
    public boolean applyMotion() {
        // TODO: check it
//        double x = motion.x();
//        double y = motion.y();
//        double z = motion.z();
//
//        double yaw = Math.toDegrees(Math.atan2(-x, z));
//        double pitch = Math.toDegrees(Math.atan2(-y, Math.sqrt(x * x + z * z)));
//        this.arrowBaseComponent.getLocation().setYaw(yaw);
//        this.arrowBaseComponent.getLocation().setPitch(pitch);
        return super.applyMotion();
    }
}
