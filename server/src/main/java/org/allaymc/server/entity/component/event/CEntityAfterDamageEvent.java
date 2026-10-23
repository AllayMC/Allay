package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CEntityAfterDamageEvent} signals that the living component has
 * attempted its health update and applied hit actions. The shared event carries
 * no victim payload; component handlers use their owner references, and
 * dispatch occurs before the later attacker notification and optional knockback
 * path.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class CEntityAfterDamageEvent extends Event {
    /**
     * {@code INSTANCE} supplies the reusable payload-free notification
     * dispatched by living components after damage application, allowing
     * handlers to react through their own component state.
     */
    public static CEntityAfterDamageEvent INSTANCE = new CEntityAfterDamageEvent();
}
