package org.allaymc.server.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.player.GameMode;

/**
 * {@code CPlayerGameModeChangeEvent} carries a player's selected replacement
 * game mode to its component handlers. The player base component sends it
 * after storing the new mode, and player physics uses the value to clear fall
 * distance and select gravity for spectator behavior.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CPlayerGameModeChangeEvent extends Event {
    /**
     * New game mode already selected by the player base component.
     */
    protected final GameMode gameMode;
}
