package org.allaymc.api.eventbus.event.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code CommandExecuteEvent} represents a command execution request before
 * registry lookup and permission checks. Listeners may replace the sender or
 * slash-free command text through the generated setters, or cancel the request.
 * The registry reads the updated values only when event dispatch permits
 * execution.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@Setter
@CallerThread(ThreadType.ANY)
public class CommandExecuteEvent extends CommandEvent implements CancellableEvent {
    /**
     * {@code commandSender} is the sender used after event dispatch. Replacing
     * it changes subsequent permission checks, execution origin, and feedback
     * delivery for this request.
     */
    protected CommandSender commandSender;
    /**
     * {@code command} holds command text without a leading {@code /}. The
     * registry tokenizes the listener-updated value only after cancellation
     * checks pass.
     */
    protected String command;
}
