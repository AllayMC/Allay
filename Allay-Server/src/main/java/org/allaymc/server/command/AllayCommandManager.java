package org.allaymc.server.command;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.CommandArgument;
import cloud.commandframework.arguments.StaticArgument;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.exceptions.*;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.execution.CommandResult;
import cloud.commandframework.internal.CommandRegistrationHandler;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.meta.SimpleCommandMeta;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandManager;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.i18n.TrKeys;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.cloudburstmc.protocol.bedrock.data.command.*;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;

@Slf4j
public class AllayCommandManager extends CommandManager {

    private final AnnotationParser<CommandSender> annotationParser;

    public AllayCommandManager() {
        super(CommandExecutionCoordinator.simpleCoordinator(), CommandRegistrationHandler.nullCommandRegistrationHandler());

        Function<ParserParameters, CommandMeta> commandMetaFunction = parameters ->
                CommandMeta.simple()
                        .with(CommandMeta.DESCRIPTION, parameters.get(StandardParameters.DESCRIPTION, "No description"))
                        .build();

        this.annotationParser = new AnnotationParser<>(this, CommandSender.class, commandMetaFunction);
    }

    public void init() {
        this.registerCommand(new GameModeCommand());
        this.registerCommand(new StopCommand());
    }

    @Override
    public @NonNull CompletableFuture<CommandResult<CommandSender>> executeCommand(@NonNull CommandSender sender, @NonNull String input) {
        return super.executeCommand(sender, input).whenComplete((commandResult, throwable) -> {
            if (throwable == null) return;
            if (throwable instanceof CompletionException) throwable = throwable.getCause();

            switch (throwable) {
                case InvalidSyntaxException exception ->
                        this.handleException(sender, InvalidSyntaxException.class, exception, (s, e) ->
                                sender.error(I18n.get().tr(TrKeys.A_COMMAND_INVALID_SYNTAX, exception.getCorrectSyntax()))
                        );
                case InvalidCommandSenderException exception ->
                        this.handleException(sender, InvalidCommandSenderException.class, exception, (s, e) ->
                                sender.error(exception.getMessage())
                        );
                case NoPermissionException exception ->
                        this.handleException(sender, NoPermissionException.class, exception, (s, e) ->
                                sender.error(I18n.get().tr(TrKeys.M_COMMANDS_TP_PERMISSION))
                        );
                case NoSuchCommandException exception ->
                        this.handleException(sender, NoSuchCommandException.class, exception, (s, e) ->
                                sender.error(I18n.get().tr(TrKeys.M_COMMANDS_GENERIC_UNKNOWN, input))
                        );
                case ArgumentParseException exception ->
                        this.handleException(sender, ArgumentParseException.class, exception, (s, e) ->
                                sender.reply(I18n.get().tr("§c" + TrKeys.M_COMMANDS_GENERIC_PARAMETER_INVALID, exception.getCause().getMessage()))
                        );
                case null, default ->
                        sender.error(I18n.get().tr(TrKeys.A_COMMAND_INTERNAL_ERROR));
            }
        });
    }

    @Override
    public boolean hasPermission(@NonNull CommandSender sender, @NonNull String permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public @NonNull CommandMeta createDefaultCommandMeta() {
        return SimpleCommandMeta.builder().build();
    }

    @Override
    public void registerCommand(@NotNull Object commandClass) {
        this.annotationParser.parse(commandClass);

        try {
            this.annotationParser.parseContainers();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public @NotNull AvailableCommandsPacket createPacketFor(@NotNull EntityPlayer player) {
        var packet = new AvailableCommandsPacket();

        for (var command : this.commands()) {
            var arguments = command.getArguments();
            var nameArgument = arguments.remove(0);

            List<String> nameAndAliases = new ArrayList<>();
            nameAndAliases.add(nameArgument.getName());
            if (nameArgument instanceof StaticArgument<?> argument)
                nameAndAliases.addAll(argument.getAlternativeAliases());

            packet.getCommands().add(new CommandData(
                    nameAndAliases.get(0),
                    command.getCommandMeta().get(CommandMeta.DESCRIPTION).get(),
                    Collections.emptySet(),
                    CommandPermission.ANY,
                    getAliases(nameAndAliases),
                    Collections.emptyList(),
                    getOverloadParams(arguments)
            ));
        }

        return packet;
    }

    private CommandEnumData getAliases(List<String> nameAndAliases) {
        Map<String, Set<CommandEnumConstraint>> values = new LinkedHashMap<>();
        for (var alias : nameAndAliases) values.put(alias, Collections.emptySet());
        return new CommandEnumData(nameAndAliases.get(0) + "Aliases", values, false);
    }

    private CommandOverloadData[] getOverloadParams(List<CommandArgument<@NonNull CommandSender, @NonNull ?>> arguments) {
        Set<CommandParamData> params = new HashSet<>();
        arguments.forEach(argument -> {
            var paramData = new CommandParamData();
            paramData.setName(argument.getName());
            paramData.setOptional(argument.isRequired());
            paramData.setType(CommandParam.TEXT); // TODO: check type
            params.add(paramData);
        });

        return new CommandOverloadData[]{new CommandOverloadData(false, params.toArray(new CommandParamData[0]))};
    }
}
