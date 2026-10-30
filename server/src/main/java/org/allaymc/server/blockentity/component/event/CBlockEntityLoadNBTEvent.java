package org.allaymc.server.blockentity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code CBlockEntityLoadNBTEvent} supplies NBT to component handlers before
 * the base component reads its fields. The base loader continues using its
 * original map after dispatch. Replacing this event's map affects later
 * handlers that read the event, but does not replace the map used for base
 * coordinates, name, or persistent data.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CBlockEntityLoadNBTEvent extends Event {
    /**
     * {@code nbt} holds the handler-visible load payload. The generated setter
     * replaces this event's reference without changing the base loader's
     * separately retained input map.
     */
    protected NbtMap nbt;
}
