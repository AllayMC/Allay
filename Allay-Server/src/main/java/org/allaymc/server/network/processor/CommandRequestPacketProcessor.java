package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerCommandEvent;
import org.allaymc.api.network.processor.PacketProcessor;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.CommandRequestPacket;

public class CommandRequestPacketProcessor extends PacketProcessor<CommandRequestPacket> {
    @Override
    public void handleSync(EntityPlayer player, CommandRequestPacket packet, long receiveTime) {
        // The packet returns `/command args`, this gets rid of the `/` at the start
        var command = packet.getCommand().substring(1);

        var event = new PlayerCommandEvent(player, command);
        event.call();
        if (event.isCancelled()) return;

        Registries.COMMANDS.execute(player, command);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.COMMAND_REQUEST;
    }
}
