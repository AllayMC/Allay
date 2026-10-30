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

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code WindExplosion} configures a wind-charge burst that adds entity motion
 * and emits particle and sound effects. The source entity is excluded, while
 * the optional shooter receives a separate close-range jump response when
 * self-knockback is enabled. No damage or block-destruction pass is
 * implemented here.
 *
 * <p>
 * The entity query first uses a cube and then filters by radial distance.
 * Within one horizontal block, the shooter receives fixed upward motion and
 * damped horizontal motion, scaled by resistance; exactly aligned shooters
 * receive only the upward component. Other cases use configured strengths
 * without distance attenuation. The ordinary response samples a horizontal
 * direction for vertically aligned or centered targets because their position
 * difference cannot provide one.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class WindExplosion {

    /**
     * {@code SELF_VERTICAL_KNOCKBACK_Y} sets the close-shooter upward motion
     * before resistance scaling. The horizontal-range test selects this jump
     * response independently of configured vertical strength.
     */
    protected static final double SELF_VERTICAL_KNOCKBACK_Y = 1.159;

    /**
     * {@code SELF_VERTICAL_ONLY_RANGE} bounds the shooter jump response in
     * horizontal blocks. Shooters outside this radius use the ordinary
     * configured horizontal and vertical strengths.
     */
    protected static final double SELF_VERTICAL_ONLY_RANGE = 1.0;

    /**
     * {@code SELF_VERTICAL_HORIZONTAL_DAMPING} scales close-shooter horizontal
     * motion to five percent of configured strength. The jump response still
     * applies knockback resistance afterward.
     */
    protected static final double SELF_VERTICAL_HORIZONTAL_DAMPING = 0.05;

    /**
     * Entity influence radius in blocks.
     */
    protected double knockbackRadius;

    /**
     * Horizontal motion magnitude before resistance scaling.
     */
    protected double knockbackStrength;

    /**
     * Vertical motion magnitude before resistance scaling.
     */
    protected double knockbackY;

    /**
     * Sound descriptor emitted after motion processing.
     */
    protected Sound sound;

    /**
     * Particle descriptor emitted after motion processing.
     */
    protected Particle particle;

    /**
     * {@code source} identifies the entity excluded from the affected-entity
     * query result. A {@code null} value removes no source before knockback
     * processing.
     */
    protected Entity source;

    /**
     * {@code shooter} identifies the entity eligible for the close-range jump
     * response. {@code applySelfKnockback} determines whether that entity
     * participates in the burst.
     */
    protected Entity shooter;

    /**
     * {@code applySelfKnockback} starts enabled and controls whether {@code
     * shooter} receives motion. Disabling it excludes the shooter but leaves
     * other eligible physics entities affected.
     */
    protected boolean applySelfKnockback;

    /**
     * Creates a player-style burst with radius two, horizontal strength {@code
     * 0.5}, and vertical strength {@code 0.6}. The ordinary wind-charge sound
     * and particle are selected, and shooter self-knockback starts enabled.
     */
    public WindExplosion() {
        this(2.0, 0.5, 0.6);
    }

    /**
     * Creates a burst with explicit radius and motion strengths. This overload
     * selects the ordinary wind-charge sound and particle, enables shooter
     * self-knockback, and retains numeric inputs without clamping or range
     * validation.
     */
    public WindExplosion(double knockbackRadius, double knockbackStrength, double knockbackY) {
        this(knockbackRadius, knockbackStrength, knockbackY,
                SimpleSound.WIND_CHARGE_BURST, SimpleParticle.WIND_EXPLOSION);
    }

    /**
     * Creates a burst retaining the supplied radius, strengths, sound, and
     * particle. Self-knockback starts enabled, the source and shooter start as
     * {@code null}, and numeric inputs are not validated.
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
     * Returns a new player-style burst with radius two and enabled
     * self-knockback. Horizontal strength is {@code 0.5}, vertical strength is
     * {@code 0.6}, and ordinary wind-charge effects are selected.
     */
    public static WindExplosion playerWindCharge() {
        return new WindExplosion(2.0, 0.5, 0.6);
    }

    /**
     * Returns a new breeze-style burst with radius three and disabled
     * self-knockback. The motion strengths are ninety percent of the
     * player-style values, and breeze-specific sound and particle descriptors
     * are selected.
     */
    public static WindExplosion breezeWindCharge() {
        var explosion = new WindExplosion(3.0, 0.5 * 0.9, 0.6 * 0.9);
        explosion.setSound(SimpleSound.BREEZE_WIND_CHARGE_BURST);
        explosion.setParticle(SimpleParticle.BREEZE_WIND_EXPLOSION);
        explosion.setApplySelfKnockback(false);
        return explosion;
    }

    /**
     * Applies this burst at the supplied dimension position by delegating to
     * the coordinate overload. The vector is read immediately; entity motion
     * and final effects follow the same calling rules as that overload.
     */
    public void explode(Dimension dimension, Vector3dc pos) {
        explode(dimension, pos.x(), pos.y(), pos.z());
    }

    /**
     * Adds wind-burst motion to nearby physics entities and then emits the
     * configured effects. The caller must coordinate dimension access and keep
     * this burst's settings stable during the operation; the source entity is
     * excluded from the query result before motion processing.
     *
     * <p>
     * The spherical distance check follows the initial cube query. Enabled
     * self-knockback uses horizontal distance to select the shooter jump
     * response, whose aligned case adds only upward motion. Ordinary response
     * uses configured strengths scaled by resistance and samples a horizontal
     * direction for aligned or centered targets. Motion is applied entity by
     * entity, so failures can leave earlier additions in place without
     * rollback.
     */
    public void explode(Dimension dimension, double x, double y, double z) {
        var explosionPos = new Vector3d(x, y, z);

        var aabb = new AABBd(
                x - knockbackRadius, y - knockbackRadius, z - knockbackRadius,
                x + knockbackRadius, y + knockbackRadius, z + knockbackRadius
        );

        var affectedEntities = dimension.getEntityManager()
                .getPhysicsService()
                .computeCollidingEntities(aabb);

        if (source != null) {
            affectedEntities.remove(source);
        }

        for (var entity : affectedEntities) {
            if (!(entity instanceof EntityPhysicsComponent physicsComponent)) {
                continue;
            }

            var entityPos = entity.getLocation();
            var distance = entityPos.distance(explosionPos);

            if (distance > knockbackRadius) {
                continue;
            }

            if (entity == shooter && !applySelfKnockback) {
                continue;
            }

            var kbResistance = physicsComponent.getKnockbackResistance();

            var direction = entityPos.sub(explosionPos, new Vector3d());
            var horizontalDistance = Math.sqrt(direction.x() * direction.x() + direction.z() * direction.z());

            // The shooter jump response depends on horizontal separation, so
            // vertical displacement alone does not select the ordinary burst response.
            if (entity == shooter && applySelfKnockback && horizontalDistance <= SELF_VERTICAL_ONLY_RANGE) {
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
                if (horizontalDistance > 0) {
                    direction.x /= horizontalDistance;
                    direction.z /= horizontalDistance;
                } else if (distance > 0) {
                    // A vertically aligned target has no horizontal direction to
                    // normalize; sample an angle so ordinary knockback still spreads
                    // it away horizontally.
                    var angle = ThreadLocalRandom.current().nextDouble() * Math.PI * 2;
                    direction.x = Math.cos(angle);
                    direction.z = Math.sin(angle);
                } else {
                    // A centered target has no radial direction at all; sample a
                    // horizontal vector before applying the configured motion strengths.
                    direction.set(ThreadLocalRandom.current().nextDouble() - 0.5, 0, ThreadLocalRandom.current().nextDouble() - 0.5).normalize();
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

        dimension.addParticle(x, y, z, particle);
        dimension.addSound(x, y, z, sound);
    }
}
