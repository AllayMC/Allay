package org.allaymc.api.component.interfaces;

import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.utils.Identifier;

import java.util.Map;

/**
 * Allay Project 2023/5/6
 *
 * @author daoge_cmd
 */
public interface ComponentManager {
    <E extends Event> E callEvent(E event);

    Map<Identifier, ? extends Component> getComponents();

    <T> T getComponent(Identifier componentIdentifier);
}
