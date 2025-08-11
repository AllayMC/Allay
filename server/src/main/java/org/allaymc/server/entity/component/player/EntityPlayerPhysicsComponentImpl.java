package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityPhysicsComponentImpl;
import org.allaymc.server.entity.component.event.CPlayerGameTypeChangeEvent;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.SetEntityMotionPacket;
import org.joml.Vector3d;
import org.joml.Vector3dc;

/**
 * @author daoge_cmd
 */
public class EntityPlayerPhysicsComponentImpl extends EntityPhysicsComponentImpl {

    @Dependency
    protected EntityPlayerNetworkComponent networkComponent;

    @EventHandler
    protected void onGameTypeChange(CPlayerGameTypeChangeEvent event) {
        this.fallDistance = 0;
        setHasGravity(event.getGameType() != GameType.SPECTATOR);
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
        // For player, motion effect is calculated by the client rather than the server
        // We only need to send SetEntityMotionPacket to client when
        // we want to apply motion on a player
        var packet = new SetEntityMotionPacket();
        packet.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
        packet.setRuntimeEntityId(thisEntity.getRuntimeId());
        networkComponent.sendPacket(packet);
    }
}
