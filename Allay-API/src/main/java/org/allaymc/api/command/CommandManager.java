package org.allaymc.api.command;

import cloud.commandframework.CommandTree;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.internal.CommandRegistrationHandler;
import cloud.commandframework.meta.CommandMeta;
import org.allaymc.api.ApiInstanceHolder;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.function.Function;

public abstract class CommandManager extends cloud.commandframework.CommandManager<CommandSender> {

    public static final ApiInstanceHolder<CommandManager> INSTANCE = ApiInstanceHolder.of();

    private final AnnotationParser<CommandSender> annotationParser;

    protected CommandManager(
            @NonNull Function<@NonNull CommandTree<CommandSender>, @NonNull CommandExecutionCoordinator<CommandSender>> commandExecutionCoordinator,
            @NonNull CommandRegistrationHandler commandRegistrationHandler
    ) {
        super(commandExecutionCoordinator, commandRegistrationHandler);

        Function<ParserParameters, CommandMeta> commandMetaFunction = parameters ->
                CommandMeta.simple()
                        // This will allow you to decorate commands with descriptions
                        .with(CommandMeta.DESCRIPTION, parameters.get(StandardParameters.DESCRIPTION, "No description"))
                        .build();

        this.annotationParser = new AnnotationParser<>(
                this,
                CommandSender.class,
                commandMetaFunction
        );
    }

    public static CommandManager getInstance() {
        return INSTANCE.get();
    }

    public void registerCommand(Object commandClass) {
        this.annotationParser.parse(commandClass);

        try {
            this.annotationParser.parseContainers();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
