package org.allaymc.api.command;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.registry.MappedRegistry;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandRegistry extends MappedRegistry<String, Command, Map<String, Command>> {

    @ApiStatus.Internal
    AvailableCommandsPacket encodeAvailableCommandsPacketFor(EntityPlayer player);

    @ApiStatus.Internal
    void registerDefaultCommands();

    void register(Command command);

    Command unregister(String name);

    /**
     * @param sender The command sender
     * @param cmd    The command, without the slash (/)
     *
     * @return The command execution result
     */
    CommandResult execute(CommandSender sender, String cmd);
}
