package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SettingsCommandPacket;

/**
 * @author daoge_cmd
 */
public class SettingsCommandPacketProcessor extends PacketProcessor<SettingsCommandPacket> {
    @Override
    public void handleSync(EntityPlayer player, SettingsCommandPacket packet, long receiveTime) {
        Registries.COMMANDS.execute(player, packet.getCommand().substring(1));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SETTINGS_COMMAND;
    }
}
