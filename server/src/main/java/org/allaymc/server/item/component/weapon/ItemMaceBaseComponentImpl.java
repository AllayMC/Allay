package org.allaymc.server.item.component.weapon;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.explosion.WindExplosion;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.api.world.sound.Sound;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;

/**
 * {@code ItemMaceBaseComponentImpl} computes fall-based mace damage and applies
 * smash, recoil, and Wind Burst effects after attacks. Smash damage counts
 * whole fallen blocks through three declining damage bands, and Density adds
 * a separate level-scaled contribution. A physics-capable victim receives
 * direct knockback, while nearby physics-capable entities receive a second
 * area effect.
 *
 * <p>
 * Wind Burst uses the attacker's recorded fall distance, with a bounded
 * downward-motion estimate when that distance is below its trigger. The
 * launch calculation caps the effective fall contribution and replaces attacker
 * motion before emitting a gust that excludes the attacker. Attack handling
 * resets fall distance afterward; if no Wind Burst is applied, the attacker
 * receives a small upward recoil even when the smash threshold was not met.
 *
 * @author daoge_cmd
 */
public class ItemMaceBaseComponentImpl extends ItemBaseComponentImpl {

    // Smash thresholds and damage contributions govern whole-block fall damage,
    // Density bonuses, and the sound chosen for a heavy ground hit.
    /**
     * Minimum exceeded fall distance for smashing, in blocks.
     */
    private static final float SMASH_TRIGGER_FALL_DISTANCE = 1.5f;
    /**
     * Initial high-damage band length, in blocks.
     */
    private static final int SMASH_BLOCKS_HIGH = 3;
    /**
     * Following medium-damage band length, in blocks.
     */
    private static final int SMASH_BLOCKS_MID = 5;
    /**
     * Damage per initial-band fall block.
     */
    private static final float SMASH_DAMAGE_HIGH = 4.0f;
    /**
     * Damage per middle-band fall block.
     */
    private static final float SMASH_DAMAGE_MID = 2.0f;
    /**
     * Damage per remaining fall block.
     */
    private static final float SMASH_DAMAGE_LOW = 1.0f;
    /**
     * Raw damage threshold for the heavy ground sound.
     */
    private static final float HEAVY_SMASH_DAMAGE = 16.0f;
    /**
     * Density damage per level per whole fallen block.
     */
    private static final float DENSITY_DAMAGE_PER_LEVEL = 0.5f;

    // Area bounds and knockback strengths control the secondary smash effect
    // around the victim, using fixed motion contributions for eligible entities.
    /**
     * Horizontal search half-width, in blocks.
     */
    private static final double AOE_HORIZONTAL_RADIUS = 3.0;
    /**
     * Vertical search half-height, in blocks.
     */
    private static final double AOE_VERTICAL_RADIUS = 2.0;
    /**
     * Horizontal smash knockback strength.
     */
    private static final double KNOCKBACK_STRENGTH = 0.35;
    /**
     * Vertical smash knockback strength.
     */
    private static final double KNOCKBACK_VERTICAL = 0.60;

    // Wind Burst thresholds combine measured falling with a bounded motion
    // estimate; launch and gust coefficients scale the resulting movement by
    // fall distance and enchantment level.
    /**
     * Minimum effective fall distance, in blocks.
     */
    private static final double WIND_BURST_MIN_FALL_DISTANCE = 1.5;
    /**
     * Maximum launch-contributing fall distance, in blocks.
     */
    private static final double WIND_BURST_FALL_DISTANCE_CAP = 7.5;
    /**
     * Vertical motion threshold enabling the fallback estimate.
     */
    private static final double WIND_BURST_MOTION_FALL_THRESHOLD = -0.08;
    /**
     * Downward-motion scale for estimated fall distance.
     */
    private static final double WIND_BURST_MOTION_FALL_FACTOR = 4.0;
    /**
     * Maximum motion-derived fall estimate, in blocks.
     */
    private static final double WIND_BURST_MOTION_FALL_CAP = 2.5;
    /**
     * Base upward launch motion.
     */
    private static final double WIND_BURST_BASE_VERTICAL = 0.72;
    /**
     * Upward motion contribution per effective fall block.
     */
    private static final double WIND_BURST_FALL_VERTICAL = 0.10;
    /**
     * Base forward launch motion.
     */
    private static final double WIND_BURST_BASE_FORWARD = 0.08;
    /**
     * Forward launch motion per enchantment level.
     */
    private static final double WIND_BURST_FORWARD_PER_LEVEL = 0.02;
    /**
     * Wind gust search radius, in blocks.
     */
    private static final double WIND_BURST_GUST_RADIUS = 2.5;
    /**
     * Base horizontal gust knockback strength.
     */
    private static final double WIND_BURST_GUST_BASE_STRENGTH = 0.60;
    /**
     * Horizontal gust strength per enchantment level.
     */
    private static final double WIND_BURST_GUST_STRENGTH_PER_LEVEL = 0.15;
    /**
     * Base vertical gust knockback strength.
     */
    private static final double WIND_BURST_GUST_BASE_VERTICAL = 0.40;
    /**
     * Vertical gust strength per enchantment level.
     */
    private static final double WIND_BURST_GUST_VERTICAL_PER_LEVEL = 0.10;

