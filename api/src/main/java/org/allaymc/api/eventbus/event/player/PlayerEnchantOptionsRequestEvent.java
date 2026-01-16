package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.enchantment.EnchantOption;

import java.util.List;

/**
 * PlayerEnchantOptionsRequestEvent is called when a player requests a new set of enchantment options, usually
 * when using an enchantment table。
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerEnchantOptionsRequestEvent extends PlayerEvent implements CancellableEvent {

    /**
     * The generated enchant options, there should be three options in the list.
     */
    protected List<EnchantOption> enchantOptions;

    public PlayerEnchantOptionsRequestEvent(EntityPlayer player, List<EnchantOption> enchantOptions) {
        super(player);
        this.enchantOptions = enchantOptions;
    }
}
