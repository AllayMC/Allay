package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.component.EntityHeadYawComponent;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3d;
import org.joml.Vector3d;

/**
 * {@code LookController} derives body yaw, head yaw, and pitch from route and
 * look-target memories. Route following changes body yaw, while target
 * following changes the separate head yaw only on entities implementing {@code
 * EntityHeadYawComponent}. An entity without that component receives no
 * independent target-facing yaw update here.
 *
 * <p>
 * The two configuration flags determine which source can replace each
 * orientation component. Route-derived head yaw and pitch apply only when
 * target following is disabled; target following uses the vector from the
 * entity's eye position to the supplied look point. Pitch-disabled entities end
 * with zero pitch regardless of either source. The controller preserves
 * position and dimension when requesting the resulting location.
 *
 * @author daoge_cmd
 */
public class LookController implements Controller {

    /**
     * Target-based head orientation flag.
     */
    protected final boolean lookAtTarget;
    /**
     * Route-based body orientation flag.
     */
    protected final boolean lookAtRoute;

    /**
     * Creates a controller with the supplied target and route orientation
     * flags.
     */
    public LookController(boolean lookAtTarget, boolean lookAtRoute) {
        this.lookAtTarget = lookAtTarget;
        this.lookAtRoute = lookAtRoute;
    }

    /**
     * Applies orientation from the enabled route and target sources. Route
     * orientation updates body yaw and can also update head yaw and pitch when
     * target following is disabled. A target point updates separate head yaw
     * only for an {@code EntityHeadYawComponent} entity.
     *
     * <p>
     * Both vectors use the entity's current location, but target pitch starts
     * at its eye height. Disabling pitch forces zero after source processing.
     * The method requests the resulting location and returns {@code true}
     * regardless of whether that request succeeds.
     */
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
            // Entity-following executors supply the target eye position. Subtract this
            // entity's eye height here so target pitch compares eyes rather than feet.
            var toTarget = new Vector3d(lookTarget.x() - loc.x(), lookTarget.y() - (loc.y() + entity.getEyeHeight()), lookTarget.z() - loc.z());
            if (entity.isPitchEnabled()) pitch = MathUtils.getPitchFromVector(toTarget);
            if (entity instanceof EntityHeadYawComponent c) c.setHeadYaw(MathUtils.getYawFromVector(toTarget));
        }

        if (!entity.isPitchEnabled()) pitch = 0;

        entity.trySetLocation(new Location3d(loc.x(), loc.y(), loc.z(), pitch, bodyYaw, loc.dimension()));
        return true;
    }
}
