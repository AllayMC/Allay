package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.primitives.AABBd;

/**
 * Ground walking movement controller. Walks the entity toward its current
 * move direction.
 *
 * @author daoge_cmd
 */
public class WalkController implements Controller {

    protected static final int JUMP_COOL_DOWN = 10;

    protected int currentJumpCoolDown = JUMP_COOL_DOWN;

    @Override
    public boolean control(EntityIntelligent entity) {
        currentJumpCoolDown++;

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

        // Clamp speed if very close to avoid overshooting
        double effectiveSpeed = Math.min(speed, horizontalDist);

        // Add delta so resulting horizontal motion equals exactly effectiveSpeed
        double factor = effectiveSpeed / horizontalDist;
        double targetMx = dx * factor;
        double targetMz = dz * factor;

        // Jump logic:
        // Condition A: target waypoint is above step height and entity is horizontally close
        // Condition B: the next horizontal movement would collide while chasing a higher waypoint
        // Condition C: entity is stuck inside a block's collision shape at feet level
        double motionY = 0;
        if (entity.isOnGround() && currentJumpCoolDown >= JUMP_COOL_DOWN) {
            double dy = end.y() - loc.y();
            double entityWidth = entity.getAABB().maxX() - entity.getAABB().minX();
            // Use 2.25 as minimum threshold (vs vanilla's 1.0) because Allay's
            // pathfinder includes diagonal neighbors, where horizontal distance can reach ~√2
            boolean shouldJump = dy > entity.getStepHeight()
                    && horizontalDistSq < Math.max(2.25, entityWidth);

            if (!shouldJump) {
                shouldJump = dy > 0 && collidesBlocks(entity, targetMx, targetMz);
            }

            if (!shouldJump) {
                int blockX = (int) Math.floor(loc.x());
                int blockY = (int) Math.floor(loc.y());
                int blockZ = (int) Math.floor(loc.z());
                var blockState = entity.getDimension().getBlockState(blockX, blockY, blockZ);
                var blockStateData = blockState.getBlockStateData();
                if (blockStateData.hasCollision()) {
                    shouldJump = loc.y() < blockStateData.collisionShape().unionAABB().maxY() + blockY;
                }
            }

            if (shouldJump) {
                motionY = 0.55;
                currentJumpCoolDown = 0;
            }
        }
        entity.addMotion(targetMx - motion.x(), motionY, targetMz - motion.z());

        return true;
    }

    protected boolean collidesBlocks(EntityIntelligent entity, double dx, double dz) {
        var aabb = new AABBd(entity.getOffsetAABB()).translate(dx, 0, dz);
        return entity.getDimension().getCollidingBlockStates(aabb) != null;
    }
}
