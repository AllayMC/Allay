package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.ArrowShakeAction;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
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
    @Dependency
    protected EntityDamageComponent damageComponent;

    // Indicates whether the arrow has already hit a block
    protected boolean hitBlock;

    @Override
    public double getGravity() {
        return 0.05;
    }

    @Override
    public Vector3d updateMotion(boolean hasLiquidMotion) {
        if (hitBlock && arrowBaseComponent.checkBlockCollision()) {
            // Set motion to zero if collided with blocks after hit block
            return new Vector3d(0, 0, 0);
        }

        return new Vector3d(
                this.motion.x * (1 - this.getDragFactorInAir()),
                (this.motion.y - this.getGravity()) * (1 - this.getDragFactorInAir()),
                this.motion.z * (1 - this.getDragFactorInAir())
        );
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick()) {
            return;
        }

        addHitSound(hitPos);
        var potionType = arrowBaseComponent.getPotionType();
        if (potionType != null) {
            potionType.applyTo(other);
        }

        if (other instanceof EntityDamageComponent otherDamageComponent) {
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

            var damageContainer = DamageContainer.projectile(thisEntity, (float) damage);
            damageContainer.setHasKnockback(false);
            if (otherDamageComponent.attack(damageContainer) && other instanceof EntityPhysicsComponent physicsComponent) {
                var kb = EntityPhysicsComponent.DEFAULT_KNOCKBACK;
                var additionalMotion = new Vector3d();
                var punchLevel = arrowBaseComponent.getPunchLevel();
                if (punchLevel != 0) {
                    kb /= 2.0;
                    additionalMotion = MathUtils.normalizeIfNotZero(this.motion).setComponent(1, 0);
                    additionalMotion.mul(punchLevel * 0.5);
                }
                // Use the last location as the knockback source
                physicsComponent.knockback(hitPos.sub(this.motion, new Vector3d()), kb, EntityPhysicsComponent.DEFAULT_KNOCKBACK, additionalMotion);
            }

            if (this.damageComponent.isOnFire()) {
                otherDamageComponent.setOnFireTicks(20 * 5);
            }
        }

        thisEntity.remove();
    }

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedNextTick() || this.hitBlock) {
            return;
        }

        addHitSound(hitPos);
        this.arrowBaseComponent.applyAction(new ArrowShakeAction(7));
        this.arrowBaseComponent.setCritical(false);
        this.hitBlock = true;
    }

    private void addHitSound(Vector3dc hitPos) {
        this.arrowBaseComponent.getDimension().addSound(hitPos, SimpleSound.ARROW_HIT);
    }

    private int getDifficultyBonus() {
        return switch (thisEntity.getWorld().getWorldData().getDifficulty()) {
            case EASY -> 1;
            case NORMAL -> 2;
            case HARD -> 3;
            default -> 0;
        };
    }
}
