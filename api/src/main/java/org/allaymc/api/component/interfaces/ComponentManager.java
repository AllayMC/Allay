package org.allaymc.api.component.interfaces;

import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.utils.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

/**
 * @author daoge_cmd
 */
@ApiStatus.Internal
public interface ComponentManager {
    <E extends Event> E callEvent(E event);

    Map<Identifier, ? extends Component> getComponents();

    <T> T getComponent(Identifier componentIdentifier);
}
