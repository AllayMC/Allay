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
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerEnchantItemEvent extends PlayerEvent implements CancellableEvent {

    protected ItemStack itemStack;
    @Setter
    protected List<EnchantmentInstance> enchantments;
    @Setter
    protected int requiredLapisLazuliCount;

    public PlayerEnchantItemEvent(EntityPlayer player, ItemStack itemStack, List<EnchantmentInstance> enchantments, int requiredLapisLazuliCount) {
        super(player);
        this.itemStack = itemStack;
        this.enchantments = enchantments;
        this.requiredLapisLazuliCount = requiredLapisLazuliCount;
    }
}
