package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CEntityTickEvent} carries the dimension tick number to ordinary
 * entity component handlers. The base component creates the event during its
 * sequential tick after advancing its own tick state, allowing living and AI
 * components to perform work associated with that dimension tick.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CEntityTickEvent extends Event {
    /**
     * Dimension tick number for this ordinary entity tick.
     */
    protected final long currentTick;
}
