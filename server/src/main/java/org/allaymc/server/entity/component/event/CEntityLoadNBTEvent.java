package org.allaymc.server.entity.component.event;

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
public class CEntityLoadNBTEvent extends Event {
    protected NbtMap nbt;
}
