package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author Dhaiven
 */
@AllArgsConstructor
@Getter
public class WhitelistAddPlayerEvent extends ServerEvent implements CancellableEvent {
    protected String uuidOrName;
}
