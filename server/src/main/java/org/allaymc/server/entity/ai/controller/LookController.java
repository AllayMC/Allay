package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.joml.Vector3d;

/**
 * Sets entity yaw and pitch based on look target and route direction.
 *
 * @author daoge_cmd
 */
public class LookController implements Controller {

    protected final boolean lookAtTarget;
    protected final boolean lookAtRoute;

    public LookController(boolean lookAtTarget, boolean lookAtRoute) {
        this.lookAtTarget = lookAtTarget;
        this.lookAtRoute = lookAtRoute;
    }

    @Override
    public boolean control(EntityIntelligent entity) {
        boolean applied = false;
        var loc = entity.getLocation();

        if (lookAtRoute && entity.hasMoveDirection()) {
            var start = entity.getMoveDirectionStart();
            var end = entity.getMoveDirectionEnd();
            if (start != null && end != null) {
                var direction = new Vector3d(end).sub(start);
                double yaw = MathUtils.getYawFromVector(direction);
                var newLoc = new Location3d(loc.x(), loc.y(), loc.z(), loc.pitch(), yaw, loc.dimension());
                entity.trySetLocation(newLoc);
                applied = true;
            }
        }

        if (lookAtTarget) {
            var lookTarget = entity.getLookTarget();
            if (lookTarget != null) {
                var direction = new Vector3d(
                        lookTarget.x() - loc.x(),
                        lookTarget.y() - (loc.y() + entity.getEyeHeight()),
                        lookTarget.z() - loc.z()
                );
                double yaw = MathUtils.getYawFromVector(direction);
                double pitch = entity.isPitchEnabled() ? MathUtils.getPitchFromVector(direction) : loc.pitch();
                var newLoc = new Location3d(loc.x(), loc.y(), loc.z(), pitch, yaw, loc.dimension());
                entity.trySetLocation(newLoc);
                applied = true;
            }
        }

        return applied;
    }
}
