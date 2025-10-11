package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.component.EntityFireworksRocketPhysicsComponent;
import org.allaymc.server.component.annotation.Dependency;
import org.joml.Vector3d;

/**
 * @author daoge_cmd
 */
public class EntityFireworksRocketPhysicsComponentImpl extends EntityPhysicsComponentImpl implements EntityFireworksRocketPhysicsComponent {

    @Dependency
    protected EntityFireworksRocketBaseComponent fireworksRocketBaseComponent;

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
        var attachedPlayer = fireworksRocketBaseComponent.getAttachedPlayer();
        if (attachedPlayer == null) {
            return new Vector3d(
                    this.motion.x * this.sidewaysMotionMultiplier,
                    this.motion.y + this.upwardsMotion,
                    this.motion.z * this.sidewaysMotionMultiplier
            );
        } else {
            var currentPos = this.fireworksRocketBaseComponent.getLocation();
            var targetPos = attachedPlayer.getLocation();
            return new Vector3d(
                    targetPos.x() - currentPos.x(),
                    targetPos.y() - currentPos.y(),
                    targetPos.z() - currentPos.z()
            );
        }
    }

    @Override
    public boolean computeEntityCollisionMotion() {
        return false;
    }
}
