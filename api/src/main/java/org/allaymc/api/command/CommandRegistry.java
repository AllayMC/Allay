package org.allaymc.api.command;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public abstract class CommandRegistry extends SimpleMappedRegistry<String, Command> {
    protected <I> CommandRegistry(I input, RegistryLoader<I, Map<String, Command>> registryLoader) {
        super(input, registryLoader);
    }

    /**
     * Method to register a command.
     *
     * @param command The command to register.
     */
    public abstract void register(Command command);

    /**
     * Method to unregister a command.
     *
     * @param name The name of the command to unregister.
     *
     * @return The unregistered command.
     */
    public abstract Command unregister(String name);

    /**
     * Method to execute a command.
     *
     * @param sender The command sender.
     * @param cmd    The command, without the slash (/).
     *
     * @return The command execution result.
     */
    public abstract CommandResult execute(CommandSender sender, String cmd);

    /**
     * Method to encode an AvailableCommandsPacket for a given player.
     *
     * @param player The player to encode the packet for.
     *
     * @return The encoded AvailableCommandsPacket.
     */
    public abstract AvailableCommandsPacket encodeAvailableCommandsPacketFor(EntityPlayer player);
}
