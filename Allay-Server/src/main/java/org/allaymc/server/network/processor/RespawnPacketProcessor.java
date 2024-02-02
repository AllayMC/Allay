package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;

public class RespawnPacketProcessor extends PacketProcessor<RespawnPacket> {

    @Override
    public void handleSync(EntityPlayer player, RespawnPacket packet) {
        if (packet.getState() != RespawnPacket.State.CLIENT_READY) return;

        var respawnPacket = new RespawnPacket();
        respawnPacket.setRuntimeEntityId(player.getUniqueId());
        var sp = player.getSpawnPoint();
        respawnPacket.setPosition(Vector3f.from(sp.x(), sp.y(), sp.z()));
        respawnPacket.setState(RespawnPacket.State.SERVER_READY);
        player.sendPacket(respawnPacket);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESPAWN;
    }
}
