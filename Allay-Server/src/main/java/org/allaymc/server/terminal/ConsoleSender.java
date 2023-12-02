package org.allaymc.server.terminal;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class ConsoleSender implements CommandSender {

    @Override
    public @NotNull String getName() {
        return "CONSOLE";
    }

    @Override
    public void reply(@NotNull String message, Object... args) {
        log.info(String.format(message, args));
    }

    @Override
    public void error(@NotNull String message, Object... args) {
        log.error(String.format(message, args));
    }

    @Override
    public boolean hasPermission(@NonNull String permission) {
        return true;
    }
}
