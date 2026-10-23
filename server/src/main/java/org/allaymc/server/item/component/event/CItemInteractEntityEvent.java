package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CItemInteractEntityEvent} carries an entity interaction and its mutable
 * handled result through item component dispatch. The base item component starts
 * {@code canBeUsed} at {@code false} and returns the value available when dispatch returns, allowing handlers
 * to report that the item consumed the interaction.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class CItemInteractEntityEvent extends Event {
    /**
     * Entity performing the item interaction.
     */
    protected Entity performer;
    /**
     * Entity receiving the item interaction.
     */
    protected Entity victim;
    /**
     * {@code canBeUsed} holds the interaction result returned by the base component
     * when dispatch returns. Handlers can replace an earlier handler's result through its
     * setter.
     */
    @Setter
    protected boolean canBeUsed;
}
