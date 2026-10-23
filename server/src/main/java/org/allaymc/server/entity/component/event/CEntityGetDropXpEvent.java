package org.allaymc.server.entity.component.event;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CEntityGetDropXpEvent} carries the mutable experience amount
 * requested from an entity component event bus. Living-entity drop calculation
 * starts with an amount and lets registered components replace it before the
 * caller reads the resulting value.
 *
 * @author daoge_cmd
 */
public class CEntityGetDropXpEvent extends Event {
    /**
     * {@code xp} holds the current experience drop amount. Component handlers
     * can replace it before the living component reads the result after
     * dispatch.
     */
    @Getter
    @Setter
    private int xp;

    /**
     * Creates a component event containing the initial experience drop amount.
     */
    public CEntityGetDropXpEvent(int xp) {
        this.xp = xp;
    }
}
