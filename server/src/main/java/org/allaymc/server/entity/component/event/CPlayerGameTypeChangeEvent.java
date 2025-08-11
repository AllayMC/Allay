package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CPlayerGameTypeChangeEvent extends Event {
    protected final GameType gameType;
}
