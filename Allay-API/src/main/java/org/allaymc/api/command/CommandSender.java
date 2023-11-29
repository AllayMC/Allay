package org.allaymc.api.command;

import cloud.commandframework.execution.CommandResult;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface CommandSender {

    @NotNull String getName();

    void reply(@NotNull String message);

    default void error(@NotNull String message) {
        this.reply("§4" + message);
    }

    default @NotNull CommandManager getCommandManager() {
        return CommandManager.getInstance();
    }

    default @NonNull CompletableFuture<CommandResult<CommandSender>> dispatch(@NotNull String command) {
        return this.getCommandManager().executeCommand(this, command);
    }
}
