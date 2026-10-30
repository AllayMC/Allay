package org.allaymc.server.item.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CItemTryUseInAirEvent} asks item components whether a player can begin
 * a timed use in air. The base component starts {@code canBeUsed} at
 * {@code false} and returns the value available when dispatch returns, allowing
 * a component to opt into timed use.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class CItemTryUseInAirEvent extends Event {
    /**
     * Player attempting timed item use.
     */
    protected EntityPlayer player;
    /**
     * {@code canBeUsed} holds the eligibility result available when component
     * dispatch returns. Handlers set this flag to select or reject the timed-use
     * path.
     */
    @Setter
    protected boolean canBeUsed;
}
