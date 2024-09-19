package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author IWareQ
 */
public class RespawnPacketProcessor extends PacketProcessor<RespawnPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, RespawnPacket packet, long receiveTime) {
        if (packet.getState() != RespawnPacket.State.CLIENT_READY) return PacketSignal.HANDLED;

        var respawnPacket = new RespawnPacket();
        // NOTICE: No need to set runtime entity id
        var sp = player.getSpawnPoint();
        respawnPacket.setPosition(Vector3f.from(sp.x(), sp.y(), sp.z()));
        respawnPacket.setState(RespawnPacket.State.SERVER_READY);
        player.sendPacket(respawnPacket);

        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESPAWN;
    }
}
