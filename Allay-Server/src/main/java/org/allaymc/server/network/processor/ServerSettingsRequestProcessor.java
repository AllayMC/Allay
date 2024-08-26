package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsResponsePacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
public class ServerSettingsRequestProcessor extends PacketProcessor<ServerSettingsRequestPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, ServerSettingsRequestPacket packet, long receiveTime) {
        // Geyser: fixes https://bugs.mojang.com/browse/MCPE-94012 because of the delay
        player.getWorld().getScheduler().scheduleDelayed(Server.getInstance(), () -> {
            player.getServerSettingForms().forEach((id, form) -> {
                var pk = new ServerSettingsResponsePacket();
                pk.setFormId(id);
                pk.setFormData(form.toJson());
                player.sendPacket(pk);
            });
            return true;
        }, 20);
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SERVER_SETTINGS_REQUEST;
    }
}
