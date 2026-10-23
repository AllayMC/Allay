package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * {@code CItemSaveExtraTagEvent} exposes the extra-tag builder used during item
 * component serialization. Handlers add their entries to that shared builder
 * before the base component adds its fields. Replacing the event property does
 * not replace the builder retained by the base save method.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemSaveExtraTagEvent extends Event {
    /**
     * {@code extraTag} initially references the base save method's builder.
     * Handlers must add entries to that builder before synchronous dispatch returns
     * for inclusion in the result; replacing the event reference does not redirect
     * the base method.
     */
    protected NbtMapBuilder extraTag;
}
