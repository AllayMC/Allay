package org.allaymc.server.item.component;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.api.world.sound.Sound;
import org.joml.Vector3d;
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
            physicsComponent.resetFallDistance();
            physicsComponent.addMotion(0, 0.05, 0);
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
}
