package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsRequestPacket;

/**
 * {@code ServerSettingsRequestProcessor} schedules a delayed response to a
 * client server-settings request. The
 * scheduled task reads the player's settings form when it runs, so the response
 * uses the form present after the delay rather than capturing one at receipt. A
 * missing form produces no response.
 *
 * @author daoge_cmd
 */
public class ServerSettingsRequestProcessor extends PacketProcessor<ServerSettingsRequestPacket> {

    /**
     * Schedules a server-owned task twenty ticks later and consumes the request
     * asynchronously. The player must be an {@code AllayPlayer}; the task sends its
     * then-current settings form when present and performs no explicit
     * connection recheck or retry here.
     */
    @Override
    public PacketSignal handleAsync(Player player, ServerSettingsRequestPacket packet, long receiveTime) {
        var allayPlayer = (AllayPlayer) player;
        // Defer the response by twenty server ticks and read the form inside
        // the task, allowing settings installed during the delay to become the
        // response.
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

    /**
     * Returns the server settings request packet type handled by this
     * processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVER_SETTINGS_REQUEST;
    }
}
