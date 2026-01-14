package org.allaymc.server.item.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.world.explosion.WindExplosion;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.api.world.sound.Sound;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;

/**
 * @author daoge_cmd
 */
public class ItemMaceBaseComponentImpl extends ItemBaseComponentImpl {

    // Smash attack constants
    private static final float SMASH_TRIGGER_FALL_DISTANCE = 1.5f;
    private static final int SMASH_BLOCKS_HIGH = 3;
    private static final int SMASH_BLOCKS_MID = 5;
    private static final float SMASH_DAMAGE_HIGH = 3.0f;
    private static final float SMASH_DAMAGE_MID = 1.5f;
    private static final float SMASH_DAMAGE_LOW = 1.0f;
    private static final float HEAVY_SMASH_DAMAGE = 16.0f;

    // AOE knockback constants
    private static final double AOE_HORIZONTAL_RADIUS = 3.0;
    private static final double AOE_VERTICAL_RADIUS = 2.0;
    private static final double KNOCKBACK_STRENGTH = 0.35;
    private static final double KNOCKBACK_VERTICAL = 0.60;

    // Wind burst constants (mace enchantment)
    private static final double WIND_BURST_MIN_FALL_DISTANCE = 1.5;
    private static final double WIND_BURST_FALL_DISTANCE_CAP = 7.5;
    private static final double WIND_BURST_MOTION_FALL_THRESHOLD = -0.08;
    private static final double WIND_BURST_MOTION_FALL_FACTOR = 4.0;
    private static final double WIND_BURST_MOTION_FALL_CAP = 2.5;
    private static final double WIND_BURST_BASE_VERTICAL = 0.72;
    private static final double WIND_BURST_FALL_VERTICAL = 0.10;
    private static final double WIND_BURST_BASE_FORWARD = 0.08;
    private static final double WIND_BURST_FORWARD_PER_LEVEL = 0.02;
    private static final double WIND_BURST_GUST_RADIUS = 2.5;
    private static final double WIND_BURST_GUST_BASE_STRENGTH = 0.60;
    private static final double WIND_BURST_GUST_STRENGTH_PER_LEVEL = 0.15;
    private static final double WIND_BURST_GUST_BASE_VERTICAL = 0.40;
    private static final double WIND_BURST_GUST_VERTICAL_PER_LEVEL = 0.10;

    public ItemMaceBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void onAttackEntity(Entity attacker, Entity victim) {
        // Check if attacker has physics component
        if (attacker instanceof EntityPhysicsComponent physicsComponent) {
            var fallDistance = physicsComponent.getFallDistance();

            // Apply smash attack effects if falling
            if (fallDistance > SMASH_TRIGGER_FALL_DISTANCE) {
                applySmashEffects(attacker, victim, calculateSmashBonus(fallDistance));
            }

            // Reset fall distance and apply recoil
            var windBurstApplied = applyWindBurst(attacker, physicsComponent, fallDistance);
            physicsComponent.resetFallDistance();
            if (!windBurstApplied) {
                physicsComponent.addMotion(0, 0.05, 0);
            }
        }
    }

    /**
     * Calculate the smash bonus damage based on fall distance.
     * <p>
     * The first 3 blocks contribute 3.0 damage each,
     * the next 5 blocks contribute 1.5 damage each,
     * and any further blocks contribute 1.0 damage each.
     */
    private float calculateSmashBonus(double fallDistance) {
        if (fallDistance <= SMASH_TRIGGER_FALL_DISTANCE) {
            return 0;
        }

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
     * Apply smash attack effects: sound, particles, and AOE knockback.
     */
    private void applySmashEffects(Entity attacker, Entity victim, float smashBonus) {
        var dimension = victim.getDimension();
        var victimLoc = victim.getLocation();

        // Play sound based on damage and ground state
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
                // Spawn dust particle if the victim is standing on a solid block
                dimension.addParticle(victimLoc, SimpleParticle.SMASH_ATTACK_GROUND_DUST);
            }

            // Apply knockback to the victim
            var attackerLoc = attacker.getLocation();
            var attackerPos = new Vector3d(attackerLoc.x(), attackerLoc.y(), attackerLoc.z());
            victimPhysicsComponent.knockback(attackerPos, KNOCKBACK_STRENGTH, KNOCKBACK_VERTICAL);
        }

        // Apply AOE knockback to nearby entities
        applyAoeKnockback(attacker, victim);
    }

    /**
     * Apply knockback to all living entities within the AOE radius.
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
            // Skip the attacker and the victim
            if (entity == attacker || entity == victim) {
                continue;
            }

            if (entity instanceof EntityPhysicsComponent physicsComponent) {
                physicsComponent.knockback(pos, KNOCKBACK_STRENGTH, KNOCKBACK_VERTICAL);
            }
        }
    }

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

    private double adjustWindBurstFallDistance(double fallDistance, Vector3dc motion) {
        if (fallDistance >= WIND_BURST_MIN_FALL_DISTANCE) {
            return fallDistance;
        }

        if (motion.y() >= WIND_BURST_MOTION_FALL_THRESHOLD) {
            return fallDistance;
        }

        var speedBasedFall = Math.min(WIND_BURST_MOTION_FALL_CAP, -motion.y() * WIND_BURST_MOTION_FALL_FACTOR);
        return Math.max(fallDistance, speedBasedFall);
    }

    private double getWindBurstLevelBonus(int level) {
        return Math.max(0.0, 0.75 * level - 0.95);
    }

    private Vector3d createWindBurstMotion(Entity attacker, double forwardBoost, double verticalBoost) {
        var yaw = Math.toRadians(attacker.getLocation().yaw());
        var forward = new Vector3d(-Math.sin(yaw), 0, Math.cos(yaw));
        if (forward.lengthSquared() > 0) {
            forward.normalize().mul(forwardBoost);
        }
        return new Vector3d(forward.x(), verticalBoost, forward.z());
    }

    private void applyWindBurstGust(Entity attacker, int level) {
        var radius = WIND_BURST_GUST_RADIUS;
        var strength = WIND_BURST_GUST_BASE_STRENGTH + WIND_BURST_GUST_STRENGTH_PER_LEVEL * level;
        var vertical = WIND_BURST_GUST_BASE_VERTICAL + WIND_BURST_GUST_VERTICAL_PER_LEVEL * level;

        var explosion = new WindExplosion(radius, strength, vertical);
        explosion.setShooter(attacker);
        explosion.setApplySelfKnockback(false);
        explosion.explode(attacker.getDimension(), attacker.getLocation());
    }
}
