package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * {@code PlayerBlockPickEvent} represents a creative player's request to select a
 * block item. Listeners can cancel the pick or replace {@code itemBlock} and
 * {@code includeBlockEntity} before the processor attaches block-entity data and
 * searches the inventory for a matching item type.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerBlockPickEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Block targeted by the pick request.
     */
    protected Block clickedBlock;
    /**
     * {@code includeBlockEntity} controls whether the processor attaches available
     * block-entity NBT to the selected candidate before searching the inventory.
     */
    @Setter
    protected boolean includeBlockEntity;
    /**
     * {@code itemBlock} holds the candidate stack. The processor reads the
     * listener-selected reference and may attach block-entity NBT directly to it.
     */
    @Setter
    protected ItemStack itemBlock;

    /**
     * Creates a {@code PlayerBlockPickEvent} for the supplied player and event data.
     * The constructor retains reference-valued arguments without copying them and
     * does not dispatch or apply the represented action.
     */
    public PlayerBlockPickEvent(EntityPlayer player, Block clickedBlock, boolean includeBlockEntity, ItemStack itemBlock) {
        super(player);
        this.clickedBlock = clickedBlock;
        this.includeBlockEntity = includeBlockEntity;
        this.itemBlock = itemBlock;
    }
}
