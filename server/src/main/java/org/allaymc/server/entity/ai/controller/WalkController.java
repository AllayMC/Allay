package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.Vector3d;

/**
 * Ground walking movement controller. Consumes route nodes to move the entity
 * toward its move target step by step.
 *
 * @author daoge_cmd
 */
public class WalkController implements Controller {

    @Override
    public boolean control(EntityIntelligent entity) {
        // When the previous direction segment is reached, consume the next route node
        if (entity.shouldUpdateMoveDirection()) {
            var routeFinder = entity.getBehaviorGroup().getRouteFinder();
            if (routeFinder != null && routeFinder.hasNext()) {
                var currentPos = entity.getLocation();
                var nextNode = routeFinder.next();
                entity.setMoveDirectionStart(new Vector3d(currentPos.x(), currentPos.y(), currentPos.z()));
                entity.setMoveDirectionEnd(nextNode.getVector());
                entity.setShouldUpdateMoveDirection(false);
            } else {
                // No more nodes; clear direction
                entity.setMoveDirectionStart(null);
                entity.setMoveDirectionEnd(null);
                entity.setShouldUpdateMoveDirection(false);
                return false;
            }
        }

        if (!entity.hasMoveDirection()) {
            return false;
        }

        var start = entity.getMoveDirectionStart();
        var end = entity.getMoveDirectionEnd();
        if (start == null || end == null) {
            return false;
        }

        var loc = entity.getLocation();
        double dx = end.x() - loc.x();
        double dz = end.z() - loc.z();
        double horizontalDistSq = dx * dx + dz * dz;

        // Reached current node
        if (horizontalDistSq < 0.25) {
            entity.setShouldUpdateMoveDirection(true);
            return true;
        }

        double horizontalDist = Math.sqrt(horizontalDistSq);
        float speed = entity.getMovementSpeed();
        double motionX = (dx / horizontalDist) * speed;
        double motionZ = (dz / horizontalDist) * speed;
        double motionY = end.y() > loc.y() + 0.1 ? 0.42 : 0; // simple jump for step-up

        entity.setMotion(motionX, motionY != 0 ? motionY : entity.getMotion().y(), motionZ);

        return true;
    }
}