    /**
     * Creates a mace base component from the supplied stack initialization data.
     */
    public ItemMaceBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns ordinary attack damage plus eligible smash and Density bonuses.
     * The attacker must expose physics and exceed the smash fall threshold;
     * otherwise the method returns the ordinary base damage and does not inspect
     * the victim for a bonus.
     */
    @Override
    public float calculateAttackDamage(Entity attacker, Entity victim) {
        var baseDamage = super.calculateAttackDamage();

        if (attacker instanceof EntityPhysicsComponent physicsComponent) {
            var fallDistance = physicsComponent.getFallDistance();
            if (fallDistance > SMASH_TRIGGER_FALL_DISTANCE) {
                var smashBonus = calculateSmashBonus(fallDistance);
                var densityBonus = calculateDensityBonus(fallDistance);
                return baseDamage + smashBonus + densityBonus;
            }
        }

        return baseDamage;
    }

    /**
     * Returns Density damage for the whole-block portion of the supplied fall
     * distance. A missing or nonpositive enchantment level returns zero; otherwise
     * each whole block contributes half a damage point per enchantment level.
     */
    private float calculateDensityBonus(double fallDistance) {
        var densityLevel = getEnchantmentLevel(EnchantmentTypes.DENSITY);
        if (densityLevel <= 0) {
            return 0;
        }
        // A 2.9-block fall contributes the same Density term as a 2.0-block
        // fall, because the final fractional block does not earn this bonus.
        var blocksFallen = (int) Math.floor(fallDistance);
        return blocksFallen * densityLevel * DENSITY_DAMAGE_PER_LEVEL;
    }

    /**
     * Applies smash effects and the attacker's Wind Burst or recoil response.
     * A physics-capable attacker exceeding the smash threshold produces victim
     * and area effects; every physics-capable attacker then attempts Wind Burst
     * and has its fall distance reset.
     *
     * <p>
     * The Wind Burst calculation reads the captured fall distance before that
     * reset. If Wind Burst declines, the handler adds a small upward recoil,
     * including on attacks below the smash threshold. This override does not
     * dispatch the inherited component attack event used by tool durability
     * listeners.
     */
    @Override
    public void onAttackEntity(Entity attacker, Entity victim) {
        if (attacker instanceof EntityPhysicsComponent physicsComponent) {
            var fallDistance = physicsComponent.getFallDistance();

            if (fallDistance > SMASH_TRIGGER_FALL_DISTANCE) {
                var totalBonus = calculateSmashBonus(fallDistance) + calculateDensityBonus(fallDistance);
                applySmashEffects(attacker, victim, totalBonus);
            }

            // Wind Burst needs the pre-impact fall distance. Reset it only after
            // the launch decision so the next fall starts without carrying this hit.
            var windBurstApplied = applyWindBurst(attacker, physicsComponent, fallDistance);
            physicsComponent.resetFallDistance();
            if (!windBurstApplied) {
                // Apply the small recoil even when this hit did not trigger a smash.
                physicsComponent.addMotion(0, 0.05, 0);
            }
        }
    }

