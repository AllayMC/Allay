package org.allaymc.api.eventbus.event.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.world.World;

/**
 * {@code WorldEvent} supplies the owning world reference shared by world and
 * chunk event types. The generated constructor retains the supplied handle and
 * the generated getter exposes it directly; individual event callers determine
 * when the notification occurs during world operations.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class WorldEvent extends Event {
    /**
     * World associated with this event.
     */
    protected World world;
}
