package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code SignWaxEvent} represents a player's attempt to wax an unwaxed sign using
 * honeycomb. The sign component dispatches this event before setting the waxed
 * state, consuming honeycomb, or playing the wax sound; cancellation prevents all
 * three effects for that interaction.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class SignWaxEvent extends BlockEvent implements CancellableEvent {
    /**
     * Player applying honeycomb to the sign.
     */
    protected EntityPlayer player;

    /**
     * Creates a sign waxing request retaining the supplied block and player
     * references. Construction leaves the sign and held item unchanged; the sign
     * component dispatches the event before applying the wax.
     */
    public SignWaxEvent(Block block, EntityPlayer player) {
        super(block);
        this.player = player;
    }
}
