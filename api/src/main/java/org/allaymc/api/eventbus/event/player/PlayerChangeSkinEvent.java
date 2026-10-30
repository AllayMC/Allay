package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.Skin;

/**
 * {@code PlayerChangeSkinEvent} describes a proposed replacement of a player
 * entity's skin. The skin packet processor dispatches this event after validating
 * and converting the submitted skin; cancellation preserves the current skin, while
 * acceptance applies the listener-selected {@code newSkin}.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerChangeSkinEvent extends PlayerEvent implements CancellableEvent {
    /**
     * {@code oldSkin} retains the previous skin reference for comparison. The event
     * does not copy its shared image arrays or metadata collections.
     */
    protected Skin oldSkin;
    /**
     * {@code newSkin} supplies the accepted replacement. The packet processor reads
     * this value after listeners run and passes it to the entity.
     */
    @Setter
    protected Skin newSkin;

    /**
     * Creates a {@code PlayerChangeSkinEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerChangeSkinEvent(EntityPlayer player, Skin oldSkin, Skin newSkin) {
        super(player);
        this.oldSkin = oldSkin;
        this.newSkin = newSkin;
    }
}
