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
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.server.command.defaults.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.allaymc.api.utils.AllayStringUtils.splitCommandArgs;

/**
 * {@code AllayCommandRegistry} stores server commands and dispatches command text after the
 * execution event and permission checks. Built-in commands are installed during construction;
 * feedback follows the sender's world rule, with successful outputs broadcast through the
 * server channel and failures sent to the executing sender.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayCommandRegistry extends CommandRegistry {
    /**
     * Creates a mutable command registry and installs built-in commands immediately. Command
     * construction requires the relevant server services, and the game-test command is included
     * only when the active API reports a development build.
     */
    public AllayCommandRegistry() {
        super(null, $ -> new HashMap<>());
        registerDefaultCommands();
    }

    /**
     * Installs built-in commands, including the game-test command for development builds.
     */
    private void registerDefaultCommands() {
        register(new CameraShakeCommand());
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
        register(new HudCommand());
        register(new StructureCommand());
        register(new FillCommand());
        register(new FogCommand());
        register(new TitleCommand());
        register(new ParticleCommand());
        register(new PermissionCommand());
        register(new PlayAnimationCommand());
        register(new PlaySoundCommand());
        register(new StopSoundCommand());
        register(new TellCommand());
        register(new SayCommand());
        if (AllayAPI.getInstance().isDevBuild()) {
            register(new GameTestCommand());
        }
    }

    /**
     * Stores the supplied command under its primary name, replacing that mapping.
     */
    @Override
    public void register(Command command) {
        content.put(command.getName(), command);
    }

    /**
     * Returns the previous mapping under {@code name} and registers {@code command} under its own
     * primary name. The supplied lookup name does not determine the replacement key, so the
     * returned command can belong to another mapping.
     */
    @Override
    public Command register(String name, Command command) {
        var previous = get(name);
        register(command);
        return previous;
    }

    /**
     * Rejects consumer-based bulk registration for this command registry. Callers must register
     * individual commands through the command overloads; this operation always throws
     * {@code UnsupportedOperationException} and never invokes the supplied consumer.
     */
    @Override
    public void register(Consumer<Map<String, Command>> consumer) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the direct mapping under {@code name} and returns its command. A missing primary
     * name returns {@code null}; aliases are resolved separately and are not individual mappings
     * removed by this operation.
     */
    @Override
    public Command unregister(String name) {
        return getContent().remove(name);
    }

    /**
     * Executes command text after listeners can replace its sender or contents. Cancelled events,
     * empty input, unknown commands, and missing permissions return failure; command text excludes
     * the leading {@code /}.
     *
     * <p>
     * The sender's world controls command feedback. Successful contextual outputs are broadcast,
     * with a direct send when the sender is outside that channel; failed contextual outputs go
     * only to the sender. Execution or feedback exceptions are logged and converted to a failure
     * result after an error message is sent.
     */
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
            var permissions = result.context().getPermissions();
            var outputs = result.context().getOutputs().toArray(TrContainer[]::new);
            if (result.isSuccess()) {
                var messageChannel = Server.getInstance().getMessageChannel();
                messageChannel.broadcastCommandOutputs(sender, status, permissions, outputs);
                if (!messageChannel.hasReceiver(sender)) {
                    // This sender is outside the broadcast channel. Send its outputs directly so
                    // successful execution still provides feedback to the initiating sender.
                    sender.sendCommandOutputs(sender, status, permissions, outputs);
                }
            } else {
                // Failed command outputs belong to the executing sender; broadcasting them would also expose that failure to other receivers.
                sender.sendCommandOutputs(result.context().getSender(), status, permissions, outputs);
            }

            return result;
        } catch (Throwable t) {
            // Command execution and feedback share this boundary. Report their failure to the
            // sender and return a failed result so callers can continue processing later commands.
            log.error("Error while execute command {}", commandName, t);
            sender.sendTranslatable(TextFormat.RED + "%" + TrKeys.MC_COMMANDS_GENERIC_EXCEPTION);
            return CommandResult.fail();
        }
    }
}
