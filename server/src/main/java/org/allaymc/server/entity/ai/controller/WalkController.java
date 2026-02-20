package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;

/**
 * Ground walking movement controller. Walks the entity toward its current
 * move direction.
 *
 * @author daoge_cmd
 */
public class WalkController implements Controller {

    @Override
    public boolean control(EntityIntelligent entity) {
        if (!entity.hasMoveDirection()) {
            return false;
        }

        var end = entity.getMoveDirectionEnd();
        if (end == null) {
            return false;
        }

        var loc = entity.getLocation();
        float speed = entity.getMovementSpeed();

        // If current horizontal motion exceeds threshold (e.g. knockback), don't override
        var motion = entity.getMotion();
        if (motion.x() * motion.x() + motion.z() * motion.z() > speed * speed * 0.4756) {
            return false;
        }

        double dx = end.x() - loc.x();
        double dz = end.z() - loc.z();
        double horizontalDistSq = dx * dx + dz * dz;
        double horizontalDist = Math.sqrt(horizontalDistSq);

        // If the entity is already at (or extremely close to) the waypoint,
        // skip this tick — updateRoute() will advance on the next tick
        if (horizontalDist < 0.01) {
            return false;
        }

        // Jump logic (Paper-inspired): only jump when on ground and horizontally close to obstacle
        double dy = end.y() - loc.y();
        double motionY = (entity.isOnGround() && dy > entity.getStepHeight() && horizontalDistSq < 1.0) ? 0.55 : 0;

        // Clamp speed if very close to avoid overshooting
        double effectiveSpeed = Math.min(speed, horizontalDist);

        // Add delta so resulting horizontal motion equals exactly effectiveSpeed
        double factor = effectiveSpeed / horizontalDist;
        double targetMx = dx * factor;
        double targetMz = dz * factor;
        entity.addMotion(targetMx - motion.x(), motionY, targetMz - motion.z());

        return true;
    }
}
