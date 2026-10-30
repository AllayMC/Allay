package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code PlayerSwingArmEvent} reports an arm-swing animation received from the
 * controlling client. The animation processor dispatches this notification on its
 * asynchronous receive path before applying the swing action; the event is not
 * cancellable and supplies no replacement animation.
 *
 * @author zernix2077
 */
@CallerThread(ThreadType.NETWORK)
public class PlayerSwingArmEvent extends PlayerEvent {
    /**
     * Creates a {@code PlayerSwingArmEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerSwingArmEvent(EntityPlayer player) {
        super(player);
    }
}
