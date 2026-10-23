package org.allaymc.api.command;

import org.allaymc.api.command.tree.CommandContext;

/**
 * {@code CommandResult} pairs a command status with its optional execution
 * context for feedback delivery. Positive statuses count as success, while zero
 * and negative statuses count as failure. The context remains shared and may be
 * {@code null} when execution never reached a parsing context.
 *
 * @param status command-specific execution status
 * @param context shared execution context, or {@code null} when no context is
 * available
 *
 * @author daoge_cmd
 */
public record CommandResult(int status, CommandContext context) {

    /**
     * {@code FAIL_STATUS} is the default failure code used by {@code fail}.
     * This mutable value is read when each failure result is created.
     */
    public static int FAIL_STATUS = 0;

    /**
     * {@code SUCCESS_STATUS} is the default success code used by
     * {@code success}. {@code isSuccess} still tests positivity rather than
     * equality with this mutable value.
     */
    public static int SUCCESS_STATUS = 1;

    /**
     * Creates a failure result using the current {@code FAIL_STATUS} and
     * supplied context. {@code context} is retained without copying and may be
     * {@code null} when no execution context exists.
     */
    public static CommandResult fail(CommandContext context) {
        return new CommandResult(FAIL_STATUS, context);
    }

    /**
     * Creates a failure result without an associated execution context. The
     * result uses the current {@code FAIL_STATUS}, and feedback consumers must
     * account for its {@code null} context before reading outputs.
     */
    public static CommandResult fail() {
        return fail(null);
    }

    /**
     * Creates a success result using the current {@code SUCCESS_STATUS} and
     * supplied context. The context is retained directly, so later context
     * mutations remain visible through this result.
     */
    public static CommandResult success(CommandContext context) {
        return new CommandResult(SUCCESS_STATUS, context);
    }

    /**
     * Returns whether this result's status is strictly greater than zero. Zero
     * and negative statuses count as failure, including results constructed
     * directly with command-specific status values.
     */
    public boolean isSuccess() {
        return status > 0;
    }
}
