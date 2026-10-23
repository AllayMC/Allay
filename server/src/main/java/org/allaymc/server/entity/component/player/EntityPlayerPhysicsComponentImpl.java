package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntitySetMotionEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.event.CPlayerGameModeChangeEvent;
import org.allaymc.server.entity.component.humanlike.EntityHumanPhysicsComponentImpl;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * {@code EntityPlayerPhysicsComponentImpl} connects player movement to either a
 * client controller or the inherited humanlike simulation. Actual players
 * receive requested motion through their controller, while fake players use
 * server motion. Client movement processing separately records observed
 * velocity through {@code setMotionValueOnly}, without sending that observation
 * back as a new motion request.
 *
 * <p>
 * Server collision and liquid effects are enabled only for fake players in
 * survival or adventure mode. Game-mode changes clear accumulated fall distance
 * and disable gravity for spectators. Fall tracking also gives flying priority
 * over gliding: flying resets distance to zero, gentle gliding selects one
 * block, and other movement delegates to the inherited environmental reset
 * rules.
 *
 * @author daoge_cmd
 */
public class EntityPlayerPhysicsComponentImpl extends EntityHumanPhysicsComponentImpl {

    /**
     * Player owning controller and movement-mode decisions.
     */
    @ComponentObject
    protected EntityPlayer thisPlayer;

    /**
     * Clears accumulated fall distance and updates gravity for the selected
     * mode. Spectator mode disables gravity, while every other mode enables it
     * through the inherited setter, which also broadcasts the entity state.
     */
    @EventHandler
    protected void onGameModeChange(CPlayerGameModeChangeEvent event) {
        this.fallDistance = 0;
        setHasGravity(event.getGameMode() != GameMode.SPECTATOR);
    }

    /**
     * Returns whether this player lacks a client controller and uses server
     * movement. Actual players provide their movement through client input,
     * while fake players continue through the inherited server physics
     * calculation.
     */
    @Override
    public boolean computeMovementServerSide() {
        return !thisPlayer.isActualPlayer();
    }

    /**
     * Returns whether server physics should apply entity-collision pushes for
     * this player. The effect is enabled only when the player has no client
     * controller and its mode is {@code SURVIVAL} or {@code ADVENTURE}.
     */
    @Override
    public boolean computeEntityCollisionMotion() {
        var gameMode = thisPlayer.getGameMode();
        return computeMovementServerSide() && (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE);
    }

    /**
     * Returns whether server physics should apply escape motion from
     * intersecting blocks for this player. The effect is enabled only when the
     * player has no client controller and its mode is {@code SURVIVAL} or
     * {@code ADVENTURE}.
     */
    @Override
    public boolean computeBlockCollisionMotion() {
        var gameMode = thisPlayer.getGameMode();
        return computeMovementServerSide() && (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE);
    }

    /**
     * Returns whether server physics should apply liquid-current motion for
     * this player. The effect is enabled only when the player has no client
     * controller and its mode is {@code SURVIVAL} or {@code ADVENTURE}.
     */
    @Override
    public boolean computeLiquidMotion() {
        var gameMode = thisPlayer.getGameMode();
        return computeMovementServerSide() && (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE);
    }

    /**
     * Returns whether server physics should apply liquid buoyancy and drag for
     * this player. The effect is enabled only when the player has no client
     * controller and its mode is {@code SURVIVAL} or {@code ADVENTURE}.
     */
    @Override
    public boolean computeLiquidPhysics() {
        var gameMode = thisPlayer.getGameMode();
        return computeMovementServerSide() && (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE);
    }

    /**
     * Records an observed velocity and retains the preceding stored velocity as
     * history. The physics engine uses this path for client movement; it copies
     * {@code motion} without an event, coordinate validation, or a controller
     * motion message.
     */
    public void setMotionValueOnly(Vector3dc motion) {
        this.lastMotion = this.motion;
        this.motion = new Vector3d(motion);
    }

    /**
     * Requests player motion after the cancellable motion event accepts it.
     * Actual players receive the event's vector through their controller
     * without changing stored velocity here; fake players delegate to inherited
     * validation and storage. An input containing {@code NaN} throws
     * {@code IllegalArgumentException} before dispatch.
     */
    @Override
    public boolean setMotion(Vector3dc motion) {
        if (thisPlayer.isActualPlayer()) {
            if (MathUtils.hasNaN(motion)) {
                throw new IllegalArgumentException("Trying to set the motion of player " + thisPlayer.getDisplayName() + " to a new motion which contains NaN: " + motion);
            }

            var event = new EntitySetMotionEvent(thisEntity, motion);
            if (event.call()) {
                // Client-controlled motion is a request to the controller. Observed
                // velocity is stored separately when the physics engine processes client movement.
                thisPlayer.getController().setMotion(event.getMotion());
                return true;
            }

            return false;
        } else {
            return super.setMotion(motion);
        }
    }

    /**
     * Updates fall tracking using flight state before applying environmental
     * reset rules. Flying selects zero distance and returns immediately.
     * Otherwise, gliding with an absolute vertical speed below {@code 0.5}
     * blocks per tick selects one block of fall distance instead of invoking
     * the inherited block and liquid checks.
     *
     * <p>
     * Every other case delegates with the supplied {@code location}. The
     * priority matters when movement flags overlap: flying wins over gliding,
     * and gentle gliding bypasses environmental reset behavior for this update.
     */
    @Override
    protected void tryResetFallDistance(Location3dc location) {
        if (thisPlayer.isFlying()) {
            this.fallDistance = 0;
            return;
        }

        // Gentle gliding retains one block of fall distance; this path bypasses
        // the inherited environmental reset checks until vertical speed leaves the threshold.
        if (thisPlayer.isGliding() &&
            Math.abs(this.motion.y()) < 0.5) {
            this.fallDistance = 1;
        } else {
            super.tryResetFallDistance(location);
        }
    }
}
