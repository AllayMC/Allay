package org.allaymc.server.entity.component.event;

import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@Getter
public class CEntityInitMetadataEvent extends Event {
    public static final CEntityInitMetadataEvent INSTANCE = new CEntityInitMetadataEvent();
}
