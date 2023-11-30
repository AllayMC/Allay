package org.allaymc.server.command;

import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.exceptions.*;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.execution.CommandResult;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.meta.SimpleCommandMeta;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.AllayCommandRegistrationHandler;
import org.allaymc.api.command.CommandManager;
import org.allaymc.api.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;

@Slf4j
public class AllayCommandManager extends CommandManager {

    private final AnnotationParser<CommandSender> annotationParser;

    public AllayCommandManager() {
        super(CommandExecutionCoordinator.simpleCoordinator(), new AllayCommandRegistrationHandler());

        Function<ParserParameters, CommandMeta> commandMetaFunction = parameters ->
                CommandMeta.simple()
                        // This will allow you to decorate commands with descriptions
                        .with(CommandMeta.DESCRIPTION, parameters.get(StandardParameters.DESCRIPTION, "No description"))
                        .build();

        this.annotationParser = new AnnotationParser<>(this, CommandSender.class, commandMetaFunction);
    }

    public void init() {
        registerCommand(new GameModeCommand());
        registerCommand(new StopCommand());

        // debug
        commands().forEach(c -> {
            c.getArguments().forEach(arg -> log.info(arg.toString()));
            c.getComponents().forEach(arg -> log.info(arg.toString()));
            log.info(c.toString());
        });
    }

    @Override
    public @NonNull CompletableFuture<CommandResult<CommandSender>> executeCommand(@NonNull CommandSender sender, @NonNull String input) {
        return super.executeCommand(sender, input).whenComplete((commandResult, throwable) -> {
            if (throwable == null) return;
            if (throwable instanceof CompletionException) throwable = throwable.getCause();

            switch (throwable) {
                case InvalidSyntaxException exception ->
                        this.handleException(sender, InvalidSyntaxException.class, exception, (s, e) ->
                                sender.error("Invalid Command Syntax. Correct command syntax is: /" + exception.getCorrectSyntax())
                        );
                case InvalidCommandSenderException exception ->
                        this.handleException(sender, InvalidCommandSenderException.class, exception, (s, e) ->
                                sender.error(exception.getMessage())
                        );
                case NoPermissionException exception ->
                        this.handleException(sender, NoPermissionException.class, exception, (s, e) ->
                                sender.error(
                                        "I'm sorry, but you do not have permission to perform this command. " +
                                        "Please contact the server administrators if you believe that this is in error."
                                )
                        );
                case NoSuchCommandException exception ->
                        this.handleException(sender, NoSuchCommandException.class, exception, (s, e) ->
                                sender.error("Unknown command. Type \"/help\" for help.")
                        );
                case ArgumentParseException exception ->
                        this.handleException(sender, ArgumentParseException.class, exception, (s, e) ->
                                sender.reply("Invalid Command Argument: " + exception.getCause().getMessage())
                        );
                case null, default ->
                        sender.error("An internal error occurred while attempting to perform this command.");
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
            exception.printStackTrace();
        }
    }
}
