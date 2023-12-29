package org.allaymc.api.cmdv2;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface Command {

    String getName();

    CommandResult execute(CommandSender sender, String[] args);
}
