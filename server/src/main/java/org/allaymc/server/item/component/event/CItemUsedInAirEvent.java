package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemUsedInAirEvent extends Event {
    protected EntityPlayer player;
    protected long usedTime;
    protected boolean canBeUsed;
}
