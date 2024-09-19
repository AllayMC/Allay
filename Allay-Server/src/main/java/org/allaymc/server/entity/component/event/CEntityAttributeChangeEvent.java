package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public class CEntityAttributeChangeEvent extends Event {
    public static CEntityAttributeChangeEvent INSTANCE = new CEntityAttributeChangeEvent();
}
