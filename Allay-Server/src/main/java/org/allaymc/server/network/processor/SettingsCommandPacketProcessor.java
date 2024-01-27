package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SettingsCommandPacket;

/**
 * Allay Project 2024/1/28
 *
 * @author daoge_cmd
 */
public class SettingsCommandPacketProcessor extends DataPacketProcessor<SettingsCommandPacket> {
    @Override
    public void handle(EntityPlayer player, SettingsCommandPacket pk) {
        Server.getInstance().getCommandRegistry().execute(player, pk.getCommand().substring(1));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SETTINGS_COMMAND;
    }
}
