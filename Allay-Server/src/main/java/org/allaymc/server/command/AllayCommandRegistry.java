package org.allaymc.server.command;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.server.command.defaults.GameTestCommand;
import org.allaymc.server.command.defaults.MeCommand;
import org.allaymc.server.command.defaults.StopCommand;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.allaymc.api.utils.AllayStringUtils.spiltCommandArgs;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class AllayCommandRegistry extends SimpleMappedRegistry<String, Command, Map<String, Command>> implements CommandRegistry {

    public AllayCommandRegistry() {
        super(null, i -> new HashMap<>());
    }

    @Override
    public void registerDefaultCommands() {
        register(new MeCommand());
        register(new GameTestCommand());
        register(new StopCommand());
    }

    @Override
    public void register(Command command) {
        register(command.getName(), command);
    }

    @Override
    public CommandResult execute(CommandSender sender, String cmd) {
        var spilt = new LinkedList<>(spiltCommandArgs(cmd));
        var cmdName = spilt.pop(); // Command name
        var command = get(cmdName);
        if (command == null) {
            sender.sendTr("§c%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, cmdName);
            return CommandResult.failed();
        }
        try {
            var result = command.execute(sender, spilt.toArray(new String[0]));
            sender.handleResult(result);
            return result;
        } catch (Throwable t) {
            t.printStackTrace();
            sender.sendTr("§c%" + TrKeys.M_COMMANDS_GENERIC_EXCEPTION);
            return CommandResult.failed();
        }
    }

    @Override
    public Command register(String s, Command command) {
        return super.register(s, command);
    }

    @Override
    public AvailableCommandsPacket encodeAvailableCommandsPacketFor(EntityPlayer player) {
        var pk = new AvailableCommandsPacket();
        for (var command : getContent().values()) {
            if (player.hasPerm(command.getPermission())) {
                pk.getCommands().add(command.buildNetworkDataFor(player));
            }
        }
        return pk;
    }
}
