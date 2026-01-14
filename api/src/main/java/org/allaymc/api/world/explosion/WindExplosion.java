package org.allaymc.api.world.explosion;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.api.world.sound.Sound;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.primitives.AABBd;

/**
 * Represents a wind charge explosion that only applies knockback
 * without damaging entities or destroying blocks.
 * <p>
 * Unlike regular explosions, wind explosions:
 * <ul>
 *     <li>Do not destroy blocks</li>
 *     <li>Do not damage entities</li>
 *     <li>Only apply knockback force to nearby entities</li>
 *     <li>Can optionally apply self-knockback to the shooter</li>
 * </ul>
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class WindExplosion {

    /**
     * The vertical knockback applied to the shooter when they are within
     * the self-vertical-only range (for wind charge jumping).
     */
    protected static final double SELF_VERTICAL_KNOCKBACK_Y = 1.159;

    /**
     * The horizontal range within which the shooter receives vertical-only knockback.
     */
    protected static final double SELF_VERTICAL_ONLY_RANGE = 1.0;

    /**
     * The damping factor for horizontal motion when applying self-knockback.
     * This reduces horizontal movement to 5% of normal.
     */
    protected static final double SELF_VERTICAL_HORIZONTAL_DAMPING = 0.05;

    /**
     * The radius within which entities will be affected by the knockback.
     */
    protected double knockbackRadius;

    /**
     * The horizontal knockback strength applied to entities.
     */
    protected double knockbackStrength;

    /**
     * The vertical knockback strength applied to entities.
     */
    protected double knockbackY;

    /**
     * The sound to play when the wind explosion occurs.
     */
    protected Sound sound;

    /**
     * The particle to spawn when the wind explosion occurs.
     */
    protected Particle particle;

    /**
     * The entity that caused the explosion (e.g., the wind charge projectile).
     * Used to exclude it from knockback calculation.
     */
    protected Entity source;

    /**
     * The entity that shot the wind charge. If set, this entity will receive
     * self-knockback (useful for wind charge jump mechanic).
     */
    protected Entity shooter;

    /**
     * Whether the shooter should receive self-knockback.
     */
    protected boolean applySelfKnockback;

    /**
     * Creates a default wind explosion with player wind charge parameters.
     */
    public WindExplosion() {
        this(2.0, 0.5, 0.6);
    }

    /**
     * Creates a wind explosion with custom parameters.
     *
     * @param knockbackRadius   the radius within which entities are affected
     * @param knockbackStrength the horizontal knockback strength
     * @param knockbackY        the vertical knockback strength
     */
    public WindExplosion(double knockbackRadius, double knockbackStrength, double knockbackY) {
        this(knockbackRadius, knockbackStrength, knockbackY,
                SimpleSound.WIND_CHARGE_BURST, SimpleParticle.WIND_EXPLOSION);
    }

    /**
     * Creates a wind explosion with full customization.
     *
     * @param knockbackRadius   the radius within which entities are affected
     * @param knockbackStrength the horizontal knockback strength
     * @param knockbackY        the vertical knockback strength
     * @param sound             the sound to play
     * @param particle          the particle to spawn
     */
    public WindExplosion(double knockbackRadius, double knockbackStrength,
                         double knockbackY, Sound sound, Particle particle) {
        this.knockbackRadius = knockbackRadius;
        this.knockbackStrength = knockbackStrength;
        this.knockbackY = knockbackY;
        this.sound = sound;
        this.particle = particle;
        this.applySelfKnockback = true;
    }

    /**
     * Factory method to create a wind explosion for player-thrown wind charges.
     *
     * @return a WindExplosion configured for player wind charges
     */
    public static WindExplosion playerWindCharge() {
        return new WindExplosion(2.0, 0.5, 0.6);
    }

    /**
     * Factory method to create a wind explosion for breeze wind charges.
     * Breeze wind charges have larger radius but 90% reduced knockback.
     *
     * @return a WindExplosion configured for breeze wind charges
     */
    public static WindExplosion breezeWindCharge() {
        var explosion = new WindExplosion(3.0, 0.5 * 0.9, 0.6 * 0.9);
        explosion.setSound(SimpleSound.BREEZE_WIND_CHARGE_BURST);
        explosion.setParticle(SimpleParticle.BREEZE_WIND_EXPLOSION);
        explosion.setApplySelfKnockback(false);
        return explosion;
    }

    /**
     * Triggers the wind explosion at a specific position.
     *
     * @param dimension the dimension where the explosion occurs
     * @param pos       the position of the explosion
     */
    public void explode(Dimension dimension, Vector3dc pos) {
        explode(dimension, pos.x(), pos.y(), pos.z());
    }

    /**
     * Triggers the wind explosion at specific coordinates.
     *
     * @param dimension the dimension where the explosion occurs
     * @param x         the x coordinate
     * @param y         the y coordinate
     * @param z         the z coordinate
     */
    public void explode(Dimension dimension, double x, double y, double z) {
        var explosionPos = new Vector3d(x, y, z);

        // Calculate the bounding box for affected entities
        var aabb = new AABBd(
                x - knockbackRadius, y - knockbackRadius, z - knockbackRadius,
                x + knockbackRadius, y + knockbackRadius, z + knockbackRadius
        );

        // Get all entities within range
        var affectedEntities = dimension.getEntityManager()
                .getPhysicsService()
                .computeCollidingEntities(aabb);

        // Remove the source entity (wind charge projectile itself)
        if (source != null) {
            affectedEntities.remove(source);
        }

        // Apply knockback to each affected entity
        for (var entity : affectedEntities) {
            if (!(entity instanceof EntityPhysicsComponent physicsComponent)) {
                continue;
            }

            var entityPos = entity.getLocation();
            var distance = entityPos.distance(explosionPos);

            if (distance > knockbackRadius) {
                continue;
            }

            // Skip shooter if self-knockback is disabled
            if (entity == shooter && !applySelfKnockback) {
                continue;
            }

            var kbResistance = physicsComponent.getKnockbackResistance();

            // Calculate knockback direction
            var direction = entityPos.sub(explosionPos, new Vector3d());
            var horizontalDistance = Math.sqrt(direction.x() * direction.x() + direction.z() * direction.z());

            // Check if this is the shooter within vertical-only range
            if (entity == shooter && applySelfKnockback && horizontalDistance <= SELF_VERTICAL_ONLY_RANGE) {
                // Apply vertical-only self-knockback (for wind charge jumping)
                var verticalKb = SELF_VERTICAL_KNOCKBACK_Y * (1.0 - kbResistance);
                var horizontalKb = knockbackStrength * SELF_VERTICAL_HORIZONTAL_DAMPING * (1.0 - kbResistance);

                var motion = new Vector3d();
                if (horizontalDistance > 0) {
                    motion.set(
                            (direction.x() / horizontalDistance) * horizontalKb,
                            verticalKb,
                            (direction.z() / horizontalDistance) * horizontalKb
                    );
                } else {
                    motion.set(0, verticalKb, 0);
                }
                physicsComponent.addMotion(motion);
            } else {
                // Normal knockback for other entities or shooter outside vertical-only range
                if (horizontalDistance > 0) {
                    direction.x /= horizontalDistance;
                    direction.z /= horizontalDistance;
                } else if (distance > 0) {
                    // Entity is directly above or below, use random horizontal direction
                    var angle = Math.random() * Math.PI * 2;
                    direction.x = Math.cos(angle);
                    direction.z = Math.sin(angle);
                } else {
                    // Entity is at explosion center
                    direction.set(Math.random() - 0.5, 0, Math.random() - 0.5).normalize();
                }

                var horizontalKb = knockbackStrength * (1.0 - kbResistance);
                var verticalKb = knockbackY * (1.0 - kbResistance);

                var motion = new Vector3d(
                        direction.x() * horizontalKb,
                        verticalKb,
                        direction.z() * horizontalKb
                );
                physicsComponent.addMotion(motion);
            }
        }

        // Play effects
        dimension.addParticle(x, y, z, particle);
        dimension.addSound(x, y, z, sound);
    }
}
