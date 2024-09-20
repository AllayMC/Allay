package org.allaymc.api.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class IPBanEvent extends NetworkEvent implements CancellableEvent {
    protected String ip;
}
