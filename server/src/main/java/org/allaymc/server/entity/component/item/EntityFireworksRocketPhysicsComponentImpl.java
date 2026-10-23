package org.allaymc.server.entity.component.item;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.component.EntityFireworksRocketPhysicsComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;
import org.allaymc.server.entity.component.event.CEntityTickEvent;
import org.joml.Vector3d;

/**
 * {@code EntityFireworksRocketPhysicsComponentImpl} applies free-flight rocket
 * acceleration or follows an attached player. Free rockets scale horizontal
 * motion and add vertical acceleration each physics update, while attached
 * rockets report zero motion and teleport to the player during component
 * ticking.
 *
 * @author daoge_cmd
 */
public class EntityFireworksRocketPhysicsComponentImpl extends EntityPhysicsComponentImpl implements EntityFireworksRocketPhysicsComponent {

    /**
     * Injected rocket lifetime and attachment component.
     */
    @Dependency
    protected EntityFireworksRocketBaseComponent fireworkBaseComponent;

    /**
     * {@code sidewaysMotionMultiplier} scales horizontal motion each update,
     * while {@code upwardsMotion} adds vertical acceleration in blocks per
     * tick squared. Attachment bypasses both values until the rocket returns
     * to free flight.
     */
    @Getter
    @Setter
    protected double sidewaysMotionMultiplier, upwardsMotion;

    /**
     * Creates rocket physics using the declared horizontal multiplier and
     * upward acceleration defaults. These values govern free-flight updates,
     * while attached rockets use the attachment path regardless of the
     * configured acceleration.
     */
    public EntityFireworksRocketPhysicsComponentImpl() {
        super();
        this.sidewaysMotionMultiplier = DEFAULT_SIDEWAYS_MOTION_MULTIPLIER;
        this.upwardsMotion = DEFAULT_UPWARDS_MOTION;
    }

    /**
     * Returns a new motion vector for free flight or zero motion for
     * attachment. The method ignores liquid state and leaves the stored motion
     * unchanged until the physics engine assigns the result.
     */
    @Override
    public Vector3d updateMotion(LiquidState liquidState) {
        if (!this.fireworkBaseComponent.isAttached()) {
            return new Vector3d(
                    this.motion.x * this.sidewaysMotionMultiplier,
                    this.motion.y + this.upwardsMotion,
                    this.motion.z * this.sidewaysMotionMultiplier
            );
        }

        // Attachment ticking supplies the rocket location by teleport, so
        // free-flight acceleration must not create independent travel between those
        // updates.
        return new Vector3d(0, 0, 0);
    }

    /**
     * Returns {@code false} to suppress ordinary entity pushing for rockets.
     */
    @Override
    public boolean computeEntityCollisionMotion() {
        return false;
    }

    /**
     * Returns {@code false} to suppress ordinary stuck-block escape motion for
     * rockets.
     */
    @Override
    public boolean computeBlockCollisionMotion() {
        return false;
    }

    /**
     * Attempts to teleport an attached rocket to its retained player location
     * and emits a contrail particle afterward. Teleport rejection does not
     * suppress that particle, while a {@code null} attachment skips both
     * operations.
     */
    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        var attachedPlayer = this.fireworkBaseComponent.getAttachedPlayer();
        if (attachedPlayer == null) {
            return;
        }

        this.fireworkBaseComponent.teleport(attachedPlayer.getLocation());
        attachedPlayer.getDimension().addParticle(attachedPlayer.getLocation(), SimpleParticle.FIREWORK_CONTRAIL);
    }
}
