package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code CItemLoadExtraTagEvent} supplies extra NBT to item component load
 * handlers before the base component loads its own fields. The event retains the
 * supplied map reference; replacing the event property affects later handlers,
 * while the base component still reads its original method argument.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemLoadExtraTagEvent extends Event {
    /**
     * {@code extraTag} supplies the map read by component handlers. Its setter
     * replaces the event reference only, so the base component's own load still uses
     * the original argument.
     */
    protected NbtMap extraTag;
}
