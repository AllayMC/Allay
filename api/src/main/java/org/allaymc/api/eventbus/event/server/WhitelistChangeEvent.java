package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author Dhaiven
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.UNKNOWN)
public class WhitelistChangeEvent extends ServerEvent implements CancellableEvent {
    protected boolean enable;
}
