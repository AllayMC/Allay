package org.allaymc.server.entity.ai.controller;

import org.allaymc.api.entity.ai.controller.Controller;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.joml.primitives.AABBd;

/**
 * {@code WalkController} prepares horizontal walking requests along the current
 * movement segment and adds jump requests when ground obstacles require them.
 * The controller leaves sufficiently large existing horizontal motion
 * untouched, allowing movement already applied to the entity to continue rather
 * than immediately requesting the walking vector.
 *
 * <p>
 * Walking speed is capped by remaining horizontal distance to avoid passing the
 * endpoint. Jump eligibility combines elapsed cooldown, ground contact,
 * waypoint height, projected collision, and a feet-level collision check.
 * Choosing a jump resets the cooldown before the motion request; cancellation
 * does not restore that counter. Ticks without usable directions still advance
 * it. The behavior group owns waypoint advancement before control, while the
 * entity's motion event decides whether to accept the requested vector.
 *
 * @author daoge_cmd
 */
public class WalkController implements Controller {

    /**
     * {@code JUMP_COOL_DOWN} sets the minimum control-tick spacing between jump
     * impulses, preventing an eligible grounded entity from jumping again on
     * every tick.
     */
    protected static final int JUMP_COOL_DOWN = 10;

    /**
     * {@code currentJumpCoolDown} counts control calls since the last jump. Its
     * initial value permits an immediate jump, and every control call advances
     * it before direction checks.
     */
    protected int currentJumpCoolDown = JUMP_COOL_DOWN;

    /**
     * Requests motion toward the current endpoint unless directions are absent,
     * existing motion is already large, or horizontal distance is negligible.
     * The requested horizontal vector uses walking speed capped by remaining
     * distance; the vertical addition is nonzero only when a jump is chosen.
     *
     * <p>
     * Jump selection requires ground contact and elapsed cooldown. A raised
     * nearby waypoint, projected collision toward a higher point, or feet
     * inside a collision shape can trigger the jump. The method returns {@code
     * true} after calling {@code addMotion}, whose underlying {@code setMotion}
     * event can cancel or replace the request; skipped control ticks return
     * {@code false}.
     */
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

        // Preserve larger existing horizontal impulses, including knockback, instead of
        // replacing them immediately with the walking vector supplied by this controller.
        var motion = entity.getMotion();
        if (motion.x() * motion.x() + motion.z() * motion.z() > speed * speed * 0.4756) {
            return false;
        }

        double dx = end.x() - loc.x();
        double dz = end.z() - loc.z();
        double horizontalDistSq = dx * dx + dz * dz;
        double horizontalDist = Math.sqrt(horizontalDistSq);

        // Skip negligible horizontal segments to avoid dividing by their tiny length;
        // the behavior group owns waypoint advancement before later controller calls.
        if (horizontalDist < 0.01) {
            return false;
        }

        // Cap this step at the endpoint to prevent horizontal overshoot.
        double effectiveSpeed = Math.min(speed, horizontalDist);

        double factor = effectiveSpeed / horizontalDist;
        double targetMx = dx * factor;
        double targetMz = dz * factor;

        // Ground contact and cooldown gate all jump causes. The waypoint and
        // collision tests cover raised nodes, blocked approaches, and feet trapped
        // inside a collision shape without adding another impulse on every tick.
        double motionY = 0;
        if (entity.isOnGround() && currentJumpCoolDown >= JUMP_COOL_DOWN) {
            double dy = end.y() - loc.y();
            double entityWidth = entity.getAABB().maxX() - entity.getAABB().minX();
            // The squared-distance floor includes diagonal route neighbors, whose centers
            // can be farther apart horizontally than neighboring cardinal block centers.
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
                // This upward impulse supplements current vertical motion for obstacle clearance.
                motionY = 0.55;
                currentJumpCoolDown = 0;
            }
        }
        entity.addMotion(targetMx - motion.x(), motionY, targetMz - motion.z());

        return true;
    }

    /**
     * Returns whether the horizontally translated entity bounds intersect any
     * block collision shape.
     */
    protected boolean collidesBlocks(EntityIntelligent entity, double dx, double dz) {
        var aabb = new AABBd(entity.getOffsetAABB()).translate(dx, 0, dz);
        return entity.getDimension().getCollidingBlockStates(aabb) != null;
    }
}
