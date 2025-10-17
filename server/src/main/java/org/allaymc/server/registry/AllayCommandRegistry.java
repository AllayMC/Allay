package org.allaymc.server.registry;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.event.command.CommandExecuteEvent;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.command.defaults.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.allaymc.api.utils.AllayStringUtils.splitCommandArgs;

/**
 * @author daoge_cmd
 */
@Slf4j
public class AllayCommandRegistry extends CommandRegistry {
    public AllayCommandRegistry() {
        super(null, $ -> new HashMap<>());
        registerDefaultCommands();
    }

    private void registerDefaultCommands() {
        register(new MeCommand());
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
        register(new EnchantCommand());
        register(new EffectCommand());
        register(new GiveCommand());
        register(new DifficultyCommand());
        register(new ListCommand());
        register(new SetWorldSpawnCommand());
        register(new GCCommand());
        register(new SpawnPointCommand());
        register(new DeOpCommand());
        register(new XpCommand());
        register(new AlwaysDayCommand());
        register(new SetBlockCommand());
        register(new WeatherCommand());
        register(new ClearCommand());
        register(new KillCommand());
        register(new SummonCommand());
        register(new SetMaxPlayersCommand());
        register(new ExecuteCommand());
        register(new HelpCommand());
        register(new StructureCommand());
        register(new FillCommand());
        register(new TitleCommand());
        register(new PermissionCommand());
        if (AllayAPI.getInstance().isDevBuild()) {
            register(new GameTestCommand());
        }
    }

    @Override
    public void register(Command command) {
        content.put(command.getName(), command);
        command.getPermissions().forEach(permission -> PermissionGroups.OPERATOR.addPermission(permission, null));
    }

    @Override
    public Command register(String name, Command command) {
        var previous = get(name);
        register(command);
        return previous;
    }

    @Override
    public void register(Consumer<Map<String, Command>> consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Command unregister(String name) {
        var cmd = getContent().remove(name);
        if (cmd != null) {
            cmd.getPermissions().forEach(permission -> PermissionGroups.OPERATOR.removePermission(permission, null));
        }
        return cmd;
    }

    @Override
    public CommandResult execute(CommandSender sender, String cmd) {
        var event = new CommandExecuteEvent(sender, cmd);
        if (!event.call()) return CommandResult.fail();

        sender = event.getCommandSender();
        cmd = event.getCommand();

        var spilt = splitCommandArgs(cmd);
        if (spilt.isEmpty()) {
            sender.sendTranslatable(TextFormat.RED + "%" + TrKeys.MC_COMMANDS_GENERIC_UNKNOWN, "");
            return CommandResult.fail();
        }

        var commandName = spilt.pop();
        var command = this.findCommand(commandName);
        if (command == null) {
            sender.sendTranslatable(TextFormat.RED + "%" + TrKeys.MC_COMMANDS_GENERIC_UNKNOWN, commandName);
            return CommandResult.fail();
        }

        if (!sender.hasPermissions(command.getPermissions())) {
            sender.sendTranslatable(TextFormat.RED + "%" + TrKeys.MC_COMMANDS_GENERIC_UNKNOWN, commandName);
            return CommandResult.fail();
        }

        try {
            var result = command.execute(sender, spilt.toArray(new String[0]));
            var sendCommandFeedback = sender.getCommandExecuteLocation().dimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK);
            if (result.context() == null || !sendCommandFeedback) {
                return result;
            }

            var status = result.status();
            var outputs = result.context().getOutputs().toArray(TrContainer[]::new);
            if (result.isSuccess()) {
                var messageChannel = Server.getInstance().getMessageChannel();
                messageChannel.broadcastCommandOutputs(sender, status, outputs);
                if (!messageChannel.hasReceiver(sender)) {
                    // The command sender used to execute this command is not registered to the message channel,
                    // but we still need to send the command outputs to itself. Let's do it manually
                    sender.sendCommandOutputs(sender, status, outputs);
                }
            } else {
                // If there is an error, only send the message to itself
                sender.sendCommandOutputs(result.context().getSender(), status, outputs);
            }

            return result;
        } catch (Throwable t) {
            log.error("Error while execute command {}", commandName, t);
            sender.sendTranslatable(TextFormat.RED + "%" + TrKeys.MC_COMMANDS_GENERIC_EXCEPTION);
            return CommandResult.fail();
        }
    }
}
