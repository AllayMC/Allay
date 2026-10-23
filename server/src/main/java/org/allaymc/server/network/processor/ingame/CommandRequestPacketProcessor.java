package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.eventbus.event.player.PlayerCommandEvent;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.CommandRequestPacket;

/**
 * {@code CommandRequestPacketProcessor} dispatches client command requests
 * through the cancellable player-command
 * event before registry execution. The expected leading command prefix is
 * removed before listeners see the text. Accepted events use the
 * listener-selected command, allowing plugins to replace it before permission
 * checks and execution in the registry.
 *
 * @author IWareQ
 */
public class CommandRequestPacketProcessor extends PacketProcessor<CommandRequestPacket> {
    /**
     * Removes the first command character and dispatches a {@code PlayerCommandEvent}
     * for the controlled entity. The command must be nonnull and nonempty;
     * event cancellation suppresses execution, while an accepted event supplies
     * its possibly replaced command to the registry.
     */
    @Override
    public void handleSync(Player player, CommandRequestPacket packet, long receiveTime) {
        // Listeners and the command registry receive unprefixed command text;
        // this path removes the expected leading slash without independently
        // validating it.
        var command = packet.getCommand().substring(1);

        var event = new PlayerCommandEvent(player.getControlledEntity(), command);
        // Execute the post-event command only after approval so a listener
        // replacement or cancellation affects the exact text passed to the
        // registry.
        if (event.call()) {
            Registries.COMMANDS.execute(player.getControlledEntity(), event.getCommand());
        }
    }

    /**
     * Returns the command request packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.COMMAND_REQUEST;
    }
}
