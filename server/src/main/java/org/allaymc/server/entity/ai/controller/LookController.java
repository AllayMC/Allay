package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.component.EntityHeadYawComponent;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.joml.Vector3d;

/**
 * Controls entity rotation: body yaw from movement direction,
 * head yaw and pitch from the look target.
 * <p>
 * If the entity implements {@link EntityHeadYawComponent}, head yaw is
 * set independently from body yaw. Otherwise, head yaw equals body yaw.
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
        var loc = entity.getLocation();
        var lookTarget = entity.getLookTarget();
        double bodyYaw = loc.yaw();
        double pitch = loc.pitch();

        if (lookAtRoute && entity.hasMoveDirection()) {
            var end = entity.getMoveDirectionEnd();
            if (end != null) {
                var routeDirection = new Vector3d(end.x() - loc.x(), end.y() - loc.y(), end.z() - loc.z());
                bodyYaw = MathUtils.getYawFromVector(routeDirection);
                if (!lookAtTarget) {
                    if (entity instanceof EntityHeadYawComponent c) c.setHeadYaw(bodyYaw);
                    if (entity.isPitchEnabled()) pitch = MathUtils.getPitchFromVector(routeDirection);
                }
            }
        }

        if (lookAtTarget && lookTarget != null) {
            // Direction from entity's eyes to the look target
            // (executors already set lookTarget.y at the target's eye height)
            var toTarget = new Vector3d(lookTarget.x() - loc.x(), lookTarget.y() - (loc.y() + entity.getEyeHeight()), lookTarget.z() - loc.z());
            if (entity.isPitchEnabled()) pitch = MathUtils.getPitchFromVector(toTarget);
            if (entity instanceof EntityHeadYawComponent c) c.setHeadYaw(MathUtils.getYawFromVector(toTarget));
        }

        if (!entity.isPitchEnabled()) pitch = 0;

        entity.trySetLocation(new Location3d(loc.x(), loc.y(), loc.z(), pitch, bodyYaw, loc.dimension()));
        return true;
    }
}
