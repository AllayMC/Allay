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

    public abstract void register(Command command);

    public abstract Command unregister(String name);

    /**
     * @param sender The command sender
     * @param cmd    The command, without the slash (/)
     *
     * @return The command execution result
     */
    public abstract CommandResult execute(CommandSender sender, String cmd);

    public abstract AvailableCommandsPacket encodeAvailableCommandsPacketFor(EntityPlayer player);
}
