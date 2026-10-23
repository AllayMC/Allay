package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SettingsCommandPacket;

/**
 * {@code SettingsCommandPacketProcessor} executes commands submitted through the
 * client settings interface as the
 * controlled player entity. The first command character is removed before
 * registry execution, matching the expected leading command prefix. This path
 * invokes the command registry directly without constructing a
 * {@code PlayerCommandEvent}.
 *
 * @author daoge_cmd
 */
public class SettingsCommandPacketProcessor extends PacketProcessor<SettingsCommandPacket> {
    /**
     * Executes the settings command after removing its first character. The
     * command must be nonnull and nonempty; this handler assumes the expected
     * prefix rather than checking it, and command permission handling remains
     * with the registry.
     */
    @Override
    public void handleSync(Player player, SettingsCommandPacket packet, long receiveTime) {
        // Settings commands include a leading prefix that the command registry
        // does not expect; remove exactly one character before executing the
        // remaining text.
        Registries.COMMANDS.execute(player.getControlledEntity(), packet.getCommand().substring(1));
    }

    /**
     * Returns the settings command packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SETTINGS_COMMAND;
    }
}
