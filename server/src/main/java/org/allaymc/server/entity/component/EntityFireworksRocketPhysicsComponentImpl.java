package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.component.EntityFireworksRocketPhysicsComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityTickEvent;
import org.joml.Vector3d;

/**
 * @author daoge_cmd
 */
public class EntityFireworksRocketPhysicsComponentImpl extends EntityPhysicsComponentImpl implements EntityFireworksRocketPhysicsComponent {

    @Dependency
    protected EntityFireworksRocketBaseComponent fireworkBaseComponent;

    @Getter
    @Setter
    protected double sidewaysMotionMultiplier, upwardsMotion;

    public EntityFireworksRocketPhysicsComponentImpl() {
        super();
        this.sidewaysMotionMultiplier = DEFAULT_SIDEWAYS_MOTION_MULTIPLIER;
        this.upwardsMotion = DEFAULT_UPWARDS_MOTION;
    }

    @Override
    public Vector3d updateMotion(boolean hasLiquidMotion) {
        if (!this.fireworkBaseComponent.isAttached()) {
            return new Vector3d(
                    this.motion.x * this.sidewaysMotionMultiplier,
                    this.motion.y + this.upwardsMotion,
                    this.motion.z * this.sidewaysMotionMultiplier
            );
        }

        // If the rocket is attached to a player, the firework rocket will be teleported to the player every
        // tick, so we do not need to update the motion of the rocket.
        return new Vector3d(0, 0, 0);
    }

    @Override
    public boolean computeEntityCollisionMotion() {
        return false;
    }

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
