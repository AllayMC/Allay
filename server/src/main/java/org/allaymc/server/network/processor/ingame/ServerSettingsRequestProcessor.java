package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsRequestPacket;

/**
 * @author daoge_cmd
 */
public class ServerSettingsRequestProcessor extends PacketProcessor<ServerSettingsRequestPacket> {

    @Override
    public PacketSignal handleAsync(Player player, ServerSettingsRequestPacket packet, long receiveTime) {
        var allayPlayer = (AllayPlayer) player;
        // Geyser: fixes https://bugs.mojang.com/browse/MCPE-94012 because of the delay
        Server.getInstance().getScheduler().scheduleDelayed(Server.getInstance(), () -> {
            var serverSettingForm = player.getServerSettingForm();
            if (serverSettingForm == null) {
                return false;
            }

            allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeServerSettingsResponse(
                    serverSettingForm.left(),
                    serverSettingForm.right().toJson()
            ));
            return true;
        }, 20);
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVER_SETTINGS_REQUEST;
    }
}
