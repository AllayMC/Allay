package org.allaymc.server.command;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class AllayCommandRegistry extends SimpleMappedRegistry<String, Command, Map<String, Command>> implements CommandRegistry {

    public AllayCommandRegistry() {
        super(null, i -> new HashMap<>());
    }

    public void registerDefaultCommands() {
        // TODO
    }

    protected AvailableCommandsPacket packetCache = null;

    @Override
    public Command register(String s, Command command) {
        packetCache = null;
        return super.register(s, command);
    }

    @Override
    public AvailableCommandsPacket getAvailableCommandsPacket() {
        if (packetCache == null) {
            packetCache = encodeAvailableCommandsPacket();
        }
        return packetCache;
    }

    protected AvailableCommandsPacket encodeAvailableCommandsPacket() {
        var pk = new AvailableCommandsPacket();
        for (var command : getContent().values()) {
            pk.getCommands().add(command.toNetworkData());
        }
        return pk;
    }
}
