package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code CEntityLoadNBTEvent} carries the initialization or load compound to
 * entity component handlers. The base component dispatches it before loading
 * its own fields; replacing the event's {@code nbt} changes what later
 * handlers see but does not replace the original compound retained by the base
 * loader.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CEntityLoadNBTEvent extends Event {
    /**
     * {@code nbt} retains the compound exposed to component load handlers.
     * Replacing it affects later event readers, while the base component
     * continues loading its original argument.
     */
    protected NbtMap nbt;
}
