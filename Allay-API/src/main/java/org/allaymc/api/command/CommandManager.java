package org.allaymc.api.command;

import cloud.commandframework.CommandTree;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.internal.CommandRegistrationHandler;
import org.allaymc.api.ApiInstanceHolder;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public abstract class CommandManager extends cloud.commandframework.CommandManager<CommandSender> {

    public static final ApiInstanceHolder<CommandManager> INSTANCE = ApiInstanceHolder.of();

    protected CommandManager(
            @NonNull Function<@NonNull CommandTree<CommandSender>, @NonNull CommandExecutionCoordinator<CommandSender>> commandExecutionCoordinator,
            @NonNull CommandRegistrationHandler commandRegistrationHandler
    ) {
        super(commandExecutionCoordinator, commandRegistrationHandler);
    }

    public static CommandManager getInstance() {
        return INSTANCE.get();
    }

    public abstract void registerCommand(@NotNull Object commandClass);
}
