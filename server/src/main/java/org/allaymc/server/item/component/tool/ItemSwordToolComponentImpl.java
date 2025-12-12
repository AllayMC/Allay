package org.allaymc.server.item.component.tool;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.ItemToolComponent;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.item.component.event.CItemAttackEntityEvent;
import org.allaymc.server.item.component.event.CItemBreakBlockEvent;

/**
 * @author daoge_cmd
 */
public class ItemSwordToolComponentImpl extends ItemToolComponentImpl implements ItemToolComponent {
    @Dependency
    protected ItemBaseComponent baseComponent;

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

        baseComponent.tryIncreaseDamage(2);
    }

    @EventHandler
    protected void onAttackEntity(CItemAttackEntityEvent event) {
        baseComponent.tryIncreaseDamage(1);
    }
}
