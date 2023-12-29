package org.allaymc.api.cmdv2;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public record CommandResult(int status) {
    public static CommandResult FAILED = new CommandResult(0);
    public static CommandResult SUCCESS = new CommandResult(1);
    public boolean isSuccess() {
        return status > 0;
    }
}
