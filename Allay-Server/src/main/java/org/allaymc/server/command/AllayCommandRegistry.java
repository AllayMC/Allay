package org.allaymc.server.command;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.server.command.CommandExecuteEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.perm.DefaultPermissions;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.command.defaults.*;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

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
        register(new TeleportCommand());
        register(new WorldCommand());
        register(new OpCommand());
        register(new TestforCommand());
        register(new TagCommand());
        register(new BanCommand());
        register(new BanIPCommand());
        register(new UnbanCommand());
        register(new UnbanIPCommand());
        register(new WhitelistCommand());
        register(new ScoreboardCommand());
        register(new TimeCommand());
    }

    @Override
    public void register(Command command) {
        register(command.getName(), command);
        command.getPermissions().forEach(DefaultPermissions.OPERATOR::addPerm);
    }

    @Override
    public Command unregister(String name) {
        var cmd = getContent().remove(name);
        if (cmd != null) {
            cmd.getPermissions().forEach(DefaultPermissions.OPERATOR::removePerm);
        }
        return cmd;
    }

    @Override
    public CommandResult execute(CommandSender sender, String cmd) {
        var event = new CommandExecuteEvent(sender, cmd);
        Server.getInstance().getEventBus().callEvent(event);
        if (event.isCancelled()) return CommandResult.fail();
        var spilt = spiltCommandArgs(cmd);
        var cmdName = spilt.pop(); // Command name
        var command = this.findCommand(cmdName);
        if (command == null) {
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, cmdName);
            return CommandResult.fail();
        }
        if (!sender.hasPerm(command.getPermissions())) {
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, cmdName);
            return CommandResult.fail();
        }
        try {
            var result = command.execute(sender, spilt.toArray(Utils.EMPTY_STRING_ARRAY));
            sender.handleResult(result);
            return result;
        } catch (Throwable t) {
            log.error("Error while execute command " + cmdName, t);
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_EXCEPTION);
            return CommandResult.fail();
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