    /**
     * Returns the whole-block smash bonus after the fall threshold is exceeded.
     * The first three blocks contribute four damage each, the next five contribute
     * two each, and later blocks contribute one each; fractional blocks add no
     * damage.
     */
    private float calculateSmashBonus(double fallDistance) {
        if (fallDistance <= SMASH_TRIGGER_FALL_DISTANCE) {
            return 0;
        }

        // Count only complete fallen blocks so crossing a damage band changes
        // the rate for later whole blocks without crediting a fractional block.
        var bonus = 0f;
        var blocksFallen = (int) Math.floor(fallDistance);

        for (var i = 0; i < blocksFallen; i++) {
            if (i < SMASH_BLOCKS_HIGH) {
                bonus += SMASH_DAMAGE_HIGH;
            } else if (i < SMASH_BLOCKS_HIGH + SMASH_BLOCKS_MID) {
                bonus += SMASH_DAMAGE_MID;
            } else {
                bonus += SMASH_DAMAGE_LOW;
            }
        }

        return bonus;
    }

    /**
     * Applies smash sound, victim particles, and direct and area knockback.
     * The attacker must implement {@code EntityPhysicsComponent}, as established
     * by the caller. The sound uses raw item attack damage plus
     * {@code smashBonus}, with the heavy variant available only on ground;
     * airborne hits use the air sound in either damage band.
     *
     * <p>
     * A physics-capable victim receives direct knockback and emits dust when its
     * standing block is solid. The subsequent area query skips both attacker and
     * victim so the direct victim knockback is not applied a second time.
     */
    private void applySmashEffects(Entity attacker, Entity victim, float smashBonus) {
        var dimension = victim.getDimension();
        var victimLoc = victim.getLocation();

        // Heavy sound selection uses raw item damage plus the supplied bonuses,
        // while the attacker's ground state decides whether any ground variant plays.
        Sound sound;
        var attackerPhysicsComponent = (EntityPhysicsComponent) attacker;
        if (itemType.getItemData().attackDamage() + smashBonus >= HEAVY_SMASH_DAMAGE) {
            sound = attackerPhysicsComponent.isOnGround() ? SimpleSound.MACE_SMASH_HEAVY_GROUND : SimpleSound.MACE_SMASH_AIR;
        } else {
            sound = attackerPhysicsComponent.isOnGround() ? SimpleSound.MACE_SMASH_GROUND : SimpleSound.MACE_SMASH_AIR;
        }
        dimension.addSound(victimLoc, sound);

        if (victim instanceof EntityPhysicsComponent victimPhysicsComponent) {
            if (victimPhysicsComponent.getBlockStateStandingOn().getBlockStateData().isSolid()) {
                dimension.addParticle(victimLoc, SimpleParticle.SMASH_ATTACK_GROUND_DUST);
            }

            victimPhysicsComponent.knockback(attacker.getLocation(), KNOCKBACK_STRENGTH, KNOCKBACK_VERTICAL);
        }

        applyAoeKnockback(attacker, victim);
    }

    /**
     * Applies secondary knockback to physics-capable entities intersecting the
     * victim-centered search box. The attacker and direct victim are excluded,
     * and eligibility does not require a living-entity interface or an additional
     * spherical-distance check.
     */
    private void applyAoeKnockback(Entity attacker, Entity victim) {
        var dimension = victim.getDimension();
        var pos = victim.getLocation();
        var aabb = new AABBd(
                pos.x() - AOE_HORIZONTAL_RADIUS,
                pos.y() - AOE_VERTICAL_RADIUS,
                pos.z() - AOE_HORIZONTAL_RADIUS,
                pos.x() + AOE_HORIZONTAL_RADIUS,
                pos.y() + AOE_VERTICAL_RADIUS,
                pos.z() + AOE_HORIZONTAL_RADIUS
        );
        var nearbyEntities = dimension.getEntityManager().getPhysicsService().computeCollidingEntities(aabb);

        for (var entity : nearbyEntities) {
            if (entity == attacker || entity == victim) {
                continue;
            }

            if (entity instanceof EntityPhysicsComponent physicsComponent) {
                physicsComponent.knockback(pos, KNOCKBACK_STRENGTH, KNOCKBACK_VERTICAL);
            }
        }
    }

