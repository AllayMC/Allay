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
@AllArgsConstructor
public class CItemTryUseInAirEvent extends Event {
    protected EntityPlayer player;
    @Setter
    protected boolean canBeUsed;
}
