package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.component.player.EntityPlayerClientComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.component.annotation.Dependency;
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

    @Dependency
    protected EntityPlayerClientComponent clientComponent;

    @EventHandler
    protected void onGameModeChange(CPlayerGameModeChangeEvent event) {
        this.fallDistance = 0;
        setHasGravity(event.getGameMode() != GameMode.SPECTATOR);
    }

    @Override
    public boolean computeMovementServerSide() {
        return false;
    }

    public void setMotionValueOnly(Vector3dc motion) {
        this.lastMotion = this.motion;
        this.motion = new Vector3d(motion);
    }

    @Override
    public void setMotion(Vector3dc motion) {
        if (MathUtils.hasNaN(motion)) {
            throw new IllegalArgumentException("Trying to set the motion of player " + clientComponent.getOriginName() + " to a new motion which contains NaN: " + motion);
        }

        // For player, motion effect is calculated by the client rather than the server. We only
        // need to send SetEntityMotionPacket to client when we want to apply motion on a player
        var packet = new SetEntityMotionPacket();
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        packet.setRuntimeEntityId(thisEntity.getRuntimeId());
        clientComponent.sendPacket(packet);
    }
}
