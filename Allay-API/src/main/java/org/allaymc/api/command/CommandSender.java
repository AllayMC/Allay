package org.allaymc.api.command;

import cloud.commandframework.execution.CommandResult;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface CommandSender {

    @NotNull String getName();

    void reply(@NotNull String message, Object... args);

    default void error(@NotNull String message, Object... args) {
        this.reply(String.format("§4" + message, args));
    }

    default @NotNull CommandManager getCommandManager() {
        return CommandManager.getInstance();
    }

    default @NonNull CompletableFuture<CommandResult<CommandSender>> dispatch(@NotNull String command) {
        return this.getCommandManager().executeCommand(this, command);
    }

    boolean hasPermission(@NonNull String permission);
}
