package org.allaymc.api.command;

import org.allaymc.api.command.tree.CommandContext;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public record CommandResult(int status, CommandContext context) {

    public static int FAIL_STATUS = 0;
    public static int SUCCESS_STATUS = 1;

    public static CommandResult fail(CommandContext context) {
        return new CommandResult(FAIL_STATUS, context);
    }

    public static CommandResult fail() {
        return fail(null);
    }

    public static CommandResult success(CommandContext context) {
        return new CommandResult(SUCCESS_STATUS, context);
    }

    public boolean isSuccess() {
        return status > 0;
    }
}
