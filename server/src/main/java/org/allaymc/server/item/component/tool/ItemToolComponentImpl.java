package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

/**
 * @author IWareQ, daoge_cmd
 */
public class ItemToolComponentImpl implements ItemToolComponent {
    @ComponentObject
    protected ItemStack itemStack;

    @EventHandler
    protected void onBreakBlock(CItemBreakBlockEvent event) {
        var entity = event.getBreaker();
        if (entity instanceof EntityPlayer player && player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        var hardness = event.getBlock().getBlockStateData().hardness();
        if (hardness == 0) {
            return;
        }

        if (!event.getBlock().getBehavior().canDamageItem(itemStack)) {
            return;
        }

        itemStack.tryIncreaseDamage(1);
    }

    @EventHandler
    protected void onAttackEntity(CItemAttackEntityEvent event) {
        itemStack.tryIncreaseDamage(2);
    }
}
