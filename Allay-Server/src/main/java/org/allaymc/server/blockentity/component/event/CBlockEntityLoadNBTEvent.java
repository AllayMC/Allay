package org.allaymc.server.blockentity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CBlockEntityLoadNBTEvent extends Event {
    protected NbtMap nbt;
}