    /**
     * Attempts an enchantment-scaled Wind Burst launch and returns whether it
     * was applied. A missing enchantment or a flying or gliding player declines
     * the effect; other attackers use recorded fall distance augmented by the
     * bounded downward-motion estimate from {@code adjustWindBurstFallDistance}.
     *
     * <p>
     * The effective distance must reach the trigger before the launch is
     * calculated. The fall contribution is capped independently of the
     * enchantment bonus, and the new motion replaces the previous motion rather
     * than adding to it. The gust runs after replacement and excludes the
     * attacker from its own knockback.
     */
    private boolean applyWindBurst(Entity attacker, EntityPhysicsComponent physicsComponent, double fallDistance) {
        var windBurstLevel = getEnchantmentLevel(EnchantmentTypes.WIND_BURST);
        if (windBurstLevel <= 0) {
            return false;
        }

        if (attacker instanceof EntityPlayer player && (player.isFlying() || player.isGliding())) {
            return false;
        }

        var motion = physicsComponent.getMotion();
        var adjustedFallDistance = adjustWindBurstFallDistance(fallDistance, motion);
        if (adjustedFallDistance < WIND_BURST_MIN_FALL_DISTANCE) {
            return false;
        }

        // Cap only the distance contribution, leaving the enchantment-level bonus
        // independent so longer falls cannot keep increasing that part of vertical motion.
        var clampedFallDistance = Math.min(adjustedFallDistance, WIND_BURST_FALL_DISTANCE_CAP);
        var verticalBoost = WIND_BURST_BASE_VERTICAL
                + clampedFallDistance * WIND_BURST_FALL_VERTICAL
                + getWindBurstLevelBonus(windBurstLevel);
        var forwardBoost = WIND_BURST_BASE_FORWARD + WIND_BURST_FORWARD_PER_LEVEL * windBurstLevel;

        var newMotion = createWindBurstMotion(attacker, forwardBoost, verticalBoost);
        physicsComponent.setMotion(newMotion);

        applyWindBurstGust(attacker, windBurstLevel);
        return true;
    }

    /**
     * Returns the fall distance used to decide Wind Burst eligibility.
     * A recorded distance already at the trigger is kept, and motion that is not
     * sufficiently downward also leaves the recorded distance unchanged.
     *
     * <p>
     * When the recorded distance is below the trigger and downward motion passes
     * the threshold, the method converts that motion to a capped fall estimate.
     * The result is the greater of the recorded distance and estimate, so this
     * alternative evidence can enable a launch without reducing a measured fall
     * or allowing the motion estimate to grow without its own bound.
     */
    private double adjustWindBurstFallDistance(double fallDistance, Vector3dc motion) {
        if (fallDistance >= WIND_BURST_MIN_FALL_DISTANCE) {
            return fallDistance;
        }

        if (motion.y() >= WIND_BURST_MOTION_FALL_THRESHOLD) {
            return fallDistance;
        }

        // When measured falling is below the trigger, downward motion supplies
        // alternative evidence; cap that estimate before comparing it with the measurement.
        var speedBasedFall = Math.min(WIND_BURST_MOTION_FALL_CAP, -motion.y() * WIND_BURST_MOTION_FALL_FACTOR);
        return Math.max(fallDistance, speedBasedFall);
    }

    /**
     * Returns the additional upward motion contributed by the enchantment level.
     * The linear level expression is floored at zero, so level one contributes
     * no extra upward motion through this particular term.
     */
    private double getWindBurstLevelBonus(int level) {
        // Keep the level-one contribution at zero rather than reducing base motion.
        return Math.max(0.0, 0.75 * level - 0.95);
    }

    /**
     * Returns replacement launch motion using horizontal facing and the supplied
     * boosts. The method ignores view pitch, so looking vertically does not
     * remove the horizontal boost or change the separately computed upward motion.
     */
    private Vector3d createWindBurstMotion(Entity attacker, double forwardBoost, double verticalBoost) {
        var forward = MathUtils.getDirectionVector(attacker.getLocation().yaw(), 0).mul(forwardBoost);
        return new Vector3d(forward.x(), verticalBoost, forward.z());
    }

    /**
     * Emits a Wind Burst explosion at the attacker with enchantment-scaled
     * knockback strengths. The explosion records the attacker as shooter and
     * disables its self-knockback so the gust does not replace the launch motion
     * just applied to that attacker.
     */
    private void applyWindBurstGust(Entity attacker, int level) {
        var strength = WIND_BURST_GUST_BASE_STRENGTH + WIND_BURST_GUST_STRENGTH_PER_LEVEL * level;
        var vertical = WIND_BURST_GUST_BASE_VERTICAL + WIND_BURST_GUST_VERTICAL_PER_LEVEL * level;

        var explosion = new WindExplosion(WIND_BURST_GUST_RADIUS, strength, vertical);
        explosion.setShooter(attacker);
        explosion.setApplySelfKnockback(false);
        explosion.explode(attacker.getDimension(), attacker.getLocation());
    }
}
