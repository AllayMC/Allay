package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsResponsePacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class ServerSettingsRequestProcessor extends PacketProcessor<ServerSettingsRequestPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, ServerSettingsRequestPacket packet, long receiveTime) {
        // Geyser: fixes https://bugs.mojang.com/browse/MCPE-94012 because of the delay
        player.getWorld().getScheduler().scheduleDelayed(Server.getInstance(), () -> {
            var serverSettingForm = player.getServerSettingForm();
            if (serverSettingForm.right() == null) {
                return false;
            }

            var pk = new ServerSettingsResponsePacket();
            pk.setFormId(serverSettingForm.left());
            pk.setFormData(serverSettingForm.right().toJson());
            player.sendPacket(pk);
            return true;
        }, 20);
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVER_SETTINGS_REQUEST;
    }
}
