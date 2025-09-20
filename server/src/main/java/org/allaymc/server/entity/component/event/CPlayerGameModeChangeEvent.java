package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.player.GameMode;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CPlayerGameModeChangeEvent extends Event {
    protected final GameMode gameMode;
}
