package org.allaymc.api.command;

import org.allaymc.api.registry.MappedRegistry;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.util.Map;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandRegistry extends MappedRegistry<String, Command, Map<String, Command>> {
    AvailableCommandsPacket getAvailableCommandsPacket();

    void registerDefaultCommands();
}
