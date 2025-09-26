package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author IWareQ | daoge_cmd
 */
@Slf4j
public class RespawnPacketProcessor extends PacketProcessor<RespawnPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayerImpl player, RespawnPacket packet, long receiveTime) {
        if (packet.getState() != RespawnPacket.State.CLIENT_READY) {
            log.warn("Respawn state must be CLIENT_READY, but got {}", packet.getState());
            return PacketSignal.HANDLED;
        }

        var respawnPacket = new RespawnPacket();
        // NOTICE: No need to set runtime entity id
        var spawnPoint = player.validateAndGetSpawnPoint();
        respawnPacket.setPosition(Vector3f.from(spawnPoint.x(), spawnPoint.y(), spawnPoint.z()));
        respawnPacket.setState(RespawnPacket.State.SERVER_READY);
        player.sendPacket(respawnPacket);

        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESPAWN;
    }
}
