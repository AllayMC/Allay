package org.allaymc.server.command;

import cloud.commandframework.Description;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.arguments.standard.IntegerArgument;
import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.meta.SimpleCommandMeta;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.AllayCommandRegistrationHandler;
import org.allaymc.api.command.CommandManager;
import org.allaymc.api.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

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
        registerCommand(new GamemodeCommand());

        // for tests only
        command(commandBuilder("test", Description.of("Test cloud command using a builder"), "testalias")
                .argument(StringArgument.quoted("input"))
                .argument(IntegerArgument.<CommandSender>builder("number").withMin(1).withMax(100).build())
                .handler(context -> {
                    String input = context.get("input");
                    int number = context.get("number");
                    log.info(String.format("I am %d%% hyped for %s!", number, input));
                }));

        commands().forEach(c -> {
            c.getArguments().forEach(arg -> log.info(arg.toString()));
            c.getComponents().forEach(arg -> log.info(arg.toString()));
            log.info(c.toString());
        });

    }

    @Override
    public boolean hasPermission(@NonNull CommandSender sender, @NonNull String permission) {
        return true;
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
