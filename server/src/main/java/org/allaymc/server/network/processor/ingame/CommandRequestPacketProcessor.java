package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.eventbus.event.player.PlayerCommandEvent;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.CommandRequestPacket;

/**
 * @author IWareQ
 */
public class CommandRequestPacketProcessor extends PacketProcessor<CommandRequestPacket> {
    @Override
    public void handleSync(EntityPlayerImpl player, CommandRequestPacket packet, long receiveTime) {
        // The packet returns `/command args`, this gets rid of the `/` at the start
        var command = packet.getCommand().substring(1);

        var event = new PlayerCommandEvent(player, command);
        if (event.call()) {
            Registries.COMMANDS.execute(player, event.getCommand());
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.COMMAND_REQUEST;
    }
}
