package org.allaymc.api.eventbus.event.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Allay Project 2024/2/26
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class CommandExecuteEvent extends CommandEvent implements CancellableEvent {
    protected CommandSender commandSender;
    protected String command;
}
