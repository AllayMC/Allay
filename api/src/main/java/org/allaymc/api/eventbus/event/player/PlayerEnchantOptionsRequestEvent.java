package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.enchantment.EnchantOption;

import java.util.List;

/**
 * {@code PlayerEnchantOptionsRequestEvent} exposes generated enchanting-table
 * options before the server publishes them to a player. Cancellation stops that
 * publication after old options have been cleared; the standard producer supplies an
 * unmodifiable list containing the generated option objects.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerEnchantOptionsRequestEvent extends PlayerEvent implements CancellableEvent {

    /**
     * {@code enchantOptions} retains the generated option list. The standard
     * producer uses {@code Stream.toList()}, so callers cannot structurally modify
     * that list.
     */
    protected List<EnchantOption> enchantOptions;

    /**
     * Creates a {@code PlayerEnchantOptionsRequestEvent} for the supplied player and
     * event data. The constructor retains reference-valued arguments without copying
     * them and does not dispatch or apply the represented action.
     */
    public PlayerEnchantOptionsRequestEvent(EntityPlayer player, List<EnchantOption> enchantOptions) {
        super(player);
        this.enchantOptions = enchantOptions;
    }
}
