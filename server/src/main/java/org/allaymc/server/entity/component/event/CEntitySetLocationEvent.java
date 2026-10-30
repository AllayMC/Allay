package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.math.location.Location3dc;

/**
 * {@code CEntitySetLocationEvent} describes an entity location about to
 * replace the base component's stored location. Physics handlers inspect the
 * old owner location alongside this proposed location to update fall distance
 * when requested; the event retains the supplied location reference without
 * copying it.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CEntitySetLocationEvent extends Event {
    /**
     * {@code location} retains the proposed replacement location before the
     * base component copies it. Physics handlers read this reference alongside
     * the owner's still-current location during dispatch.
     */
    protected final Location3dc location;
    /**
     * Whether physics should update accumulated fall distance for this
     * movement.
     */
    protected final boolean calculateFallDistance;
}
