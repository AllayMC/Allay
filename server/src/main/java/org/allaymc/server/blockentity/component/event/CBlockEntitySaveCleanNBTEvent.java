package org.allaymc.server.blockentity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * Event fired when saving clean NBT data (without position-related information).
 * Components can listen to this event to remove their own position-related fields.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CBlockEntitySaveCleanNBTEvent extends Event {
    protected NbtMapBuilder nbt;
}
