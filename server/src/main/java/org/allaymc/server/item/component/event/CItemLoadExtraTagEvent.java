package org.allaymc.server.item.component.event;

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
public class CItemLoadExtraTagEvent extends Event {
    protected NbtMap extraTag;
}
