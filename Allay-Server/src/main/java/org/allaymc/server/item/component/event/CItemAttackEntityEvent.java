package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemAttackEntityEvent extends Event {
    protected Entity attacker;
    protected Entity victim;
}
