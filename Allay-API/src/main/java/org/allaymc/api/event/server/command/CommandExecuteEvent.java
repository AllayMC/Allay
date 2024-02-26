package org.allaymc.api.event.server.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.event.CancellableEvent;
import org.allaymc.api.event.Event;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CommandExecuteEvent extends Event implements CancellableEvent {
    protected CommandSender commandSender;
    protected String command;
}
