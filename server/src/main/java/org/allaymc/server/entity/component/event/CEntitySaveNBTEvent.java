package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * {@code CEntitySaveNBTEvent} shares an NBT builder with entity component save
 * handlers. The base component dispatches the event before writing its own
 * keys and building its original builder, so handlers contribute entries
 * directly and later base writes replace colliding component keys.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CEntitySaveNBTEvent extends Event {
    /**
     * {@code nbt} initially refers to the base save builder. Handlers should
     * write to that builder; replacing this event reference does not replace
     * the builder the base component ultimately builds.
     */
    protected NbtMapBuilder nbt;
}
