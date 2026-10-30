package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentInstance;

import java.util.List;

/**
 * {@code PlayerEnchantItemEvent} holds a proposed enchanting-table operation and its
 * lapis cost. Listeners can cancel the operation or replace {@code enchantments} and
 * {@code requiredLapisLazuliCount}; the crafting processor reads those final values
 * before checking materials and applying the enchantments.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerEnchantItemEvent extends PlayerEvent implements CancellableEvent {

    /**
     * {@code itemStack} retains the mutable input item. The crafting processor
     * applies accepted enchantments to this same stack after checking its
     * requirements.
     */
    protected ItemStack itemStack;
    /**
     * {@code enchantments} supplies the accepted enchantment list. The crafting
     * processor reads the listener-selected reference after dispatch without copying
     * it here.
     */
    @Setter
    protected List<EnchantmentInstance> enchantments;
    /**
     * {@code requiredLapisLazuliCount} supplies the accepted material cost. The
     * crafting processor uses it after dispatch; this event does not constrain its
     * range.
     */
    @Setter
    protected int requiredLapisLazuliCount;

    /**
     * Creates a {@code PlayerEnchantItemEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerEnchantItemEvent(EntityPlayer player, ItemStack itemStack, List<EnchantmentInstance> enchantments, int requiredLapisLazuliCount) {
        super(player);
        this.itemStack = itemStack;
        this.enchantments = enchantments;
        this.requiredLapisLazuliCount = requiredLapisLazuliCount;
    }
}
