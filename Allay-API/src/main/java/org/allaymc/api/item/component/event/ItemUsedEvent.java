package org.allaymc.api.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/7/7
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class ItemUsedEvent extends Event {
    protected EntityPlayer player;
    protected long usedTime;
    protected boolean canBeUsed;
}
