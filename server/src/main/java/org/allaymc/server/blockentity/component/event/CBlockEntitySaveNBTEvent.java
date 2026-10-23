package org.allaymc.server.blockentity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * {@code CBlockEntitySaveNBTEvent} lets component handlers add their state to a
 * block entity's save builder. Base identity and coordinates are written after
 * dispatch and take precedence over conflicting handler values. Handlers mutate
 * the supplied builder in place; replacing the event reference does not replace
 * the base saver's builder.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CBlockEntitySaveNBTEvent extends Event {
    /**
     * {@code nbt} holds the shared save builder. Its generated setter changes
     * only the handler-visible reference, while the base saver retains its
     * original builder.
     */
    protected NbtMapBuilder nbt;
}
