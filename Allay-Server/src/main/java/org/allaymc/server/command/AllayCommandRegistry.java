package org.allaymc.server.command;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.command.defaults.*;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.allaymc.api.utils.AllayStringUtils.spiltCommandArgs;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayCommandRegistry extends SimpleMappedRegistry<String, Command, Map<String, Command>> implements CommandRegistry {

    public AllayCommandRegistry() {
        super(null, i -> new ConcurrentHashMap<>());
    }

    @Override
    public void registerDefaultCommands() {
        register(new MeCommand());
        register(new GameTestCommand());
        register(new StopCommand());
        register(new GameModeCommand());
        register(new GameRuleCommand());
        register(new TpsCommand());
        register(new KickCommand());
        register(new PluginCommand());
        register(new StatusCommand());
        register(new VersionCommand());
    }

    @Override
    public void register(Command command) {
        register(command.getName(), command);
        command.getPermissions().forEach(DefaultPermissions.OPERATOR::addPerm);
    }

    @Override
    public CommandResult execute(CommandSender sender, String cmd) {
        var spilt = new LinkedList<>(spiltCommandArgs(cmd));
        var cmdName = spilt.pop(); // Command name
        var command = this.findCommand(cmdName);
        if (command == null) {
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, cmdName);
            return CommandResult.failed();
        }
        if (!sender.hasPerm(command.getPermissions())) {
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, cmdName);
            return CommandResult.failed();
        }
        try {
            var result = command.execute(sender, spilt.toArray(Utils.EMPTY_STRING_ARRAY));
            sender.handleResult(result);
            return result;
        } catch (Throwable t) {
            log.error("Error while execute command " + cmdName, t);
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_EXCEPTION);
            return CommandResult.failed();
        }
    }

    protected Command findCommand(String nameOrAlias) {
        var result = this.get(nameOrAlias);
        if (result != null) return result;

        return this.getContent().values().stream()
                .filter(command -> command.getAliases().contains(nameOrAlias))
                .findFirst().orElse(null);
    }

    @Override
    public AvailableCommandsPacket encodeAvailableCommandsPacketFor(EntityPlayer player) {
        var pk = new AvailableCommandsPacket();
        for (var command : getContent().values()) {
            if (player.hasPerm(command.getPermissions())) {
                pk.getCommands().add(command.buildNetworkDataFor(player));
            }
        }
        return pk;
    }
}
