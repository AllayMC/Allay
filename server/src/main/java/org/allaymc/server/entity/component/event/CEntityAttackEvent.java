package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CEntityAttackEvent} notifies an attacking entity's components after
 * an accepted damage operation. The living component sends the shared payload
 * to an entity attacker after victim damage callbacks; handlers use the owning
 * component manager to identify the attacker because this event has no entity
 * field.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class CEntityAttackEvent extends Event {
    /**
     * {@code INSTANCE} supplies the shared payload for component-manager
     * dispatch. The event carries no operation-specific data, and its type
     * does not permit cancellation through the inherited event API.
     */
    public static CEntityAttackEvent INSTANCE = new CEntityAttackEvent();
}
