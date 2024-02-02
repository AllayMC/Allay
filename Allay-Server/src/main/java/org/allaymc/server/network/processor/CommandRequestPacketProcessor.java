package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.CommandRequestPacket;

public class CommandRequestPacketProcessor extends PacketProcessor<CommandRequestPacket> {

    @Override
    public void handleSync(EntityPlayer player, CommandRequestPacket packet) {
        // The packet returns `/command args`, this gets rid of the `/` at the start
        Server.getInstance().getCommandRegistry().execute(player, packet.getCommand().substring(1));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.COMMAND_REQUEST;
    }
}
