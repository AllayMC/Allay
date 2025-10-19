package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerRespawnEvent;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;

/**
 * @author IWareQ | daoge_cmd
 */
@Slf4j
public class RespawnPacketProcessor extends PacketProcessor<RespawnPacket> {

    @Override
    public void handleSync(EntityPlayer player, RespawnPacket packet, long receiveTime) {
        if (packet.getState() != RespawnPacket.State.CLIENT_READY) {
            log.warn("Respawn state must be CLIENT_READY, but got {}", packet.getState());
            return;
        }

        var event = new PlayerRespawnEvent(player);
        event.setRespawnLocation(player.validateAndGetSpawnPoint());
        event.call();
        var spawnPoint = event.getRespawnLocation();

        // NOTICE: No need to set runtime entity id
        var respawnPacket = new RespawnPacket();
        respawnPacket.setPosition(Vector3f.from(spawnPoint.x(), spawnPoint.y(), spawnPoint.z()));
        respawnPacket.setState(RespawnPacket.State.SERVER_READY);
        player.sendPacket(respawnPacket);

        // Respawn the player at the current dimension first, reset its data and then teleport to
        // the spawn point
        player.getDimension().addPlayer(player, () -> {
            resetData(player);
            player.teleport(spawnPoint);
        });
    }

    private void resetData(EntityPlayer player) {
        player.removeAllEffects();
        player.resetHealth();
        player.resetFoodData();
        player.extinguish();
        player.setAirSupplyTicks(player.getAirSupplyMaxTicks());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESPAWN;
    }
}
