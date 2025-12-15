package org.allaymc.api.eventbus.event.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a command is about to be executed.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@Setter
@CallerThread(ThreadType.ANY)
public class CommandExecuteEvent extends CommandEvent implements CancellableEvent {
    protected CommandSender commandSender;
    protected String command;
}
