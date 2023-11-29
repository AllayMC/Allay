package org.allaymc.server.terminal;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class ConsoleSender implements CommandSender {

    @Override
    public @NotNull String getName() {
        return "CONSOLE";
    }

    @Override
    public void reply(@NotNull String message) {
        log.info(message);
    }

    @Override
    public void error(@NotNull String message) {
        log.error(message);
    }
}
