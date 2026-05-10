package org.allaymc.server.entity.component.event;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
public class CEntityGetDropXpEvent extends Event {
    @Getter
    @Setter
    private int xp;

    public CEntityGetDropXpEvent(int xp) {
        this.xp = xp;
    }
}
