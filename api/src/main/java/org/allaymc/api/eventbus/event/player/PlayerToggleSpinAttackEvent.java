package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code PlayerToggleSpinAttackEvent} reports a player entity's change in spin
 * attacking state. The player component dispatches this notification after storing
 * and broadcasting its new state; listeners inspect {@code value}, and the event
 * does not support cancelling or replacing the transition.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerToggleSpinAttackEvent extends PlayerEvent {
    /**
     * State reported by this transition.
     */
    protected boolean value;

    /**
     * Creates a {@code PlayerToggleSpinAttackEvent} for the supplied player and
     * event data. The constructor retains reference-valued arguments without copying
     * them and does not dispatch or apply the represented action.
     */
    public PlayerToggleSpinAttackEvent(EntityPlayer player, boolean value) {
        super(player);
        this.value = value;
    }
}
