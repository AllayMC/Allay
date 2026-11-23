package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;

/**
 * {@code CItemAttackEntityEvent} carries the attacker and victim to an item's
 * component handlers during attack notification. The event retains both entity
 * references, and its generated setters can change the values seen by subsequent
 * component handlers without copying either entity.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemAttackEntityEvent extends Event {
    /**
     * Owning item stack used to attack the target.
     */
    protected ItemStack itemStack;
    /**
     * Entity performing the item attack.
     */
    protected Entity attacker;
    /**
     * Entity targeted by the item attack.
     */
    protected Entity victim;
}
