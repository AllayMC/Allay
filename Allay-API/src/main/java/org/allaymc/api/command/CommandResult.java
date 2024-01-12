package org.allaymc.api.command;

import org.allaymc.api.command.tree.CommandContext;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public record CommandResult(int status, CommandContext context) {

    public static int FAILED_STATUS = 0;
    public static int SUCCESS_STATUS = 1;

    public static CommandResult failed(CommandContext context) {
        return new CommandResult(FAILED_STATUS, context);
    }

    public static CommandResult failed() {
        return failed(null);
    }

    public static CommandResult success(CommandContext context) {
        return new CommandResult(SUCCESS_STATUS, context);
    }

    public boolean isSuccess() {
        return status > 0;
    }
}
