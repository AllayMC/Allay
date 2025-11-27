package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.entity.EntitySetMotionEvent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.EntityHumanPhysicsComponentImpl;
import org.allaymc.server.entity.component.event.CPlayerGameModeChangeEvent;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.SetEntityMotionPacket;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * @author daoge_cmd
 */
public class EntityPlayerPhysicsComponentImpl extends EntityHumanPhysicsComponentImpl {

    @ComponentObject
    protected EntityPlayer thisPlayer;

    @EventHandler
    protected void onGameModeChange(CPlayerGameModeChangeEvent event) {
        this.fallDistance = 0;
        setHasGravity(event.getGameMode() != GameMode.SPECTATOR);
    }

    @Override
    public boolean computeMovementServerSide() {
        return !thisPlayer.isActualPlayer();
    }

    @Override
    public boolean computeEntityCollisionMotion() {
        var gameMode = thisPlayer.getGameMode();
        return computeMovementServerSide() && (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE);
    }

    @Override
    public boolean computeBlockCollisionMotion() {
        var gameMode = thisPlayer.getGameMode();
        return computeMovementServerSide() && (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE);
    }

    @Override
    public boolean computeLiquidMotion() {
        var gameMode = thisPlayer.getGameMode();
        return computeMovementServerSide() && (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE);
    }

    public void setMotionValueOnly(Vector3dc motion) {
        this.lastMotion = this.motion;
        this.motion = new Vector3d(motion);
    }

    @Override
    public boolean setMotion(Vector3dc motion) {
        if (thisPlayer.isActualPlayer()) {
            if (MathUtils.hasNaN(motion)) {
                throw new IllegalArgumentException("Trying to set the motion of player " + thisPlayer.getDisplayName() + " to a new motion which contains NaN: " + motion);
            }

            var event = new EntitySetMotionEvent(thisEntity, motion);
            if (event.call()) {
                // For player, motion effect is calculated by the client rather than the server. We only
                // need to send SetEntityMotionPacket to the client when we want to apply motion on a player
                var packet = new SetEntityMotionPacket();
                var mot = event.getMotion();
                packet.setMotion(Vector3f.from(mot.x(), mot.y(), mot.z()));
                packet.setRuntimeEntityId(thisEntity.getRuntimeId());
                thisPlayer.getController().sendPacket(packet);
                return true;
            }

            return false;
        } else {
            return super.setMotion(motion);
        }
    }

    @Override
    protected void tryResetFallDistance(Location3dc location) {
        // Reset fall distance when the player is flying
        if (thisPlayer.isFlying()) {
            this.fallDistance = 0;
            return;
        }

        // Fall distance for the player will be reset to 1 if the player is gliding and the absolute value of the
        // vertical motion is smaller than 0.5
        if (thisPlayer.isGliding() &&
            Math.abs(this.motion.y()) < 0.5) {
            this.fallDistance = 1;
        } else {
            super.tryResetFallDistance(location);
        }
    }
}
