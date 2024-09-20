package org.allaymc.api.command;

import org.allaymc.api.command.tree.CommandContext;

/**
 * @author daoge_cmd
 */
public record CommandResult(int status, CommandContext context) {

    /**
     * Represents the status of a command execution failure.
     */
    public static int FAIL_STATUS = 0;

    /**
     * Represents the status of a command execution success.
     */
    public static int SUCCESS_STATUS = 1;

    /**
     * Creates a new CommandResult instance for a failed command execution.
     *
     * @param context The command context.
     *
     * @return A new CommandResult instance for a failed command execution.
     */
    public static CommandResult fail(CommandContext context) {
        return new CommandResult(FAIL_STATUS, context);
    }

    /**
     * Creates a new CommandResult instance for a failed command execution with a null context.
     *
     * @return A new CommandResult instance for a failed command execution with a null context.
     */
    public static CommandResult fail() {
        return fail(null);
    }

    /**
     * Creates a new CommandResult instance for a successful command execution.
     *
     * @param context The command context.
     *
     * @return A new CommandResult instance for a successful command execution.
     */
    public static CommandResult success(CommandContext context) {
        return new CommandResult(SUCCESS_STATUS, context);
    }

    /**
     * Checks if the command execution was successful.
     *
     * @return {@code true} if the command execution was successful, {@code false} otherwise.
     */
    public boolean isSuccess() {
        return status > 0;
    }
}
