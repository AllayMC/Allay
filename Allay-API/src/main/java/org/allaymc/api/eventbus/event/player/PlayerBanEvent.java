package org.allaymc.api.eventbus.event.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class PlayerBanEvent extends Event implements CancellableEvent {
    protected String uuidOrName;
}
