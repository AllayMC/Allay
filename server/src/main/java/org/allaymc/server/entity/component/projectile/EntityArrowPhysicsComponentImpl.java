package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.ArrowShakeAction;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntityArrowPhysicsComponentImpl} resolves arrow embedding, damage,
 * potion effects, and piercing lifetime. Embedded arrows remain motionless
 * while their block collision persists, before liquid updates are considered.
 * Removing the supporting collision clears the embedded flag and restores
 * ordinary projectile acceleration on the same update.
 *
 * <p>
 * Entity impacts combine shooter kind, speed, difficulty, enchantments, and
 * current flags when preparing damage. Pierced runtime identifiers remain
 * recorded so later ray tests and callbacks skip those targets. Each permitted
 * piercing impact consumes one retained identifier before ordinary removal
 * becomes necessary, while block impacts clear critical state, mark embedding,
 * and zero stored motion immediately.
 *
 * @author harryxi | daoge_cmd
 */
public class EntityArrowPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * Injected arrow damage and pickup-state component.
     */
    @Dependency
    protected EntityArrowBaseComponent arrowBaseComponent;
    /**
     * Injected component supplying optional potion contents.
     */
    @Dependency
    protected EntityPotionComponent potionComponent;
    /**
     * Injected retained shooter reference component.
     */
    @Dependency
    protected EntityProjectileComponent projectileComponent;

    /**
     * {@code piercedEntities} retains runtime identifiers hit during piercing.
     * Collision filtering and impact handling share this set, and changing the
     * Piercing level does not clear prior identifiers.
     */
    protected Set<Long> piercedEntities = new HashSet<>();

    /**
     * Returns arrow downward acceleration in blocks per tick squared.
     */
    @Override
    public double getGravity() {
        return 0.05;
    }

    /**
     * Returns the fraction of arrow motion removed by water drag.
     */
    @Override
    public double getWaterDragFactor() {
        return 0.4;
    }

    /**
     * Returns the fraction of arrow motion removed by lava drag.
     */
    @Override
    public double getLavaDragFactor() {
        return 0.4;
    }

    /**
     * Returns the next arrow motion while preserving the priority of embedded
     * state. A block collision keeps an embedded arrow at zero motion even
     * when liquid would otherwise apply buoyancy or drag. A removed collision
     * clears {@code inGround} before another motion branch is selected.
     *
     * <p>
     * Liquid physics applies only after embedding has been ruled out.
     * Otherwise gravity and air drag produce a new motion vector from the
     * stored vector, leaving assignment to the engine motion setter. This
     * method does not itself move the arrow or publish the changed embedded
     * flag.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (arrowBaseComponent.isInGround()) {
            if (arrowBaseComponent.checkBlockCollision()) {
                return new Vector3d();
            }
            arrowBaseComponent.setInGround(false);
        }

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
     * Processes a new permitted target impact and decides whether piercing
     * preserves the arrow. Queued removal and previously pierced identifiers
     * suppress the callback. Living targets receive potion effects before
     * projectile damage, and player shooters add speed, difficulty, random,
     * and optional critical adjustments.
     *
     * <p>
     * For finite inputs, reversed critical bounds select ten before Power
     * adjustment. Punch normalizes, flattens, and scales the retained {@code
     * motion} vector itself; the additional-knockback vector shares that
     * storage, changing continued piercing flight as well as target impulse.
     * Burning arrows ignite living targets even after rejected attacks. A
     * positive Piercing allowance records the target and preserves flight
     * until exhausted; other impacts request removal.
     */
    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        // A retained runtime identifier suppresses repeated damage if another
        // deferred callback reaches an already pierced target.
        long entityId = other.getRuntimeId();
        if (piercedEntities.contains(entityId)) {
            return;
        }

        addHitSound(hitPos);
        if (other instanceof EntityLiving living) {
            var potionType = potionComponent.getPotionType();
            if (potionType != null) {
                potionType.applyTo(living);
            }

            double damage = arrowBaseComponent.getBaseDamage();
            if (projectileComponent.getShooter() instanceof EntityPlayer) {
                damage = damage
                         + 0.11 * getDifficultyBonus()
                         + 0.25 * ThreadLocalRandom.current().nextGaussian()
                         + 0.97 * motion.length();
                if (arrowBaseComponent.isCritical()) {
                    double criticalBonus = 0.5 * ThreadLocalRandom.current().nextDouble() * damage + 2 * ThreadLocalRandom.current().nextDouble();
                    double criticalDamage = damage + criticalBonus;
                    // For finite inputs, these reversed clamp bounds force ten
                    // before the later Power adjustment, regardless of the sampled
                    // critical bonus.
                    damage = Math.max(10, Math.min(9, criticalDamage));
                }
            }
            if (arrowBaseComponent.getPowerLevel() > 0) {
                damage = 1.25 * damage + 0.25 * arrowBaseComponent.getPowerLevel() + damage;
            }

            var damageContainer = DamageContainer.projectile(thisEntity, (float) damage);
            damageContainer.setKnockbackSource(hitPos.sub(this.motion, new Vector3d()));

            var punchLevel = arrowBaseComponent.getPunchLevel();
            if (punchLevel != 0) {
                damageContainer.setKnockback(EntityPhysicsComponent.DEFAULT_KNOCKBACK / 2.0);
                // Normalization and scaling operate on retained motion itself, so
                // Punch also changes any continued piercing flight and shares that
                // vector with damage handling.
                var knockbackAdditional = MathUtils.normalizeIfNotZero(this.motion).setComponent(1, 0);
                knockbackAdditional.mul(punchLevel * 0.5);
                damageContainer.setKnockbackAdditional(knockbackAdditional);
            }

            living.attack(damageContainer);

            if (this.livingComponent.isOnFire()) {
                // Convert the five-second ignition duration to the
                // twenty-tick-per-second entity clock.
                living.setOnFireTicks(20 * 5);
            }
        }

        int piercingLevel = arrowBaseComponent.getPiercingLevel();
        if (piercingLevel > 0 && piercedEntities.size() < piercingLevel) {
            // Retain each traversed target before continuing so subsequent ray
            // queries cannot spend another piercing allowance on it.
            piercedEntities.add(entityId);
            return;
        }

        thisEntity.remove();
    }

    /**
     * Embeds the arrow after an accepted block hit and zeroes stored motion
     * immediately. Queued removal or existing embedding suppresses repeated
     * sounds and actions; a new embedding also clears the critical flag.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater() || arrowBaseComponent.isInGround()) {
            return;
        }

        addHitSound(hitPos);
        this.arrowBaseComponent.applyAction(new ArrowShakeAction(7));
        this.arrowBaseComponent.setCritical(false);
        this.arrowBaseComponent.setInGround(true);
        this.motion.zero();
    }

    /**
     * Emits the arrow hit sound at the supplied world position.
     */
    private void addHitSound(Vector3dc hitPos) {
        this.arrowBaseComponent.getDimension().addSound(hitPos, SimpleSound.ARROW_HIT);
    }

    /**
     * Returns the world difficulty bonus used by player-shot arrow damage.
     */
    private int getDifficultyBonus() {
        return switch (thisEntity.getWorld().getWorldData().getDifficulty()) {
            case EASY -> 1;
            case NORMAL -> 2;
            case HARD -> 3;
            default -> 0;
        };
    }

    /**
     * Returns whether parent filtering or prior piercing excludes this entity.
     * Runtime identifiers in {@code piercedEntities} stay excluded after the
     * initial hit so later travel can continue through their bounds.
     */
    @Override
    protected boolean shouldSkipEntityCollision(Entity entity) {
        if (super.shouldSkipEntityCollision(entity)) {
            return true;
        }

        return piercedEntities.contains(entity.getRuntimeId());
    }
}
