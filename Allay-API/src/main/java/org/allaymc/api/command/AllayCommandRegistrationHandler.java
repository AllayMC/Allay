package org.allaymc.api.command;

import cloud.commandframework.Command;
import cloud.commandframework.internal.CommandRegistrationHandler;
import org.checkerframework.checker.nullness.qual.NonNull;

public class AllayCommandRegistrationHandler implements CommandRegistrationHandler {

    @Override
    public boolean registerCommand(@NonNull Command<?> command) {
        return false;
    }
}
