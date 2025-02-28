package org.allaymc.server.registry;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.command.CommandExecuteEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.permission.DefaultPermissions;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.command.defaults.*;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

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
        if (AllayAPI.getInstance().isDevBuild()) {
            register(new GameTestCommand());
        }
    }

    @Override
    public void register(Command command) {
        content.put(command.getName(), command);
        command.getPermissions().forEach(DefaultPermissions.OPERATOR::addPermission);
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
            cmd.getPermissions().forEach(DefaultPermissions.OPERATOR::removePermission);
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
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, "");
            return CommandResult.fail();
        }

        var commandName = spilt.pop();
        var command = this.findCommand(commandName);
        if (command == null) {
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, commandName);
            return CommandResult.fail();
        }

        if (!sender.hasPermission(command.getPermissions())) {
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_UNKNOWN, commandName);
            return CommandResult.fail();
        }

        try {
            var result = command.execute(sender, spilt.toArray(Utils.EMPTY_STRING_ARRAY));
            sender.handleResult(result);
            return result;
        } catch (Throwable t) {
            log.error("Error while execute command {}", commandName, t);
            sender.sendTr(TextFormat.RED + "%" + TrKeys.M_COMMANDS_GENERIC_EXCEPTION);
            return CommandResult.fail();
        }
    }

    @Override
    public AvailableCommandsPacket encodeAvailableCommandsPacketFor(EntityPlayer player) {
        var pk = new AvailableCommandsPacket();
        getContent().values().stream()
                .filter(command -> !command.isServerSideOnly() && player.hasPermission(command.getPermissions()))
                .forEach(command -> pk.getCommands().add(command.buildNetworkDataFor(player)));
        return pk;
    }

    public Command findCommand(String nameOrAlias) {
        var result = this.get(nameOrAlias);
        if (result != null) return result;
        return this.getContent().values().stream()
                .filter(command -> command.getAliases().contains(nameOrAlias))
                .findFirst().orElse(null);
    }
}
