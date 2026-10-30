package org.allaymc.server.blockentity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * {@code CBlockEntitySaveCleanNBTEvent} lets components remove
 * location-dependent tags from an existing save builder. The base saver has
 * already serialized the entity and removed {@code "x"}, {@code "y"}, and
 * {@code "z"}. Handlers mutate that same builder, for example to discard
 * pairing coordinates before the saved state is relocated by a piston.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockEntitySaveCleanNBTEvent extends Event {
    /**
     * {@code nbt} holds the relocation-save builder after base-coordinate
     * removal. Handlers edit it in place before the base saver constructs the
     * resulting map.
     */
    protected NbtMapBuilder nbt;
}
